package com.mycompany.gameofchance.gamestatistics;


import com.mycompany.gameofchance.jedi.Jedi;
import com.mycompany.gameofchance.logger.Logger;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class HistoricalDataSet {
    private static final String PATH_OF_HISTORY_FILE = "src/main/resources/History.csv";
    private Logger logger;
    private List<HistoricalDataPoint> dataPoints = new ArrayList<>();
    private String args;


    public HistoricalDataSet(Logger logger) {
        this.logger = logger;
    }

    public HistoricalDataSet(Logger logger, String args) {
        this.logger = logger;
        this.args = args;
    }


    public int getSize() {
        return dataPoints.size();
    }

    public List<HistoricalDataPoint> DataPoints() {
        return dataPoints;
    }

    public void generate() throws Exception {
        boolean jediWonBefore = false;
        logger.info("Jedi participating: Dooku, Obi Wan Kenobi, Anakin Skywalker," +
                "Luke Skywalke, Leia, Ben Solo, Rey, Ezra Bridger, Grogu, Yoda");
        logger.info("Generating " + args + " data");
        for (int i = 0; i < Integer.parseInt(args); i++) {
            HistoricalDataPoint historicalDataPoint = new HistoricalDataPoint();
            if (dataPoints.size() > 0) {
                for (HistoricalDataPoint item : dataPoints) {
                    if (item.getJedi().getName().equalsIgnoreCase(historicalDataPoint.getJedi().getName())) {
                        item.getJedi().changeWonGame();
                        jediWonBefore = true;
                        logger.info("Generating 1 round of data");
                        logger.info("Racers: " + historicalDataPoint.getContestantOne() + ", " + historicalDataPoint.getContestantTwo());
                        logger.info("Win: " + historicalDataPoint.getJedi().getName());
                    }
                }
            }
            if (!jediWonBefore) {
                logger.info("Generating 1 round of data");
                logger.info("Racers: " + historicalDataPoint.getContestantOne() + ", " + historicalDataPoint.getContestantTwo());
                dataPoints.add(historicalDataPoint);
                logger.info("Win: " + historicalDataPoint.getJedi().getName());
            }
            jediWonBefore = false;
        }
    }


    public void load() throws Exception {
        dataPoints.clear();
        File file = new File(PATH_OF_HISTORY_FILE);
        if (file.exists()) {
            List<Jedi> jediList = GameData.read(PATH_OF_HISTORY_FILE);
            for (Jedi jedi : jediList) {
                dataPoints.add(new HistoricalDataPoint(jedi));
            }
        } else {
            logger.error(" Missing the History.csv!");
        }
    }

    public Logger getLogger() {
        return logger;
    }
}
