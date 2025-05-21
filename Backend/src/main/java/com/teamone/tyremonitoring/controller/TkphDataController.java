package com.teamone.tyremonitoring.controller;

import com.teamone.tyremonitoring.model.TkphData;
import com.teamone.tyremonitoring.service.TkphDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tkph")
@CrossOrigin("*")
public class TkphDataController {

    @Autowired
    private TkphDataService tkphDataService;

    // POST endpoint to save TKPH data
    @PostMapping("/save")
    public TkphData save(@RequestBody TkphData data) {
        return tkphDataService.save(data);
    }

    // GET endpoint to retrieve all TKPH records
    @GetMapping("/all")
    public List<TkphData> getAll() {
        return tkphDataService.getAll();
    }
}
