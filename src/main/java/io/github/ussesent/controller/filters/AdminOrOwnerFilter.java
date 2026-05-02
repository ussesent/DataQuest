package io.github.ussesent.controller.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import io.github.ussesent.model.User;
import io.github.ussesent.util.ServletUtils;

import java.io.IOException;

@WebFilter("/secure/addEvent")
public class AdminOrOwnerFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession(false);

        User sessionUser = (User) session.getAttribute("user");
        User freshUser = ServletUtils.getUserService(req).getUserById(sessionUser.getId());
        session.setAttribute("user", freshUser);

        User user = (User) session.getAttribute("user");
        if (!user.getRole().equals(User.UserRole.ADMIN) && !user.getRole().equals(User.UserRole.OWNER)) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Нет доступа");
            return;
        }

        chain.doFilter(req, resp);
    }
}