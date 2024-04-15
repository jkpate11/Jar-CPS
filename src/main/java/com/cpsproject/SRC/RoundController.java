package com.cpsproject.SRC;

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

    // Method to start a new round
    public void startRound(double quantity, double weight) {
        if (!isRoundInProgress && (weight<maxCapacity || weight > 0)) {
            isRoundInProgress = true;
            currentRound = new Round();
            currentRound.setRoundId(currentRoundId);
            currentRound.setStartingWeight(weight);
            currentRoundId++;
            
        }
    }
    
    public void finishRound(double weight) {
        if (isRoundInProgress) {
            isRoundInProgress = false;
            currentRound.setEndingWeight(weight);
            currentRound.setRefillAmount(currentRound.getEndingWeight() - currentRound.getWeightChangeDueToRemoval()); 
            rounds.add(currentRound);
//            System.out.println();
//            System.out.println(currentRound.getRoundInfo());
//            System.out.println();
            roundInfo.convertAndSend("/topic/round", currentRound.getRoundInfoJSON());
            
        }
    }

    // Method to execute one round
    public void executeRound(double weight) {
    	if (isRoundInProgress) {
    		currentRound.setWeightChangeDueToRemoval(weight);
        	currentRound.setRemovedWeight(currentRound.getStartingWeight() - weight);
    	}
    }
}
