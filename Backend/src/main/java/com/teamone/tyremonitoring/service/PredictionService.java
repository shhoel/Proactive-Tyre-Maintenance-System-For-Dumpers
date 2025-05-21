package com.teamone.tyremonitoring.service;

import com.teamone.tyremonitoring.model.*;
import com.teamone.tyremonitoring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.ParameterizedTypeReference;

import java.util.*;

@Service
public class PredictionService {

    @Autowired
    private TyreDataRepository tyreDataRepository;

    @Autowired
    private TkphDataRepository tkphDataRepository;

    @Autowired
    private LiveSensorService liveSensorService;

    private final RestTemplate restTemplate;

    public PredictionService() {
        this.restTemplate = new RestTemplate();
    }

    // Regression: Uses stored data only
    public PredictionResult getRegressionPrediction() {
        PredictionResult result = new PredictionResult();

        try {
            TyreData latestData = getLatestTyreData();
            TkphData latestTkph = getLatestTkphData();

            Map<String, Object> flaskRequest = new HashMap<>();
            addSensorData(flaskRequest, latestData, true);
            flaskRequest.put("tkph", latestTkph != null ? latestTkph.getTkph() : 0.0);

            ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                "http://localhost:5000/predict",
                HttpMethod.POST,
                new HttpEntity<>(flaskRequest),
                new ParameterizedTypeReference<>() {}
            );

            Map<String, Object> responseBody = response.getBody();
            if (responseBody != null) {
                result.setAnomalyStatus((String) responseBody.get("anomaly_status"));
                result.setSafetyStatus((String) responseBody.get("safety_status"));
                result.setTyreLifePrediction(Double.parseDouble(responseBody.get("tyre_life_prediction").toString()));
            }

        } catch (Exception e) {
            handleError(result, e);
        }

        return result;
    }

    // ✅ Live classification uses cache first
    public PredictionResult getClassificationLive() {
        TyreData liveData = liveSensorService.getLatestLiveData();
        return liveData != null ? buildPrediction(liveData, getLastNRecords(10), getLatestTkphData())
                : getFallbackLivePrediction();
    }

    // Stored classification uses DB
    public PredictionResult getClassificationStored() {
        return buildPrediction(getLatestTyreData(), getLastNRecords(10), getLatestTkphData());
    }

    // Live anomaly detection
    public PredictionResult getAnomalyLive() {
        return getClassificationLive();
    }

    // Stored anomaly detection
    public PredictionResult getAnomalyStored() {
        return getClassificationStored();
    }

    private PredictionResult buildPrediction(TyreData currentData, List<TyreData> historicalData, TkphData tkphData) {
        PredictionResult result = new PredictionResult();

        try {
            Map<String, Object> flaskRequest = new HashMap<>();
            addSensorData(flaskRequest, currentData, true);
            flaskRequest.put("tkph", tkphData != null ? tkphData.getTkph() : 0.0);

            List<Map<String, Object>> historyList = new ArrayList<>();
            for (TyreData data : historicalData) {
                Map<String, Object> historyMap = new HashMap<>();
                addSensorData(historyMap, data, false);
                historyList.add(historyMap);
            }
            flaskRequest.put("historical_data", historyList);

            ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                "http://localhost:5000/predict",
                HttpMethod.POST,
                new HttpEntity<>(flaskRequest),
                new ParameterizedTypeReference<>() {}
            );

            Map<String, Object> responseBody = response.getBody();
            if (responseBody != null) {
                result.setAnomalyStatus((String) responseBody.get("anomaly_status"));
                result.setSafetyStatus((String) responseBody.get("safety_status"));
                result.setTyreLifePrediction(Double.parseDouble(responseBody.get("tyre_life_prediction").toString()));
            }

        } catch (Exception e) {
            handleError(result, e);
        }

        return result;
    }

    private PredictionResult getFallbackLivePrediction() {
        PredictionResult result = new PredictionResult();
        result.setAnomalyStatus("No live data");
        result.setSafetyStatus("Using stored");
        result.setTyreLifePrediction(0);
        return result;
    }

    private void addSensorData(Map<String, Object> target, TyreData source, boolean includeTilt) {
        target.put("temperature", source.getTemperature());
        target.put("pressure", source.getPressure());
        target.put("vibration", source.getVibration());
        if (includeTilt) {
            target.put("tilt", convertTiltToBinary(source.getTilt()));
        }
        target.put("speed", source.getSpeed());
    }

    private int convertTiltToBinary(String tiltValue) {
        return "tilt".equalsIgnoreCase(tiltValue) ? 1 : 0;
    }

    private TyreData getLatestTyreData() {
        List<TyreData> results = tyreDataRepository.findAll();
        return results.isEmpty() ? new TyreData() : results.get(results.size() - 1);
    }

    private TkphData getLatestTkphData() {
        List<TkphData> results = tkphDataRepository.findAll();
        return results.isEmpty() ? new TkphData() : results.get(results.size() - 1);
    }

    private List<TyreData> getLastNRecords(int n) {
        List<TyreData> results = tyreDataRepository.findAll();
        int startIndex = Math.max(0, results.size() - n);
        return results.subList(startIndex, results.size());
    }

    private void handleError(PredictionResult result, Exception e) {
        result.setAnomalyStatus("Error");
        result.setSafetyStatus("Error");
        result.setTyreLifePrediction(0);
        System.err.println("Prediction error: " + e.getMessage());
    }
}
