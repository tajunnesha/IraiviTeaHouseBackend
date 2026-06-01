package com.example.iraivibackend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppProperties {

    @Value("${frontend.url}")
    private String frontendUrl;

    @Value("${backend.url}")
    private String backendUrl;

    public String getFrontendUrl() {
        return frontendUrl;
    }

    public String getBackendUrl() {
        return backendUrl;
    }
}