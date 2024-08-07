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
    // TODO удалить лишние зависимости (matchRepository)
    private final IMatchRepository matchRepository;
    private final IPlayerRepository playerRepository;

    public MatchesController(MatchRepository matchRepository, IPlayerRepository playerRepository) {
        this.matchRepository = matchRepository;
        this.playerRepository = playerRepository;
        // TODO забей
        MatchesBuilder.initTestData();
    }

    public MatchesController() {
        this.matchRepository = new MatchRepository();
        this.playerRepository = new PlayerRepository();
        // TODO забей
        MatchesBuilder.initTestData();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        // TODO добавить адекватную обработку исключений и возврат сообщения об ошибке потребителю
        try {
            int page = Integer.parseInt(req.getParameter("page"));
            String name = req.getParameter("filter_by_player_name");
            // TODO ПРИ КАЖДОМ ЗАПРОСЕ СОЗДАЕТСЯ ЗАНОВО, ПОСЛЕ ЗАПРОСА УДАЛЯЕТСЯ, исправить на использование зависимости и инициализацию при инициализации контроллера
            PaginationService paginationService = new PaginationService();

            // TODO переместить весь код ниже до начала работы с request, при ошибке при валидации выкидывать исключение, которое будет обрабатываться в контроллере и отдавать ошибку на потребителя
            if (!Objects.equals(name, "") && playerRepository.getPlayerByName(name) == null && name != null){
                name = "";
                req.setAttribute("message", "Игрок с таким именем не участвовал в матчах.");
            }
            int numberOfPages = paginationService.getNumberOfPages(name);
            List<Match> matches = paginationService.getMatches(name, page);

            req.setAttribute("numberOfPages", numberOfPages);
            req.setAttribute("matches", matches);
            req.setAttribute("filter_by_player_name", name);
            req.getRequestDispatcher("matches.jsp").forward(req, resp);
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
