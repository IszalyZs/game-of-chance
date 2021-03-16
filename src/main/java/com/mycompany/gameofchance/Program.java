package com.mycompany.gameofchance;

import com.mycompany.gameofchance.gamestatistics.*;
import com.mycompany.gameofchance.jedi.Jedi;


import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Program {
    private static final String PATH_OF_HISTORY_FILE = "src/main/resources/History.csv";
    private static final String PATH_OF_JEDI_FILE = "src/main/resources/Jedi.csv";
    private static HistoricalDataSet historicalDataSet = null;
    private static DecimalFormat decimalFormat = new DecimalFormat("#.##");
    private static ConsoleLogger consoleLogger = new ConsoleLogger();

    public static void main(String[] args) throws Exception {
        System.out.println("Let's start the game!");
        File file = new File(PATH_OF_JEDI_FILE);
        if (!file.exists()) {
            initialize();
        }
        if (args.length == 0) {
            historicalDataSet = new HistoricalDataSet(new ConsoleLogger());
            historicalDataSet.getLogger().info("Jedi participating: Dooku, Obi Wan Kenobi, Anakin Skywalker," +
                    "Luke Skywalke, Leia, Ben Solo, Rey, Ezra Bridger, Grogu, Yoda");
            printStatic(true);

        } else
            try {
                int firstArgument = Integer.parseInt(args[0]);
                if (firstArgument > 0) {
                    historicalDataSet = generateHistoricalDataSet(args[0]);
                    writeToCsv();
                    printStatic(false);
                } else if (firstArgument == 0) {
                    historicalDataSet = generateHistoricalDataSet(args[0]);
                } else {
                    consoleLogger.error("This is a wrong argument");
                }
            } catch (Exception ex) {
                consoleLogger.error(ex.getMessage());
            }
    }

    private static void printStatic(boolean loadDataFromDataPointsOrCsv) throws Exception {
        if (loadDataFromDataPointsOrCsv) {
            historicalDataSet.load();
            historicalDataSet.DataPoints();
        } else {
            historicalDataSet.DataPoints();
        }
        DataEvaluator dataEvaluator = new DataEvaluator(historicalDataSet, historicalDataSet.getLogger());
        Result result = dataEvaluator.run();
        historicalDataSet.getLogger().info("Winners count: " + result.getCountOfWonGame() + ", " +
                "simulations: " + result.getNumberOfSimulations() + ", " + "top Jedi: " + result.getBestChoice());
        historicalDataSet.getLogger().info("Number of simulations: " + result.getNumberOfSimulations());
        historicalDataSet.getLogger().info("Result: Jedi to bet on: " + result.getBestChoice() + "chance of winning: " +
                decimalFormat.format(result.getBestChoiceChance()) + "%");
    }

    public static HistoricalDataSet generateHistoricalDataSet(String args) throws Exception {
        HistoricalDataSet historicalDataSet = new HistoricalDataSet(consoleLogger, args);
        historicalDataSet.load();
        historicalDataSet.generate();
        return historicalDataSet;

    }

    private static void writeToCsv() throws Exception {
        File file = new File(PATH_OF_HISTORY_FILE);
        file.delete();
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<HistoricalDataPoint> historicalDataPointList = historicalDataSet.DataPoints();
        for (HistoricalDataPoint item : historicalDataPointList) {
            GameData.writeToCsv(item.getJedi(), PATH_OF_HISTORY_FILE);
        }
    }

    private static void initialize() throws Exception {
        List<Jedi> listOfJedi;
        listOfJedi = new ArrayList<>(Arrays.asList(
                new Jedi("Yoda"),
                new Jedi("Dooku"),
                new Jedi("Obi Wan Kenobi"),
                new Jedi("Anakin Skywalker"),
                new Jedi("Luke Skywalker"),
                new Jedi("Leia"),
                new Jedi("Ben Solo"),
                new Jedi("Rey"),
                new Jedi("Ezra Bridger"),
                new Jedi("Grogu")
        ));
        for (Jedi jedi : listOfJedi) {
            GameData.writeToCsv(jedi, PATH_OF_JEDI_FILE);
        }
    }

}
