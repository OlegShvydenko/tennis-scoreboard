package model;

import util.Pair;

public class MatchScore {
    private Pair score;
    private Pair point;
    private Pair set;
    private Pair game;
    private Pair match;
    private boolean tieBreak;
    private boolean gameOver;

    public MatchScore() {
        this.score = new Pair();
        this.point = new Pair();
        this.set = new Pair();
        this.game = new Pair();
        this.match = new Pair();
        this.tieBreak = false;
        this.gameOver = false;
    }

    public Pair getScore() {
        return score;
    }

    public void setScore(Pair score) {
        this.score = score;
    }

    public Pair getPoint() {
        return point;
    }

    public void setPoint(Pair point) {
        this.point = point;
    }

    public Pair getSet() {
        return set;
    }

    public void setSet(Pair set) {
        this.set = set;
    }

    public Pair getGame() {
        return game;
    }

    public void setGame(Pair game) {
        this.game = game;
    }

    public Pair getMatch() {
        return match;
    }

    public void setMatch(Pair match) {
        this.match = match;
    }

    public boolean isTieBreak() {
        return tieBreak;
    }

    public void setTieBreak(boolean tieBreak) {
        this.tieBreak = tieBreak;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
}
