package service;


import service.model.MatchScore;
import org.junit.jupiter.api.Test;
import util.Pair;
import util.WinnerPoint;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MatchScoreCalculationServiceTest {
  @Test
    public void testNotWin(){
      MatchScore matchScore = new MatchScore();
      matchScore.setPoint(new Pair(3, 3));
      MatchScoreCalculationService matchScoreCalculationService = new MatchScoreCalculationService(matchScore);
      matchScoreCalculationService.updateMatchScore(WinnerPoint.FIRST);
      assertEquals(matchScore.getGame().first(), 0);
  }
  @Test
  public void testWin(){
      MatchScore matchScore = new MatchScore();
      matchScore.setPoint(new Pair(3, 0));
      MatchScoreCalculationService matchScoreCalculationService = new MatchScoreCalculationService(matchScore);
      matchScoreCalculationService.updateMatchScore(WinnerPoint.FIRST);
      assertEquals(matchScore.getGame().first(), 1);
  }
  @Test
  public void testTieBreak(){
      MatchScore matchScore = new MatchScore();
      matchScore.setPoint(new Pair(3, 0));
      matchScore.setGame(new Pair(5, 6));
      MatchScoreCalculationService matchScoreCalculationService = new MatchScoreCalculationService(matchScore);
      matchScoreCalculationService.updateMatchScore(WinnerPoint.FIRST);
      assertTrue(matchScore.isTieBreak());
  }
}
