package com.cpsproject.SRC;

import com.google.gson.Gson;

public class Round {
    private int roundId;
    private double startingWeight;
    private double endingWeight;
    private double refillAmount;
    private double weightChangeDueToRemoval;
    private double removedWeight;

    public Round() {
        // Default constructor
    }

    // Getter and setter methods for round attributes
    public int getRoundId() {
        return roundId;
    }

    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    public double getStartingWeight() {
        return startingWeight;
    }

    public void setStartingWeight(double startingWeight) {
        this.startingWeight = startingWeight;
    }

    public double getEndingWeight() {
        return endingWeight;
    }

    public void setEndingWeight(double endingWeight) {
        this.endingWeight = endingWeight;
    }

    public double getRefillAmount() {
        return refillAmount;
    }

    public void setRefillAmount(double refillAmount) {
        this.refillAmount = refillAmount;
    }

    public double getWeightChangeDueToRemoval() {
        return weightChangeDueToRemoval;
    }

    public void setWeightChangeDueToRemoval(double weightChangeDueToRemoval) {
        this.weightChangeDueToRemoval = weightChangeDueToRemoval;
    }

    public double getRemovedWeight() {
        return removedWeight;
    }

    public void setRemovedWeight(double removedWeight) {
        this.removedWeight = removedWeight;
    }
    
    public String getRoundInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Round ID: ").append(roundId).append("\n");
        sb.append("Starting Weight: ").append(startingWeight).append("\n");
        sb.append("Removed Weight: ").append(removedWeight).append("\n");
        sb.append("Weight Change Due to Removal: ").append(weightChangeDueToRemoval).append("\n");
        sb.append("Refill Amount: ").append(refillAmount).append("\n");
        sb.append("Ending Weight: ").append(endingWeight).append("\n");
        return sb.toString();
    }
    

    public String getRoundInfoJSON() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }


}
