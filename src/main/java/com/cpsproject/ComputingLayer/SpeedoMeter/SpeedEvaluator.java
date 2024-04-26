package com.cpsproject.ComputingLayer.SpeedoMeter;

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
        } else if (remainingCapacity >= RefillSpeed.SPEED_LOW.getRefillAmount()) {
            return RefillSpeed.SPEED_LOW;
        } else if (remainingCapacity >= RefillSpeed.SPEED_MIN.getRefillAmount()) {
            return RefillSpeed.SPEED_MIN;
        } else  {
            return RefillSpeed.STOP;
        }
    }
    
    public RefillSpeed getMaxSpeed() {
        
       return RefillSpeed.SPEED_MAX;
    }
}
