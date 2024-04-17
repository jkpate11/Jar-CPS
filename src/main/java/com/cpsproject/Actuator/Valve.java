package com.cpsproject.Actuator;

import com.cpsproject.PhysicalEnvironment.Jar;
import com.cpsproject.ComputingLayer.SpeedoMeter.RefillSpeed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Valve implements Actuator {
	private final Jar jar;

    @Autowired
    public Valve(Jar jar) {
        this.jar = jar;
    }

    @Override
    public void refill(RefillSpeed speed) {
    	double refillAmount = speed.getRefillAmount();
    	jar.addWeight(refillAmount);
    }
}
