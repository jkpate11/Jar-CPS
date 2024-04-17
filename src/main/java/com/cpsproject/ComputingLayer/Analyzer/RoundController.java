package com.cpsproject.ComputingLayer.Analyzer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class RoundController {
    private List<Round> rounds;
    private Round currentRound;
    private int currentRoundId;
    public boolean isRoundInProgress;
    private double maxCapacity;
    private final SimpMessagingTemplate roundInfo;

    @Autowired
    public RoundController(double maxCapacity, SimpMessagingTemplate roundInfo) {
		this.isRoundInProgress = false;
        this.rounds = new ArrayList<>();
        currentRoundId = 1;
        this.maxCapacity = maxCapacity;
        this.roundInfo = roundInfo;
    }


    public void startRound(double quantity,double startingWeight,double currentWeight) {
        if (!isRoundInProgress && (startingWeight<maxCapacity || startingWeight > 0)) {
            isRoundInProgress = true;
            currentRound = new Round();
            currentRound.setRoundId(currentRoundId);
            currentRound.setStartingWeight(startingWeight);
            currentRound.setWeightChangeDueToRemoval(currentWeight);
        	currentRound.setRemovedWeight(quantity);
            currentRoundId++;
            
        }
    }
    
    public void finishRound(double weight) {
        if (isRoundInProgress) {
            isRoundInProgress = false;
            currentRound.setRefillAmount(weight);
            currentRound.setEndingWeight(weight + currentRound.getWeightChangeDueToRemoval()); 
            rounds.add(currentRound);
//            System.out.println();
//            System.out.println(currentRound.getRoundInfo());
//            System.out.println();
            roundInfo.convertAndSend("/topic/round", currentRound.getRoundInfoJSON());
            
            try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
            isRoundInProgress = false;
            
        }
    }


}
