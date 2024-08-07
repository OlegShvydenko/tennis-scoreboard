package persistence.repository;

import persistence.entity.Match;

import java.util.List;

public interface IMatchRepository {
    List<Match> getMatchesFromGiven(int from, int max);
    void addNewMatch(Match match);
    List<Match> getMatchesFromGivenWherePlayer(String name, int from, int max);
    int getNumberOfMatches();
    int getNumberOfMatchesWherePlayer(String name);
}
