package com.cpsproject.PhysicalEnvironment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Component
@RestController
public class Jar {

	private double currentWeight;
    private double maxCapacity;
    private final SimpMessagingTemplate weightInfo;

    @Autowired
    public Jar(double maxCapacity, SimpMessagingTemplate weightInfo) {
    	this.maxCapacity = maxCapacity;
        this.currentWeight = maxCapacity; // Initially full
        this.weightInfo = weightInfo;
        getCurrentWeightInfo();
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
        return currentWeight == maxCapacity;
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
        // Check if the jar is now full
        if (isOverflow()) {
            // Perform any necessary actions when the jar is full
            System.out.println("Jar is Overflowing");
        }
    }
    
    
    @PostMapping("/dataFromFile")
    public void readInputFromFile(@RequestBody String filePath) {
    	//filePath = "src/main/resources/test1.csv";
    	//System.out.println(filePath);
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
            	if (line.trim().startsWith("#")) {
                    continue;
                }
                double quantity = Double.parseDouble(line.trim());
                refillJar(quantity);
                Thread.sleep(1000);
            }
            
            while (true) {
                refillJar(0);
                Thread.sleep(1000);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    @PostMapping("/refill")
    public double refillJar(@RequestBody double quantity) {
    	removeWeight(quantity);
        return currentWeight;
    }
    
    @PostMapping("/addExtraWeight")
    public void addExtraWeight(@RequestBody double quantity) {
    	addWeight(quantity);
    }

}
