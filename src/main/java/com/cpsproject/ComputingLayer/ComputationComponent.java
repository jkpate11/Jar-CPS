package com.cpsproject.ComputingLayer;

import com.cpsproject.Actuator.Actuator;
import com.cpsproject.ComputingLayer.SpeedoMeter.RefillSpeed;
import com.cpsproject.ComputingLayer.SpeedoMeter.SpeedEvaluator;
import com.cpsproject.ComputingLayer.Analyzer.RoundController;
import com.cpsproject.ComputingLayer.Service.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComputationComponent {

	private double maxCapacity;
    private final Actuator actuator;
    private final RoundController roundController;
    private final SpeedEvaluator speedEvaluator;
    private double previousWeight;
    private boolean sendEmail;
    
    @Autowired
	private EmailService emailService;
    

    @Autowired
    public ComputationComponent(double maxCapacity, Actuator actuator, double maxRefillLimit, RoundController roundController, SpeedEvaluator speedEvaluator) {
        this.maxCapacity = maxCapacity;
    	this.actuator = actuator;
        this.roundController = roundController;
        this.speedEvaluator = speedEvaluator;
        previousWeight = this.maxCapacity;
        sendEmail = false;
    }
    
    public void processSensorData(double currentWeight) {
    	
    	if(currentWeight>maxCapacity) {
    		notifyUser();
    		
    	}else {

    		double quantity = previousWeight - currentWeight;
        	
        	roundController.startRound(quantity, previousWeight, currentWeight);
        	refillJar(currentWeight); 		
    	}
    	
    	
    }
    
    public void notifyUser() {
    	if(!sendEmail) {
	    	System.out.println("Sending mail");
	        String toEmail = "jinesh1077@gmail.com";
	        String subject = "Jar is Malfunctioned";
	        String message = "Jar is not working properly" ;
	        emailService.sendNotificationEmail(toEmail, subject, message);
	        System.out.println("Notification email sent to: " + toEmail);
	        sendEmail=true;
    	}
     
    }

    public void refillJar(double currentWeight) {
    	
    	if (roundController.isRoundInProgress) {
    		System.out.println(currentWeight);
	        double remainingCapacity = maxCapacity - currentWeight;
	            
	        RefillSpeed speed = speedEvaluator.evaluateSpeed(remainingCapacity);
	
	        actuator.refill(speed);
	        previousWeight=currentWeight+speed.getRefillAmount();

	        roundController.finishRound(speed.getRefillAmount());
    	}
    }
    
}
