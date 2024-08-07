package model;

import persistence.entity.Match;
import persistence.repository.IMatchRepository;
import persistence.repository.MatchRepository;

import java.util.List;

public class PaginationService {
    private static final int numberMatchesInPage = 5;
    private final IMatchRepository matchRepository;

    public PaginationService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public PaginationService() {
        this.matchRepository = new MatchRepository();
    }

    public int getNumberOfPages(String name) {
        if (name == null || name.equals(""))
            return divide(matchRepository.getNumberOfMatches(), numberMatchesInPage);
        else
            return divide(matchRepository.getNumberOfMatchesWherePlayer(name), numberMatchesInPage);
    }
    private static int divide(int a, int b){
        return  (a + (b - 1) ) / b;
    }

    public List<Match> getMatches(String name, int page) {
        if (name == null || name.equals(""))
            return matchRepository.getMatchesFromGiven(page != 1 ? page * numberMatchesInPage : 1, numberMatchesInPage);
        else
            return matchRepository.getMatchesFromGivenWherePlayer
                    (name,
                    page != 1 ? page * numberMatchesInPage : 1,
                    numberMatchesInPage);
    }
}
