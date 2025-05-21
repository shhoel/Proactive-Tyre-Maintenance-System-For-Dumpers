package com.teamone.tyremonitoring.controller;

import com.teamone.tyremonitoring.model.TyreData;
import com.teamone.tyremonitoring.service.TyreDataService;
import com.teamone.tyremonitoring.service.LiveSensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

//import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/data")
@CrossOrigin(origins = "*")
public class TyreDataController {

    @Autowired
    private TyreDataService tyreDataService;

    @Autowired
    private LiveSensorService liveSensorService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @PostMapping
    public TyreData saveTyreData(@RequestBody TyreData tyreData) {
        TyreData savedData = tyreDataService.saveTyreData(tyreData);
        liveSensorService.updateLiveData(savedData); // ✅ Update cache directly
        messagingTemplate.convertAndSend("/topic/tyreData", savedData);
        return savedData;
    }

    @GetMapping("/latest")
    public TyreData getLatest() {
        TyreData liveData = liveSensorService.getLatestLiveData();
        return liveData != null ? liveData : tyreDataService.getAllTyreData().get(0);
    }

    @GetMapping
    public List<TyreData> getAllTyreData() {
        return tyreDataService.getAllTyreData();
    }
}