# 🚛 PROACTIVE TYRE MAINTENANCE SYSTEM FOR DUMPERS  
### 🔧 Using IoT-Driven Monitoring and ML-Based Analysis

## 📌 Objective

This system aims to **monitor and predict tyre performance** in heavy-duty dumpers using real-time sensor data, intelligent analysis, and machine learning. The goal is to reduce unexpected failures and increase operational efficiency through proactive maintenance insights.

---

## 🌐 System Overview

### 🛠️ Core Components:

- **Sensors & ESP32**: Collects environmental and operational data
- **Backend (Spring Boot)**: Processes data, stores it in MySQL, and communicates with ML models
- **Machine Learning Models**: Predicts tyre life, detects anomalies, and classifies tyre safety
- **Frontend (HTML/CSS/JS)**: Visualizes data and alerts in real-time

---

## 📡 Sensor Inputs

| Sensor        | Parameter                 |
|---------------|----------------------------|
| BMP280        | Temperature, Pressure      |
| SW420         | Vibration Detection        |
| MPU6050       | Tilt Detection ("Tilt"/"No Tilt") |
| GPS BN-880    | Speed, Latitude, Longitude |

---

## ⚙️ Backend Technology

- **Java Spring Boot (Eclipse IDE)**
- RESTful APIs to receive data from ESP32
- MySQL database for persistent storage
- Integrates with ML models for predictions and anomaly detection

---

## 🤖 Machine Learning Modules

### 1. **Tyre Life Prediction**
- **Model**: `XGBRegressor`
- **Purpose**: Predict remaining tyre life (in hours or cycles)

### 2. **Safety Classification**
- **Model**: Logistic Regression
- **Purpose**: Label tyres as **"Safe"** or **"Risky"**

### 3. **Anomaly Detection**
- **Model**: Isolation Forest
- **Purpose**: Identify abnormal tyre behavior or usage patterns

> All models use `StandardScaler` for feature scaling.

---

## 🔁 Data Flow Options

### 1. **Real-Time Data Processing**
- Data from ESP32 is immediately analyzed
- Ideal for live alerts and anomaly detection

### 2. **Database-Fetched Data**
- ML model reads latest stored data from MySQL
- Useful for trend-based predictions (e.g., tyre degradation over time)

---

## 💻 Web Interface

The web UI is designed for monitoring and analysis, built with **HTML**, **CSS**, and **JavaScript**:

- 📈 **Bar & Line Charts**: Visualize temperature, pressure, speed, tilt, vibration
- 🚨 **Live Notifications**: Alert on unsafe or abnormal conditions
- 🧠 **ML Insights View**: See predictions like tyre safety and life status
- 🧭 **Navigation Bar**: Quick access to different modules and dashboards


