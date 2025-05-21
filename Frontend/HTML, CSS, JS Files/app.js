import axios from 'axios';
import SockJS from 'sockjs-client';
import Stomp from 'stompjs';

const backendUrl = 'http://<YOUR_LOCAL_IP>:8080/api/data';
const websocketUrl = 'http://<YOUR_LOCAL_IP>:8080/ws';

// Fetch Tyre Data via REST API
async function fetchTyreData() {
    try {
        const response = await axios.get(backendUrl);
        console.log("Tyre Data:", response.data);
    } catch (error) {
        console.error("Error fetching data:", error);
    }
}

// Save Tyre Data via REST API
async function saveTyreData(data) {
    try {
        const response = await axios.post(backendUrl, data);
        console.log("Data saved:", response.data);
    } catch (error) {
        console.error("Error saving data:", error);
    }
}

// WebSocket Connection
const socket = new SockJS(websocketUrl);
const stompClient = Stomp.over(socket);

stompClient.connect({}, (frame) => {
    console.log('Connected: ' + frame);

    // Subscribe to the WebSocket topic
    stompClient.subscribe('/topic/tyreData', (message) => {
        const tyreData = JSON.parse(message.body);
        console.log("Real-time Tyre Data:", tyreData);
    });
});

// Example Usage
fetchTyreData();
saveTyreData({ temperature: 30, pressure: 32 });
