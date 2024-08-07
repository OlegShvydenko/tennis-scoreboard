package service;

import persistence.entity.Match;
import persistence.repository.IMatchRepository;
import persistence.repository.MatchRepository;
import util.PointWinner;

import java.util.UUID;

public class FinishedMatchesPersistenceService {
    private final IMatchRepository matchRepository;

    public FinishedMatchesPersistenceService() {
        this.matchRepository = new MatchRepository();
    }

    public FinishedMatchesPersistenceService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public void finishMatch(UUID uuid, PointWinner pointWinner) {
        setWinner(uuid, pointWinner);
        saveMatchToDB(uuid);
        deleteFinishedMatch(uuid);
    }

    private void setWinner(UUID uuid, PointWinner pointWinner) {
        Match match = MatchesStorageService.getMatchByUUID(uuid);
        if (pointWinner == PointWinner.FIRST) match.setWinner(match.getPlayerOne());
        else match.setWinner(match.getPlayerTwo());
    }

    private void saveMatchToDB(UUID uuid) {
        matchRepository.addNewMatch(MatchesStorageService.getMatchByUUID(uuid));
    }

    private void deleteFinishedMatch(UUID uuid) {
        MatchesStorageService.removeMatchByUUID(uuid);
    }
}
