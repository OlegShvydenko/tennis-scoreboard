package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.OngoingMatchesService;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/new-match")
public class NewMatchController extends HttpServlet {
    private final OngoingMatchesService ongoingMatchesService;

    public NewMatchController(OngoingMatchesService ongoingMatchesService) {
        this.ongoingMatchesService = ongoingMatchesService;
    }

    public NewMatchController() {
        this.ongoingMatchesService = new OngoingMatchesService();
    }

    // TODO разделять логические части кода переходами строк
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("new-match.jsp");
        requestDispatcher.forward(req, resp);
    }
    // TODO Добавить try catch куска с sendRedirect
    // TODO разделять логические части кода переходами строк
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("first-name");
        String secondName = req.getParameter("second-name");
        UUID uuid = ongoingMatchesService.createMatchAndGetUUID(firstName, secondName);
        resp.sendRedirect("match-score?uuid=" + uuid);
    }
}
