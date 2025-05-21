# 🤖 ML Models – Proactive Tyre Maintenance System

This directory contains the machine learning models used to:
- Predict the remaining life of tyres
- Classify tyre safety status
- Detect anomalous tyre behavior

These models are deployed via a Flask API and are consumed by the Spring Boot backend through RESTful endpoints.

---

## 🔍 Model Details

### 1. Regression – Tyre Life Prediction

- **Inputs:**  
  Pressure, temperature, vibration, tilt, speed (retrieved from database)

- **Model:**  
  `XGBoost Regressor (XGBRegressor)`

- **Output:**  
  Predicted remaining tyre life in kilometers

- **Endpoint:**  
  `POST /predict/regression`

- **Integration:**  
  Spring Boot sends formatted JSON data to the Flask server, which responds with the predicted tyre life.

---

### 2. Classification – Safety Status (Hybrid)

- **Inputs:**  
  Pressure, temperature, vibration, tilt, speed

- **Model:**  
  `Logistic Regression`

- **Scaler:**  
  `StandardScaler`

- **Output:**  
  Tyre safety status: `"Safe"` or `"Risky"`

- **Endpoints:**  
  - `POST /predict/classification/live` – for live data  
  - `POST /predict/classification/stored` – fallback to last 10 records from DB

- **Integration:**  
  Spring Boot checks for real-time data. If unavailable, it falls back to stored data. Both are sent to Flask via respective endpoints.

---

### 3. Anomaly Detection – Tyre Behavior (Hybrid)

- **Inputs:**  
  Pressure, temperature, vibration, tilt, speed

- **Model:**  
  `Isolation Forest`

- **Scaler:**  
  `StandardScaler`

- **Output:**  
  `"Normal"` or `"Anomaly"`

- **Endpoints:**  
  - `POST /predict/anomaly/live` – for live buffered sensor data  
  - `POST /predict/anomaly/stored` – fallback to last 10 records

- **Integration:**  
  The Flask server flags anomalies and returns them to the backend for alert generation.
