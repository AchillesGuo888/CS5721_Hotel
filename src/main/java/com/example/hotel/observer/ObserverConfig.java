package com.example.hotel.observer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObserverConfig {

    @Bean
    public ObserverManager observerManager(AdminService adminService) {
        ObserverManager observerManager = new ObserverManager();
        observerManager.addObserver(adminService); // Register AdminService as an observer
        return observerManager;
    }
}
