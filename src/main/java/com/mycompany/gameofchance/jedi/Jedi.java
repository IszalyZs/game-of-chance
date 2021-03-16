package com.mycompany.gameofchance.jedi;

public class Jedi implements Comparable<Jedi> {
    private String name;
    private int strengthOfHit;
    private boolean exist;
    private int life;
    private int wonGame;

    public Jedi() {
        this.exist = true;
    }

    public Jedi(String name) {
        this.name = name;
        this.strengthOfHit = (int) ((Math.random() * 11) + 10);
        this.exist = true;
        this.life = (int) ((Math.random() * 31) + 100);
    }

    public void changeLife(int strengthOfHit) {
        if (getLife() - strengthOfHit <= 0) {
            setExist(false);
        } else this.life -= strengthOfHit;
    }

    public void changeWonGame() {
        this.wonGame++;
    }

    public int getWonGame() {
        return wonGame;
    }

    public String getName() {
        return name;
    }

    public int getStrengthOfHit() {
        return strengthOfHit;
    }

    public boolean isExist() {
        return exist;
    }

    public int getLife() {
        return life;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStrengthOfHit(int strengthOfHit) {
        this.strengthOfHit = strengthOfHit;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void setWonGame(int wonGame) {
        this.wonGame = wonGame;
    }


    @Override
    public int compareTo(Jedi o) {
        return this.getName().compareTo(o.getName());
    }

    @Override
    public String toString() {
        return name;
    }
}
