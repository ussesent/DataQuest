package io.github.ussesent.util;

import jakarta.servlet.http.HttpServletRequest;
import io.github.ussesent.service.EventService;
import io.github.ussesent.service.GameService;
import io.github.ussesent.service.SubscriptionService;
import io.github.ussesent.service.UserService;

public class ServletUtils {

    public static EventService getEventService(HttpServletRequest req) {
        return (EventService) req.getServletContext().getAttribute("eventService");
    }

    public static UserService getUserService(HttpServletRequest req) {
        return (UserService) req.getServletContext().getAttribute("userService");
    }

    public static GameService getGameService(HttpServletRequest req) {
        return (GameService) req.getServletContext().getAttribute("gameService");
    }

    public static SubscriptionService getSubscriptionService(HttpServletRequest req) {
        return (SubscriptionService) req.getServletContext().getAttribute("subscriptionService");
    }
}
