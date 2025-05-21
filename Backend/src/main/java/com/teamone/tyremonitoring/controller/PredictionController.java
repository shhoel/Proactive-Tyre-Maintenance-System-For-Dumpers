package com.teamone.tyremonitoring.controller;

import com.teamone.tyremonitoring.model.PredictionResult;
import com.teamone.tyremonitoring.service.PredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/predict")
@CrossOrigin(origins = "*")
public class PredictionController {

    @Autowired
    private PredictionService predictionService;

    @GetMapping("/regression")
    public PredictionResult getRegressionPrediction() {
        return predictionService.getRegressionPrediction();
    }

    @GetMapping("/classification/live")
    public PredictionResult getClassificationLive() {
        return predictionService.getClassificationLive();
    }

    @GetMapping("/classification/stored")
    public PredictionResult getClassificationStored() {
        return predictionService.getClassificationStored();
    }

    @GetMapping("/anomaly/live")
    public PredictionResult getAnomalyLive() {
        return predictionService.getAnomalyLive();
    }

    @GetMapping("/anomaly/stored")
    public PredictionResult getAnomalyStored() {
        return predictionService.getAnomalyStored();
    }
}