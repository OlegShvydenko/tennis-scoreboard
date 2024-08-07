package service;

import model.MatchScore;
import util.Pair;
import util.PointWinner;

// TODO разделить код по логике пробелами
public class MatchScoreCalculationService {
    private static final int[] SCORE_COMPLIANCE = new int[]{0, 15, 30, 40};

    private final MatchScore matchScore;

    public MatchScoreCalculationService(MatchScore matchScore) {
        this.matchScore = matchScore;
    }

    public void updateMatchScore(PointWinner pointWinner) {
        if (!matchScore.isTieBreak()) {
            updateGameScore(pointWinner);
        } else {
            updateTieBreakScore(pointWinner);
        }
        if (checkSet(matchScore.getSet())) {
            matchScore.setGameOver(true);
        }
    }

    private void updateGameScore(PointWinner pointWinner) {
        changePair(matchScore.getPoint(), pointWinner);
        if (checkPoint(matchScore.getPoint())) {
            changePair(matchScore.getGame(), pointWinner);
            resetPair(matchScore.getPoint());
            resetPair(matchScore.getScore());
        }
        if (checkGame(matchScore.getGame())) {
            changePair(matchScore.getSet(), pointWinner);
            resetPair(matchScore.getGame());
        }
        setUpScore(matchScore.getPoint(), matchScore.getScore());
        matchScore.setTieBreak(checkTieBreak(matchScore.getGame()));
    }

    private void updateTieBreakScore(PointWinner pointWinner) {
        //resetPair(matchScore.getPoint());
        changePair(matchScore.getPoint(), pointWinner);
        if (checkGame(matchScore.getPoint())) {
            changePair(matchScore.getSet(), pointWinner);
            resetPair(matchScore.getPoint());
            resetPair(matchScore.getGame());
            matchScore.setTieBreak(false);
        }
    }

    private void changePair(Pair pair, PointWinner pointWinner) {
        if (pointWinner == PointWinner.FIRST) pair.first(pair.first() + 1);
        else pair.second(pair.second() + 1);
    }

    private void resetPair(Pair pair) {
        pair.first(0);
        pair.second(0);
    }
    // TODO вынести в общий метод, добавить новый аргумент Enum с value и назвать их по смыслу, переименовать общий метод на checkState
    private boolean checkPoint(Pair pair) {
        return ((pair.first() > 3 || pair.second() > 3) && Math.abs(pair.first() - pair.second()) > 1);
    }

    private boolean checkGame(Pair pair) {
        return ((pair.first() > 6 || pair.second() > 6) && Math.abs(pair.first() - pair.second()) > 1);
    }

    private boolean checkSet(Pair pair) {
        return pair.first() > 1 || pair.second() > 1;
    }

    private boolean checkTieBreak(Pair pair) {
        return (pair.first() == 6) && (pair.second() == 6);
    }

    private void setUpScore(Pair point, Pair score) {
        if (point.first() > 3) score.first(40);
        else score.first(SCORE_COMPLIANCE[point.first()]);
        if (point.second() > 3) score.second(40);
        else score.second(SCORE_COMPLIANCE[point.second()]);
    }

}
