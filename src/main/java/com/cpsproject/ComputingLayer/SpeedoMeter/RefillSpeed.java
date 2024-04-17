package com.cpsproject.ComputingLayer.SpeedoMeter;

public enum RefillSpeed {
    SPEED_MAX(400.0),
    SPEED_HIGH(200.0),
    SPEED_MEDIUM(50.0),
    SPEED_LOW(10.0),
    SPEED_MIN(1.0);

    private final double refillRate;

    RefillSpeed(double refillRate) {
        this.refillRate = refillRate;
    }

    public double getRefillAmount() {
        return refillRate;
    }
}
