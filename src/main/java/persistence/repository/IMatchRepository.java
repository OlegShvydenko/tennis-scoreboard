package persistence.repository;

import persistence.entity.Match;

import java.util.List;

public interface IMatchRepository {
    List<Match> getAllMatches();
    void saveMatch(Match match);
    List<Match> getMatchesByPlayerName();
}
