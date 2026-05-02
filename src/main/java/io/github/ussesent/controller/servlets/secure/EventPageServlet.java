package io.github.ussesent.controller.servlets.secure;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import io.github.ussesent.model.Event;
import io.github.ussesent.service.EventService;
import io.github.ussesent.service.GameService;
import io.github.ussesent.util.ServletUtils;

import java.io.IOException;

@WebServlet("/pages/secure/event")
public class EventPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String eventId = req.getParameter("eventId");

        if (eventId == null) {
            req.setAttribute("error", "Не указан id события");
            forwardToEventPage(req, resp);
            return;
        }

        try {
            EventService eventService = ServletUtils.getEventService(req);
            GameService gameService = ServletUtils.getGameService(req);


            int id = Integer.parseInt(eventId);

            Event event = eventService.getEventById(id);

            if (event == null) {
                req.setAttribute("error", "Событие не найдено");
                forwardToEventPage(req, resp);
                return;
            }

            String gameName = gameService.getGameById(event.getGameId()).getName();


            req.setAttribute("event", event);
            req.setAttribute("gameName", gameName);
            forwardToEventPage(req, resp);

        } catch (NumberFormatException e) {
            e.printStackTrace();
            req.setAttribute("error", "Неверный формат id");
            forwardToEventPage(req, resp);

        } catch (RuntimeException e) {
            e.printStackTrace();
            req.setAttribute("error", "Не удалось загрузить событие.");
            forwardToEventPage(req, resp);
        }
    }

    private void forwardToEventPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/secure/event.jsp").forward(req, resp);
    }
}
