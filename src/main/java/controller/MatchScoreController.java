package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.model.MatchScore;
import persistence.entity.Match;
import service.FinishedMatchesPersistenceService;
import service.MatchScoreCalculationService;
import service.model.MatchesStorage;
import util.WinnerPoint;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/match-score")
public class MatchScoreController extends HttpServlet {
    private final MatchScoreCalculationService calculationService;
    private final FinishedMatchesPersistenceService finishedMatchesService;

    public MatchScoreController(MatchScoreCalculationService calculationService,
                                FinishedMatchesPersistenceService finishedMatchesPersistenceService) {
        this.calculationService = calculationService;
        this.finishedMatchesService = finishedMatchesPersistenceService;
    }
    public MatchScoreController() {
        this.calculationService = new MatchScoreCalculationService();
        this.finishedMatchesService = new FinishedMatchesPersistenceService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = UUID.fromString(req.getParameter("uuid"));

        Match match = MatchesStorage.getByUUID(uuid);

        req.setAttribute("match", match);
        req.setAttribute("uuid", uuid);
        req.getRequestDispatcher("match-score.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        WinnerPoint winnerPoint = WinnerPoint.valueOf(req.getParameter("player"));

        Match match = MatchesStorage.getByUUID(uuid);
        MatchScore matchScore = match.getMatchScore();

        calculationService.setMatchScore(matchScore);
        calculationService.updateMatchScore(winnerPoint);

        if (matchScore.isGameOver()) finishedMatchesService.finishMatch(uuid, winnerPoint);

        req.setAttribute("match", match);
        req.setAttribute("uuid", uuid);
        req.getRequestDispatcher("match-score.jsp").forward(req, resp);
    }
}
