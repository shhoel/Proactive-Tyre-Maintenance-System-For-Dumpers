// MlResponseDto.java
package com.teamone.tyremonitoring.dto;

import lombok.Data;

@Data
public class MlResponseDto {
    private float tyre_life_prediction; // From regression
    private String safety_status; // From classification
    private String anomaly_status; // From anomaly detection
    private MlRequestDto input_data; // Input data used for prediction
    private int records_used; // Number of records used for prediction
}