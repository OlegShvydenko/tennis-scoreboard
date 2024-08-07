package model;

import persistence.entity.Match;
import persistence.repository.IMatchRepository;
import persistence.repository.MatchRepository;

import java.util.List;
// TODO переместить в пакет service/объединить пакеты со структурой service/model
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
        if (name == null || name.isEmpty())
            return divide(matchRepository.getNumberOfMatches(), NUMBER_MATCHES_IN_PAGE);
        else
            return divide(matchRepository.getNumberOfMatchesWherePlayer(name), NUMBER_MATCHES_IN_PAGE);
    }
    // TODO сделать название более конкретным и убрать второй параметр b, тк этот метод используется только со значением
    //  NUMBER_MATCHES_IN_PAGE, если понадобится метод для деления именно двух чисел, а не a на NUMBER_MATCHES_IN_PAGE,
    //  то написать новый с более конкретным названием и также переиспользовать его в этом месте
    private int divide(int a, int b){
        return  (a + (b - 1) ) / b;
    }

    public List<Match> getMatches(String name, int page) {
        if (name == null || name.isEmpty())
            return matchRepository.getMatchesFromGiven(page != 1 ? page * NUMBER_MATCHES_IN_PAGE : 1, NUMBER_MATCHES_IN_PAGE);
        else
            return matchRepository.getMatchesFromGivenWherePlayer
                    (name,
                    page != 1 ? page * NUMBER_MATCHES_IN_PAGE : 1,
                            NUMBER_MATCHES_IN_PAGE);
    }
}
