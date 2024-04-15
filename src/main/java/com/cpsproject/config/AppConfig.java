package com.cpsproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;


@Configuration
@EnableScheduling
public class AppConfig {

    
    @Bean
    public double maxCapacity() {
        return 1000.0; // Adjust as needed
    }

    
}
