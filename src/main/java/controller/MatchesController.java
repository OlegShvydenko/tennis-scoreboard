package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.PaginationService;
import persistence.entity.Match;
import persistence.repository.IMatchRepository;
import persistence.repository.IPlayerRepository;
import persistence.repository.MatchRepository;
import persistence.repository.PlayerRepository;
import util.MatchesBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@WebServlet("/matches")
public class MatchesController extends HttpServlet {
    private final IMatchRepository matchRepository;
    private final IPlayerRepository playerRepository;

    public MatchesController(MatchRepository matchRepository, IPlayerRepository playerRepository) {
        this.matchRepository = matchRepository;
        this.playerRepository = playerRepository;
    }

    public MatchesController() {
        this.matchRepository = new MatchRepository();
        this.playerRepository = new PlayerRepository();
        new MatchesBuilder();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = Integer.parseInt(req.getParameter("page"));
        String name = req.getParameter("filter_by_player_name");

        PaginationService paginationService = new PaginationService();

        if (!Objects.equals(name, "") && playerRepository.getPlayerByName(name) == null && name != null){
            name = "";
            req.setAttribute("message", "Игрок с таким именем не учавствовал в матчах.");
        }
        int numberOfPages = paginationService.getNumberOfPages(name);
        List<Match> matches = paginationService.getMatches(name, page);

        req.setAttribute("numberOfPages", numberOfPages);
        req.setAttribute("matches", matches);
        req.setAttribute("filter_by_player_name", name);
        req.getRequestDispatcher("matches.jsp").forward(req, resp);
    }
}
