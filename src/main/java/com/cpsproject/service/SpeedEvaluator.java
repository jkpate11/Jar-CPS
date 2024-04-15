package com.cpsproject.service;

import org.springframework.stereotype.Component;

@Component
public class SpeedEvaluator {

	public RefillSpeed evaluateSpeed(double remainingCapacity) {
        if (remainingCapacity >= RefillSpeed.SPEED_MAX.getRefillAmount()) {
            return RefillSpeed.SPEED_MAX;
        } else if (remainingCapacity >= RefillSpeed.SPEED_HIGH.getRefillAmount()) {
            return RefillSpeed.SPEED_HIGH;
        } else if (remainingCapacity >= RefillSpeed.SPEED_MEDIUM.getRefillAmount()) {
            return RefillSpeed.SPEED_MEDIUM;
        } else {
            return RefillSpeed.SPEED_LOW;
        }
    }
    
    public RefillSpeed getMaxSpeed() {
        
       return RefillSpeed.SPEED_MAX;
    }
}
