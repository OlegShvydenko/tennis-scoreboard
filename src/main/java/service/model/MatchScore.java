package service.model;

import util.Pair;

public class MatchScore {
    private Pair<Integer> score;
    private Pair<Integer> point;
    private Pair<Integer> set;
    private Pair<Integer> game;
    private boolean tieBreak;
    private boolean gameOver;

    public MatchScore() {
        this.score = new Pair<>(0, 0);
        this.point = new Pair<>(0, 0);
        this.set = new Pair<>(0, 0);
        this.game = new Pair<>(0, 0);
        this.tieBreak = false;
        this.gameOver = false;
    }

    public Pair<Integer> getScore() {
        return score;
    }

    public void setScore(Pair<Integer> score) {
        this.score = score;
    }

    public Pair<Integer> getPoint() {
        return point;
    }

    public void setPoint(Pair<Integer> point) {
        this.point = point;
    }

    public Pair<Integer> getSet() {
        return set;
    }

    public void setSet(Pair<Integer> set) {
        this.set = set;
    }

    public Pair<Integer> getGame() {
        return game;
    }

    public void setGame(Pair<Integer> game) {
        this.game = game;
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
