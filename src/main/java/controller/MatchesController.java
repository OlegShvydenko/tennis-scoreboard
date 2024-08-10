package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import persistence.entity.Match;
import service.PaginationService;
import util.ExceptionMessage;
import util.MatchesBuilder;
import util.Validator;

import java.io.IOException;
import java.util.List;

@WebServlet("/matches")
public class MatchesController extends HttpServlet {
    private final PaginationService paginationService;
    private final Validator validator;

    public MatchesController( PaginationService paginationService, Validator validator) {
        this.paginationService = paginationService;
        this.validator = validator;
        new MatchesBuilder();
    }

    public MatchesController() {
        this.paginationService = new PaginationService();
        this.validator = new Validator();
        new MatchesBuilder();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = Integer.parseInt(req.getParameter("page"));
        String name = req.getParameter("filter_by_player_name");

        if (validator.checkExistenceOfPlayer(name)) {
            name = "";
            req.setAttribute("message", ExceptionMessage.NO_SUCH_PLAYER_MESSAGE.message);
        }

        int numberOfPages = paginationService.getNumberOfPages(name);
        List<Match> matches = paginationService.getMatches(name, page);

        req.setAttribute("numberOfPages", numberOfPages);
        req.setAttribute("matches", matches);
        req.setAttribute("filter_by_player_name", name);
        req.getRequestDispatcher("matches.jsp").forward(req, resp);
    }
}
