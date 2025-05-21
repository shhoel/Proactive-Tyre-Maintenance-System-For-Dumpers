// MlRequestDto.java
package com.teamone.tyremonitoring.dto;

import lombok.Data;

@Data
public class MlRequestDto {
    private float temperature;
    private float pressure;
    private float vibration;
    private int tilt; // 1 for tilt, 0 for no tilt
    private float speed;
    private Float tkph; // Optional, only for regression
    private boolean liveData; // Flag to indicate if it's live data
}