package com.teamone.tyremonitoring.model;

public class PredictionResult {
    private String anomalyStatus;
    private String safetyStatus;
    private double tyreLifePrediction;

    // Getters and Setters
    public String getAnomalyStatus() { return anomalyStatus; }
    public void setAnomalyStatus(String anomalyStatus) { this.anomalyStatus = anomalyStatus; }

    public String getSafetyStatus() { return safetyStatus; }
    public void setSafetyStatus(String safetyStatus) { this.safetyStatus = safetyStatus; }

    public double getTyreLifePrediction() { return tyreLifePrediction; }
    public void setTyreLifePrediction(double tyreLifePrediction) { this.tyreLifePrediction = tyreLifePrediction; }
}