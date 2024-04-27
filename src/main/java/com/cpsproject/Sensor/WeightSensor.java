package com.cpsproject.Sensor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cpsproject.PhysicalEnvironment.Jar;
import com.cpsproject.ComputingLayer.ComputationComponent;

@Component
public class WeightSensor implements Sensor {
    private final Jar jar;
    private double previousWeight;
    @Autowired
    private ComputationComponent computationComponent;

    public WeightSensor(Jar jar) { 
        this.jar = jar;
        this.previousWeight = jar.getMaxCapacity();
    }

    @Scheduled(fixedRate = 200) // Run every 0.2 second
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
    	computationComponent.processSensorData(currentWeight);
        
    }
}
