package com.cpsproject.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cpsproject.SRC.RoundController;

@Component
@RestController
public class Jar {

	private double currentWeight;
    private double maxCapacity;
    private final SimpMessagingTemplate weightInfo;
    private final RoundController roundController;

    @Autowired
    public Jar(double maxCapacity, SimpMessagingTemplate weightInfo, RoundController roundController) {
    	this.maxCapacity = maxCapacity;
        this.currentWeight = maxCapacity; // Initially full
        this.weightInfo = weightInfo;
        getCurrentWeightInfo();
        this.roundController = roundController;
    }

    public double getCurrentWeight() {
        return currentWeight;
    }
    
    @Scheduled(fixedRate = 100)
    public void getCurrentWeightInfo() {
    	weightInfo.convertAndSend("/topic/weight", currentWeight);
    }
    
    public double getMaxCapacity() {
        return maxCapacity;
    }

    public boolean isFull() {
        return currentWeight >= maxCapacity;
    }
    
    public boolean isOverflow() {
        return currentWeight > maxCapacity;
    }
    
    public void removeWeight(double weight) {
    	if (currentWeight>=weight) {
    		currentWeight -= weight;
    	}
    }

  
    public void addWeight(double weight) {
        // Add the weight to the current weight
        currentWeight += weight;
        roundController.finishRound(currentWeight);
        // Check if the jar is now full
        if (isOverflow()) {
            // Perform any necessary actions when the jar is full
            System.out.println("Jar is Overflowing");
        }
    }
    
    @PostMapping("/refill")
    public double refillJar(@RequestBody double quantity) {
    	roundController.startRound(quantity, currentWeight);
    	removeWeight(quantity);
    	roundController.executeRound(currentWeight);
        return currentWeight;
    }

}
