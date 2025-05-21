package com.teamone.tyremonitoring.service;

import com.teamone.tyremonitoring.model.TkphData;
import com.teamone.tyremonitoring.repository.TkphDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TkphDataService {

    @Autowired
    private TkphDataRepository tkphDataRepository;

    public TkphData save(TkphData data) {
        return tkphDataRepository.save(data);
    }

    public List<TkphData> getAll() {
        return tkphDataRepository.findAll();
    }
    
}
