package com.cpsproject.service;

import com.cpsproject.Actuator.Actuator;
import com.cpsproject.SRC.RoundController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComputationComponent {

	private double maxCapacity;
    private final Actuator actuator;
    private final RoundController roundController;
    private final SpeedEvaluator speedEvaluator;
    

    @Autowired
    public ComputationComponent(double maxCapacity, Actuator actuator, double maxRefillLimit, RoundController roundController, SpeedEvaluator speedEvaluator) {
        this.maxCapacity = maxCapacity;
    	this.actuator = actuator;
        this.roundController = roundController;
        this.speedEvaluator = speedEvaluator;
    }

    public void refillJar(double currentWeight) {
    	if (roundController.isRoundInProgress) {
	
	        if (currentWeight < maxCapacity) {
	            double remainingCapacity = maxCapacity - currentWeight;
	            
	            RefillSpeed speed = speedEvaluator.evaluateSpeed(remainingCapacity);
	
	            actuator.refill(speed);
	            
	        }
    	}
    }
    
}
