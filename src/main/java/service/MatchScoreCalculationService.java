package service;

import persistence.entity.Match;
import util.Pair;

import java.util.UUID;

public class MatchScoreCalculationService {
    public void updateScore(UUID uuid, String player){
        Match match = MatchesStorageService.getMatchByUUID(uuid);
        Pair point = match.getMatchScore().getPoint();
        if(player == "first") point.first(point.first() + 1);
        else point.second(point.second() + 1);
    }

}
