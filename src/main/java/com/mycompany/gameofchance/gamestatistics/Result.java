package com.mycompany.gameofchance.gamestatistics;

public class Result {
    private int numberOfSimulations;
    private String bestChoice;
    private float bestChoiceChance;
    private int countOfWonGame;

    public Result(int numberOfSimulations, String bestChoice, float bestChoiceChance, int countOfWonGame) {
        this.numberOfSimulations = numberOfSimulations;
        this.bestChoice = bestChoice;
        this.bestChoiceChance = bestChoiceChance;
        this.countOfWonGame = countOfWonGame;
    }

    public float getBestChoiceChance() {
        return bestChoiceChance;
    }

    public String getBestChoice() {
        return bestChoice;
    }

    public int getNumberOfSimulations() {
        return numberOfSimulations;
    }

    public int getCountOfWonGame() {
        return countOfWonGame;
    }
}
