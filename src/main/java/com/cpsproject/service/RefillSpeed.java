package com.cpsproject.service;

public enum RefillSpeed {
    SPEED_MAX(200.0),
    SPEED_HIGH(50.0),
    SPEED_MEDIUM(10.0),
    SPEED_LOW(1.0);

    private final double refillRate;

    RefillSpeed(double refillRate) {
        this.refillRate = refillRate;
    }

    public double getRefillAmount() {
        return refillRate;
    }
}
