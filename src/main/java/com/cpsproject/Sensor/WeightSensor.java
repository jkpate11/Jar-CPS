package com.cpsproject.Sensor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cpsproject.model.Jar;
import com.cpsproject.service.ComputationComponent;

@Component
public class WeightSensor implements Sensor {
    private final Jar jar;
    private double previousWeight;
    @Autowired
    private ComputationComponent computationComponent;

    public WeightSensor(Jar jar) { 
        this.jar = jar;
        this.previousWeight = jar.getCurrentWeight();
    }

    @Scheduled(fixedRate = 200) // Run every 1 second
    public void monitor() {
    	
        double currentWeight = jar.getCurrentWeight();
        if (currentWeight != previousWeight) {
            //onWeightChange(currentWeight);
            previousWeight = currentWeight;
        }
        if (!jar.isFull()) {
        	
            sendToComputationalComponent(currentWeight);
        }
    }

    private void sendToComputationalComponent(double currentWeight) {
        // Logic to send data to computational component
    	computationComponent.refillJar(currentWeight);
        
    }
}
