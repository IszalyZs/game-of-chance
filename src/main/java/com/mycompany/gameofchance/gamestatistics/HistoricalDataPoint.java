package com.mycompany.gameofchance.gamestatistics;

import com.mycompany.gameofchance.jedi.Jedi;

public class HistoricalDataPoint {
    private static final String FILE = "src/main/resources/Jedi.csv";
    private Jedi jedi;
    private Jedi contestantOne;
    private Jedi contestantTwo;


    public HistoricalDataPoint() throws Exception {
        this.jedi = tournament();
        this.jedi.changeWonGame();
    }

    public HistoricalDataPoint(Jedi jedi) {
        this.jedi = jedi;
    }

    private Jedi contestant(Jedi contestantOne, Jedi contestantTwo) {
        Jedi first, second;
        if ((int) (Math.random() * 2) == 0) {
            first = contestantOne;
            second = contestantTwo;
        } else {
            first = contestantTwo;
            second = contestantOne;
        }
        return fight(first, second);
    }

    private Jedi fight(Jedi first, Jedi second) {
        while (first.isExist() && second.isExist()) {
            second.changeLife(first.getStrengthOfHit());
            if (second.isExist() == false) break;
            first.changeLife(second.getStrengthOfHit());
        }
        if (first.isExist()) return first;
        else if (second.isExist()) return second;
        return null;
    }

    private Jedi tournament() throws Exception {
        int contestantOne = 0, constentantTwo = 0;
        boolean haveTwoRandomIndex = false;
        while (!haveTwoRandomIndex) {
            contestantOne = (int) (Math.random() * GameData.read(FILE).size());
            constentantTwo = (int) (Math.random() * GameData.read(FILE).size());
            if (constentantTwo != contestantOne) haveTwoRandomIndex = true;
            if (contestantOne == 0 && constentantTwo == 0) throw new Exception("No Jedi in the file");
        }
        this.contestantOne = GameData.read(FILE).get(contestantOne);
        this.contestantTwo = GameData.read(FILE).get(constentantTwo);
        return contestant(this.contestantOne, this.contestantTwo);
    }

    public Jedi getJedi() {
        return jedi;
    }

    public Jedi getContestantOne() {
        return contestantOne;
    }

    public Jedi getContestantTwo() {
        return contestantTwo;
    }
}
