package com.teamone.tyremonitoring.controller;

import com.teamone.tyremonitoring.model.TyreData;
import com.teamone.tyremonitoring.service.TyreDataService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TyreDataControllerTest {

    @Mock
    private TyreDataService tyreDataService;

    @Mock
    private SimpMessagingTemplate messagingTemplate;

    @InjectMocks
    private TyreDataController tyreDataController;

    @Test
    void testSaveTyreData() {
        TyreData data = new TyreData();
        data.setTemperature(35.0f);

        when(tyreDataService.saveTyreData(any(TyreData.class))).thenReturn(data);
        doNothing().when(messagingTemplate).convertAndSend(anyString(), any(TyreData.class));

        TyreData response = tyreDataController.saveTyreData(data);

        assertNotNull(response);
        assertEquals(35.0f, response.getTemperature());

        // ✅ Ensure the correct topic is used
        verify(messagingTemplate, times(1)).convertAndSend("/topic/tyreData", data);
    }

    @Test
    void testGetAllTyreData() {
        TyreData data1 = new TyreData();
        TyreData data2 = new TyreData();
        when(tyreDataService.getAllTyreData()).thenReturn(Arrays.asList(data1, data2));

        List<TyreData> response = tyreDataController.getAllTyreData();
        assertEquals(2, response.size());
    }
}
