// Updated AnalysisController.java
package com.teamone.tyremonitoring.controller;

import com.teamone.tyremonitoring.dto.MlRequestDto;
import com.teamone.tyremonitoring.dto.MlResponseDto;
import com.teamone.tyremonitoring.model.TkphData;
import com.teamone.tyremonitoring.model.TyreData;
import com.teamone.tyremonitoring.service.AnalysisService;
import com.teamone.tyremonitoring.service.TkphDataService;
import com.teamone.tyremonitoring.service.TyreDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/analysis")
@CrossOrigin("*")
public class AnalysisController {

    @Autowired
    private TyreDataService tyreDataService;

    @Autowired
    private TkphDataService tkphDataService;

    @Autowired
    private AnalysisService analysisService;

    // Regression endpoint - uses last stored data
    @GetMapping("/regression")
    public ResponseEntity<?> getRegressionPrediction() {
        try {
            // Get last tyre data
            List<TyreData> tyreDataList = tyreDataService.getAllTyreData();
            if (tyreDataList.isEmpty()) {
                return ResponseEntity.badRequest().body("No tyre data available");
            }
            TyreData lastTyreData = tyreDataList.get(tyreDataList.size() - 1);

            // Get last TKPH data
            List<TkphData> tkphDataList = tkphDataService.getAll();
            if (tkphDataList.isEmpty()) {
                return ResponseEntity.badRequest().body("No TKPH data available");
            }
            TkphData lastTkphData = tkphDataList.get(tkphDataList.size() - 1);

            // Prepare request for Flask
            MlRequestDto request = new MlRequestDto();
            request.setTemperature(lastTyreData.getTemperature());
            request.setPressure(lastTyreData.getPressure());
            request.setVibration(lastTyreData.getVibration()); // Now accepts float
            request.setTilt(lastTyreData.getTilt().equalsIgnoreCase("tilt") ? 1 : 0);
            request.setSpeed(lastTyreData.getSpeed());
            request.setTkph(lastTkphData.getTkph());

            // Call Flask API
            MlResponseDto response = analysisService.callFlaskApi(request);

            // Add input data to response
            response.setInput_data(request);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error during prediction: " + e.getMessage());
        }
    }

    // ... rest of the controller methods remain the same ...
}