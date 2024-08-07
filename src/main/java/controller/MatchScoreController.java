package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.MatchScore;
import persistence.entity.Match;
import service.FinishedMatchesPersistenceService;
import service.MatchScoreCalculationService;
import service.MatchesStorageService;
import util.PointWinner;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/match-score")
public class MatchScoreController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        Match match = MatchesStorageService.getMatchByUUID(uuid);
        req.setAttribute("match", match);
        req.setAttribute("uuid", uuid);
        req.getRequestDispatcher("match-score.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        PointWinner pointWinner = PointWinner.valueOf(req.getParameter("player"));

        Match match = MatchesStorageService.getMatchByUUID(uuid);
        MatchScore matchScore = match.getMatchScore();
        MatchScoreCalculationService calculationService = new MatchScoreCalculationService(matchScore);
        calculationService.updateMatchScore(pointWinner);
        FinishedMatchesPersistenceService finishedMatchesPersistenceService = new FinishedMatchesPersistenceService();
        if (matchScore.isGameOver()) finishedMatchesPersistenceService.finishMatch(uuid, pointWinner);
        req.setAttribute("match", match);
        req.setAttribute("uuid", uuid);
        req.getRequestDispatcher("match-score.jsp").forward(req, resp);
    }
}
