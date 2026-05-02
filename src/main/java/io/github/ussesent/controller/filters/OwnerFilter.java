package io.github.ussesent.controller.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import io.github.ussesent.model.User;

import java.io.IOException;

@WebFilter(urlPatterns = {"/pages/secure/owner/*", "/secure/owner/*"} )
public class OwnerFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession(false);

        User user = (User) session.getAttribute("user");

        if (user.getRole() == User.UserRole.ADMIN || user.getRole() == User.UserRole.USER) {
            resp.sendRedirect(req.getContextPath()+"/pages/secure/profile");
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}