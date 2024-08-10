package service;

import service.model.MatchScore;
import util.Pair;
import util.WinnerPoint;

public class MatchScoreCalculationService {
    private MatchScore matchScore;

    private final static int[] SCORE_COMPLIANCE = new int[]{0, 15, 30, 40};
    private final static int MIN_POINT_TO_WIN = 4;
    private final static int MIN_GAME_TO_WIN = 7;
    private final static int GAME_TO_TIE_BREAK = 6;
    private final static int DIFFERENCE_STATE_TO_WIN = 2;

    public MatchScoreCalculationService(MatchScore matchScore) {
        this.matchScore = matchScore;
    }

    public MatchScoreCalculationService() {

    }

    public void setMatchScore(MatchScore matchScore) {
        this.matchScore = matchScore;
    }

    public void updateMatchScore(WinnerPoint winnerPoint) {
        if (!matchScore.isTieBreak()) {
            updateGameScore(winnerPoint);

        } else {
            updateTieBreakScore(winnerPoint);

        }

        if (checkSet(matchScore.getSet())) {
            matchScore.setGameOver(true);
        }
    }

    private void updateGameScore(WinnerPoint winnerPoint) {
        changePair(matchScore.getPoint(), winnerPoint);

        if (checkState(matchScore.getPoint(), MIN_POINT_TO_WIN)) {
            changePair(matchScore.getGame(), winnerPoint);

            resetPair(matchScore.getPoint());
            resetPair(matchScore.getScore());
        }

        if (checkState(matchScore.getGame(), MIN_GAME_TO_WIN)) {
            changePair(matchScore.getSet(), winnerPoint);

            resetPair(matchScore.getGame());
        }

        setUpScore(matchScore.getPoint(), matchScore.getScore());

        matchScore.setTieBreak(checkTieBreak(matchScore.getGame()));
    }

    private void updateTieBreakScore(WinnerPoint winnerPoint) {
        //resetPair(matchScore.getPoint());
        changePair(matchScore.getPoint(), winnerPoint);

        if (checkState(matchScore.getGame(), MIN_GAME_TO_WIN)) {
            changePair(matchScore.getSet(), winnerPoint);

            resetPair(matchScore.getPoint());
            resetPair(matchScore.getGame());

            matchScore.setTieBreak(false);
        }
    }

    private void changePair(Pair<Integer> pair, WinnerPoint winnerPoint) {
        if (winnerPoint == WinnerPoint.FIRST) pair.first(pair.first() + 1);
        else pair.second(pair.second() + 1);
    }

    private void resetPair(Pair<Integer> pair) {
        pair.first(0);
        pair.second(0);
    }

    private boolean checkState(Pair<Integer> pair, int typeOfState) {
        return ((pair.first() >= typeOfState || pair.second() >= typeOfState) &&
                Math.abs(pair.first() - pair.second()) >= DIFFERENCE_STATE_TO_WIN);
    }

    private boolean checkSet(Pair<Integer> pair) {
        return pair.first() >= DIFFERENCE_STATE_TO_WIN || pair.second() >= DIFFERENCE_STATE_TO_WIN;
    }

    private boolean checkTieBreak(Pair<Integer> pair) {
        return (pair.first() == GAME_TO_TIE_BREAK) && (pair.second() == GAME_TO_TIE_BREAK);
    }

    private void setUpScore(Pair<Integer> point, Pair<Integer> score) {
        if (point.first() >= MIN_POINT_TO_WIN) score.first(SCORE_COMPLIANCE[MIN_POINT_TO_WIN - 1]);
        else score.first(SCORE_COMPLIANCE[point.first()]);
        if (point.second() >= MIN_POINT_TO_WIN) score.second(SCORE_COMPLIANCE[MIN_POINT_TO_WIN - 1]);
        else score.second(SCORE_COMPLIANCE[point.second()]);
    }

}
