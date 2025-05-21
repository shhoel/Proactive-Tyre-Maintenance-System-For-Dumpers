package com.teamone.tyremonitoring.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import java.time.Duration;

@Configuration
class RestTemplateConfig {  // Changed to package-private

    @Bean
    RestTemplate restTemplate(RestTemplateBuilder builder) {  // Removed public modifier
        ClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        ((SimpleClientHttpRequestFactory) factory).setConnectTimeout(Duration.ofSeconds(5));
        ((SimpleClientHttpRequestFactory) factory).setReadTimeout(Duration.ofSeconds(10));
        
        return builder
            .requestFactory(() -> factory)
            .build();
    }
}