package service;

import persistence.entity.Match;
import persistence.repository.IMatchRepository;
import persistence.repository.MatchRepository;
import service.model.MatchesStorage;
import util.WinnerPoint;

import java.util.UUID;

public class FinishedMatchesPersistenceService {
    private final IMatchRepository matchRepository;

    public FinishedMatchesPersistenceService() {
        this.matchRepository = new MatchRepository();
    }

    public FinishedMatchesPersistenceService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public void finishMatch(UUID uuid, WinnerPoint winnerPoint) {
        Match match = MatchesStorage.getByUUID(uuid);
        if (winnerPoint == WinnerPoint.FIRST) match.setWinner(match.getPlayerOne());
        else match.setWinner(match.getPlayerTwo());

        matchRepository.addNewMatch(MatchesStorage.getByUUID(uuid));

        MatchesStorage.removeByUUID(uuid);
    }
}
