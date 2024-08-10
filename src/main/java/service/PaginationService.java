package service;

import persistence.entity.Match;
import persistence.repository.IMatchRepository;
import persistence.repository.MatchRepository;

import java.util.List;

public class PaginationService {
    private static final int NUMBER_MATCHES_IN_PAGE = 5;
    private final IMatchRepository matchRepository;

    public PaginationService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public PaginationService() {
        this.matchRepository = new MatchRepository();
    }

    public int getNumberOfPages(String name) {
        if (name == null || name.equals(""))
            return calculateNumberOfPages(matchRepository.getNumberOfMatches());
        else
            return calculateNumberOfPages(matchRepository.getNumberOfMatchesWherePlayer(name));
    }
    private int calculateNumberOfPages(int numberOfRows){
        return  (numberOfRows + (PaginationService.NUMBER_MATCHES_IN_PAGE - 1))
                / PaginationService.NUMBER_MATCHES_IN_PAGE;
    }

    public List<Match> getMatches(String name, int page) {
        if (name == null || name.equals(""))
            return matchRepository.getMatchesFromGiven(page != 1 ? page * NUMBER_MATCHES_IN_PAGE : 1,
                    NUMBER_MATCHES_IN_PAGE);
        else
            return matchRepository.getMatchesFromGivenWherePlayer(name, page != 1 ? page * NUMBER_MATCHES_IN_PAGE : 1,
                    NUMBER_MATCHES_IN_PAGE);
    }
}
