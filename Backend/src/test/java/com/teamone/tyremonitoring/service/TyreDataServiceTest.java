package com.teamone.tyremonitoring.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.teamone.tyremonitoring.model.TyreData;
import com.teamone.tyremonitoring.repository.TyreDataRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class TyreDataServiceTest {

    @Mock
    private TyreDataRepository tyreDataRepository;

    @InjectMocks
    private TyreDataService tyreDataService;

    @Test
    void testSaveTyreData() {
        TyreData data = new TyreData();
        data.setTemperature(30.5f);
        data.setPressure(8.2f);

        when(tyreDataRepository.save(any(TyreData.class))).thenReturn(data);

        TyreData savedData = tyreDataService.saveTyreData(data);
        assertNotNull(savedData);
        assertEquals(30.5f, savedData.getTemperature());
    }

    @Test
    void testGetAllTyreData() {
        TyreData data1 = new TyreData();
        TyreData data2 = new TyreData();

        when(tyreDataRepository.findAll()).thenReturn(Arrays.asList(data1, data2));

        List<TyreData> dataList = tyreDataService.getAllTyreData();
        assertEquals(2, dataList.size());
    }
}
