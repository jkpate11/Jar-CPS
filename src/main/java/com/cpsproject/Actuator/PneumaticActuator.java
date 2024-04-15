package com.cpsproject.Actuator;

import com.cpsproject.model.Jar;
import com.cpsproject.service.RefillSpeed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PneumaticActuator implements Actuator {
	private final Jar jar;

    @Autowired
    public PneumaticActuator(Jar jar) {
        this.jar = jar;
    }

    @Override
    public void refill(RefillSpeed speed) {
    	double refillAmount = speed.getRefillAmount();
    	jar.addWeight(refillAmount);
    }
}
