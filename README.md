🚛 PROACTIVE TYRE MAINTENANCE SYSTEM FOR DUMPERS
🔧 An IoT-Powered Real-Time Monitoring and Predictive Analytics Platform
📖 Description
Proactive Tyre Maintenance System for Dumpers is a smart, IoT-enabled solution designed to enhance tyre performance and safety in mining and heavy-duty industrial environments. This system continuously monitors critical tyre and vehicle parameters using real-time sensor data and predicts tyre health using advanced machine learning models. It calculates the TKPH (Tonne Kilometres Per Hour) to ensure tyres operate within safe thermal limits and helps prevent premature tyre failures.

The project uses an ESP32 microcontroller to collect sensor data including temperature, pressure, vibration, tilt, speed, and location. This data is sent to a Java Spring Boot backend for processing, storage in a MySQL database, and analysis using trained machine learning models. Users can access a responsive web dashboard to visualize live sensor data, receive alerts, and review ML-based predictions.

🎯 Key Features
TKPH Calculation
Uses vehicle load and travel speed to calculate Tonne Kilometre Per Hour for tyre safety.

Sensor Monitoring via ESP32

BMP280 – Temperature and Pressure

SW420 – Vibration detection

MPU6050 – Tilt detection (Tilt/No Tilt)

GPS BN-880 – Speed, Latitude, and Longitude

Data Transmission & Backend

Data sent from ESP32 in real-time to a Spring Boot API

Stored securely in a MySQL database

Preprocessed and passed to ML models for predictions

Machine Learning Integration

XGBRegressor – Predict remaining tyre life

Logistic Regression – Classify tyres as "Safe" or "Risky"

Isolation Forest – Detect anomalies in tyre behavior

Uses StandardScaler for feature scaling

Web Interface

Displays sensor data using bar and line charts

Shows live alerts for anomalies

Includes navigation with a responsive navbar

Displays ML predictions (remaining life, safety status, anomalies)

Dual Data Use for ML

Real-Time Analysis: For instant alerting and monitoring

Historical Analysis: For pattern-based predictions using stored data

📦 Tech Stack
Hardware: ESP32, BMP280, SW420, MPU6050, GPS BN-880

Backend: Java Spring Boot

Database: MySQL

Frontend: HTML, CSS, JavaScript

Machine Learning: Python, XGBoost, Logistic Regression, Isolation Forest, StandardScaler

Communication: HTTP REST API (ESP32 → Backend)

🔒 Use Case
This system is ideal for mining industries and large-scale construction operations where tyre health is critical to vehicle safety and efficiency. By enabling predictive maintenance, this solution reduces unexpected breakdowns, downtime, and maintenance costs.
