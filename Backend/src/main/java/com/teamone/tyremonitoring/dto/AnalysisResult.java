package com.teamone.tyremonitoring.dto;

public class AnalysisResult {
    private Float tyreLifePrediction;
    private String safetyStatus;
    private String anomalyStatus;

    // Getters and Setters
    public Float getTyreLifePrediction() {
        return tyreLifePrediction;
    }

    public void setTyreLifePrediction(Float tyreLifePrediction) {
        this.tyreLifePrediction = tyreLifePrediction;
    }

    public String getSafetyStatus() {
        return safetyStatus;
    }

    public void setSafetyStatus(String safetyStatus) {
        this.safetyStatus = safetyStatus;
    }

    public String getAnomalyStatus() {
        return anomalyStatus;
    }

    public void setAnomalyStatus(String anomalyStatus) {
        this.anomalyStatus = anomalyStatus;
    }
}