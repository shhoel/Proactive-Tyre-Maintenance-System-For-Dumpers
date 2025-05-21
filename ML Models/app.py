from flask import Flask, request, jsonify
import numpy as np
import pickle

app = Flask(__name__)

# Load models
with open('models/regression_model.pkl', 'rb') as f:
    regression_model = pickle.load(f)
    
with open('models/classification_model.pkl', 'rb') as f:
    classification_model = pickle.load(f)
    
with open('models/anomaly_model.pkl', 'rb') as f:
    anomaly_model = pickle.load(f)

# Load scalers
with open('models/scaler_classification.pkl', 'rb') as f:
    clf_scaler = pickle.load(f)
    
with open('models/scaler_anomaly.pkl', 'rb') as f:
    ano_scaler = pickle.load(f)

@app.route('/predict', methods=['POST'])
def predict():
    data = request.get_json()
    try:
        # Extract required fields
        temperature = float(data['temperature'])
        pressure = float(data['pressure'])
        vibration = float(data['vibration'])
        speed = float(data['speed'])

        # Safe tilt conversion
        tilt_value = data.get('tilt', 0)
        if isinstance(tilt_value, str):
            tilt = 1 if tilt_value.lower() == "tilt" else 0
        elif isinstance(tilt_value, (int, float)):
            tilt = int(tilt_value)
        else:
            tilt = 0

        tkph = float(data.get('tkph', 0))

        # Prepare features
        reg_features = np.array([[temperature, pressure, vibration, tilt, speed, tkph]])
        clf_ano_features = np.array([[temperature, pressure, vibration, tilt, speed]])

        # Predict
        tyre_life = regression_model.predict(reg_features)[0]
        clf_scaled = clf_scaler.transform(clf_ano_features)
        safety = classification_model.predict(clf_scaled)[0]
        ano_scaled = ano_scaler.transform(clf_ano_features)
        anomaly = anomaly_model.predict(ano_scaled)[0]

        return jsonify({
            "tyre_life_prediction": float(tyre_life),
            "safety_status": "Safe" if safety == 0 else "Risky",
            "anomaly_status": "Anomaly" if anomaly == -1 else "Normal"
        })
        
    except Exception as e:
        return jsonify({"error": str(e)}), 500

if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0', port=5000)
