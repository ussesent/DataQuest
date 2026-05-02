package io.github.ussesent.controller.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import io.github.ussesent.model.User;
import io.github.ussesent.service.UserService;
import io.github.ussesent.util.ServletUtils;

import java.io.IOException;

@WebFilter("/pages/secure/*")
public class UserRefreshFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false);

        UserService userService = ServletUtils.getUserService(req);

        if (session != null) {
            Integer userId = (Integer) session.getAttribute("userId");
            User cachedUser = (User) session.getAttribute("user");

            if (userId != null) {
                try {
                    User actualUser = userService.getUserById(userId);

                    if (cachedUser == null || !cachedUser.getRole().equals(actualUser.getRole())) {
                        //  Обновляем объект user в сессии, если роль изменилась
                        session.setAttribute("user", actualUser);
                    }

                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
            }
        }

        chain.doFilter(request, response);
    }
}