package service;

import model.MatchScore;
import persistence.entity.Match;
import util.Pair;
import util.PointWinner;

import java.util.UUID;

public class MatchScoreCalculationService {
    private final MatchScore matchScore;

    public MatchScoreCalculationService(MatchScore matchScore) {
        this.matchScore = matchScore;
    }

    public void updateMatchScore(PointWinner pointWinner) {
        if (!matchScore.isTieBreak()) {
            changePair(matchScore.getPoint(), pointWinner);
            if (checkPoint(matchScore.getPoint())) {
                changePair(matchScore.getGame(), pointWinner);
                resetPair(matchScore.getPoint());
                resetPair(matchScore.getScore());
            }
            matchScore.setTieBreak(checkTieBreak(matchScore.getGame()));
            if (checkGame(matchScore.getGame())) {
                changePair(matchScore.getSet(), pointWinner);
                resetPair(matchScore.getGame());
            }

        }
        else {
            resetPair(matchScore.getPoint());
            changePair(matchScore.getPoint(), pointWinner);
            if (checkGame(matchScore.getScore())){
                changePair(matchScore.getSet(), pointWinner);
                resetPair(matchScore.getPoint());
                resetPair(matchScore.getGame());
                matchScore.setTieBreak(false);
            }
        }
        if (checkSet(matchScore.getSet())) {
            matchScore.setGameOver(true);
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

    private void updateScore(Match match) {

    }
}
