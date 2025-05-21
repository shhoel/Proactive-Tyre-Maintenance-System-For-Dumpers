async function fetchSensorData() {
    try {
        const response = await fetch("http://localhost:8080/api/data");  // Ensure your backend is running
        if (!response.ok) {
            throw new Error("Failed to fetch sensor data");
        }
        const data = await response.json();
        console.log("✅ Sensor Data:", data);

        // Update the UI with sensor values
        document.getElementById("temperature").innerText = `Temperature: ${data.temperature}°C`;
        document.getElementById("pressure").innerText = `Pressure: ${data.pressure} hPa`;
        document.getElementById("vibration").innerText = `Vibration: ${data.vibration}`;
        document.getElementById("tilt").innerText = `Tilt: ${data.tilt ? "Yes" : "No"}`;
        document.getElementById("location").innerText = `Location: ${data.latitude}, ${data.longitude}`;

    } catch (error) {
        console.error("❌ Error fetching sensor data:", error);
    }
}

// Fetch data every 5 seconds
setInterval(fetchSensorData, 5000);

// Call once when the page loads
fetchSensorData();
