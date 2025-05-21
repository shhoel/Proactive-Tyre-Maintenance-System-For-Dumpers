package com.teamone.tyremonitoring.service;

import com.teamone.tyremonitoring.model.TyreData;
import org.springframework.stereotype.Service;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class LiveSensorService {
    private final AtomicReference<TyreData> latestLiveData = new AtomicReference<>();

    public void updateLiveData(TyreData data) {
        latestLiveData.set(data);
    }

    public TyreData getLatestLiveData() {
        return latestLiveData.get();
    }
}