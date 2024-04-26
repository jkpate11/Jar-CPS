package com.cpsproject.ComputingLayer.SpeedoMeter;

public enum RefillSpeed {
    SPEED_MAX(400.0),
    SPEED_HIGH(280.0),
    SPEED_MEDIUM(160.0),
    SPEED_LOW(40.0),
    SPEED_MIN(20.0),
    STOP(0.0);

    private final double refillRate;

    RefillSpeed(double refillRate) {
        this.refillRate = refillRate;
    }

    public double getRefillAmount() {
        return refillRate;
    }
}
