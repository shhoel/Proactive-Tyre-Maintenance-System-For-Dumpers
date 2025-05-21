package com.teamone.tyremonitoring.service;

import com.teamone.tyremonitoring.dto.MlRequestDto;
import com.teamone.tyremonitoring.dto.MlResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AnalysisService {

    @Autowired
    private RestTemplate restTemplate;

    private final String flaskApiUrl = "http://localhost:5000/predict";

    public MlResponseDto callFlaskApi(MlRequestDto request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<MlRequestDto> entity = new HttpEntity<>(request, headers);

        return restTemplate.postForObject(flaskApiUrl, entity, MlResponseDto.class);
    }
}