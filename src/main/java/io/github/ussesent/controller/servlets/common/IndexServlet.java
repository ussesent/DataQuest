package io.github.ussesent.controller.servlets.common;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import io.github.ussesent.model.Event;
import io.github.ussesent.service.EventService;
import io.github.ussesent.util.ServletUtils;

import java.io.IOException;
import java.util.List;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            EventService eventService = ServletUtils.getEventService(req);

            List<Event> lastEvents = eventService.getLastThreeEvents();

            req.setAttribute("lastEvents", lastEvents);
            req.getRequestDispatcher("/WEB-INF/pages/index.jsp").forward(req, resp);

        } catch (RuntimeException e) {
            e.printStackTrace();
            req.setAttribute("error", "Не удалось загрузить события.");
            req.getRequestDispatcher("/WEB-INF/pages/index.jsp").forward(req, resp);
        }
    }
}