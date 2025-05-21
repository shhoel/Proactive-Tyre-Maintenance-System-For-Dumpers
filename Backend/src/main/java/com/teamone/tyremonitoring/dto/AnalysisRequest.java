package com.teamone.tyremonitoring.dto;

public class AnalysisRequest {
    private boolean useLiveData;
    private int hoursOfHistory;
    private String analysisType;

    // Getters and Setters
    public boolean isUseLiveData() {
        return useLiveData;
    }

    public void setUseLiveData(boolean useLiveData) {
        this.useLiveData = useLiveData;
    }

    public int getHoursOfHistory() {
        return hoursOfHistory;
    }

    public void setHoursOfHistory(int hoursOfHistory) {
        this.hoursOfHistory = hoursOfHistory;
    }

    public String getAnalysisType() {
        return analysisType;
    }

    public void setAnalysisType(String analysisType) {
        this.analysisType = analysisType;
    }
}