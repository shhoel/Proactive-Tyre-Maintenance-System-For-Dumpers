package com.teamone.tyremonitoring.service;

import com.teamone.tyremonitoring.model.TyreData;
import com.teamone.tyremonitoring.repository.TyreDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
import java.util.List;
//import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Service
public class TyreDataService {

    @Autowired
    private TyreDataRepository tyreDataRepository;

    public TyreData saveTyreData(TyreData tyreData) {
        return tyreDataRepository.save(tyreData);
    }

    public List<TyreData> getAllTyreData() {
        return tyreDataRepository.findAll();
    }
    
    public List<TyreData> getDataByDateRange(LocalDateTime start, LocalDateTime end) {
        return tyreDataRepository.findByTimestampBetween(start, end);
    }
    
    public List<TyreData> getRecentData(int days) {
        LocalDateTime date = LocalDateTime.now().minusDays(days);
        return tyreDataRepository.findByTimestampAfter(date);
    }
}