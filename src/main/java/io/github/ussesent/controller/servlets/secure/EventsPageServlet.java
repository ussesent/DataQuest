package io.github.ussesent.controller.servlets.secure;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import io.github.ussesent.model.Event;
import io.github.ussesent.model.Game;
import io.github.ussesent.service.EventService;
import io.github.ussesent.service.GameService;
import io.github.ussesent.util.ServletUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet("/pages/secure/events")
public class EventsPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            EventService eventService = ServletUtils.getEventService(req);
            GameService gameService = ServletUtils.getGameService(req);

            List<Event> events = eventService.getAllEvents();
            List<Game> games = gameService.getAllGames();

            Map<Integer, String> gameNamesById = games.stream()
                    .collect(Collectors.toMap(Game::getId, Game::getName));

            req.setAttribute("events", events);
            req.setAttribute("games", games);
            req.setAttribute("gameNames", gameNamesById);
            req.setAttribute("formats", Event.EventFormat.values());
            forwardToEventsPage(req, resp);

        } catch (RuntimeException e) {
            e.printStackTrace();
            req.setAttribute("error", "Не удалось загрузить события. Попробуйте позже");
            forwardToEventsPage(req, resp);
        }
    }

    private void forwardToEventsPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/secure/events.jsp").forward(req, resp);
    }
}
