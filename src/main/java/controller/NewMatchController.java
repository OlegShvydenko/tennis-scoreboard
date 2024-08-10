package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.OngoingMatchesService;
import util.ExceptionMessage;
import util.Validator;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/new-match")
public class NewMatchController extends HttpServlet {
    private final OngoingMatchesService ongoingMatchesService;
    private final Validator validator;

    public NewMatchController(OngoingMatchesService ongoingMatchesService, Validator validator) {
        this.ongoingMatchesService = ongoingMatchesService;
        this.validator = validator;
    }

    public NewMatchController() {
        this.ongoingMatchesService = new OngoingMatchesService();
        this.validator = new Validator();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("new-match.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("first-name");
        String secondName = req.getParameter("second-name");

        if (validator.checkNames(firstName, secondName)) {
            req.setAttribute("message", ExceptionMessage.SAME_NAMES_MESSAGE.message);
            req.getRequestDispatcher("new-match.jsp").forward(req, resp);
        }

        UUID uuid = ongoingMatchesService.createMatchAndGetUUID(firstName, secondName);

        resp.sendRedirect("match-score?uuid=" + uuid);
    }
}
