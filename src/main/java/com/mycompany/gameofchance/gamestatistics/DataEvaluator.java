package com.mycompany.gameofchance.gamestatistics;

import com.mycompany.gameofchance.logger.Logger;

public class DataEvaluator {
    private HistoricalDataSet historicalDataSet;
    private Logger logger;

    public DataEvaluator(HistoricalDataSet historicalDataSet, Logger logger) {
        this.historicalDataSet = historicalDataSet;
        this.logger = logger;
    }

    public Result run() {
        int countOfRounds = 0;
        String bestWinnerName = "";
        int max = 0;
        for (HistoricalDataPoint item : historicalDataSet.DataPoints()) {
            countOfRounds += item.getJedi().getWonGame();
            if (item.getJedi().getWonGame() > max) {
                max = item.getJedi().getWonGame();
            }
        }
        for (HistoricalDataPoint item : historicalDataSet.DataPoints()) {
            if (item.getJedi().getWonGame() == max) {
                bestWinnerName += item.getJedi().getName() + ", ";
            }
        }
        float average = (max / (float) countOfRounds) * 100;
        return new Result(countOfRounds, bestWinnerName, average, max);
    }
}
