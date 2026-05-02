package io.github.ussesent.controller.servlets.common;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import io.github.ussesent.exceptions.InvalidCredentialsException;
import io.github.ussesent.model.User;
import io.github.ussesent.service.UserService;
import io.github.ussesent.util.ServletUtils;

import java.io.IOException;


@WebServlet("/pages/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            UserService userService = ServletUtils.getUserService(req);

            User user = userService.loginUser(req.getParameter("username"), req.getParameter("password"));

            HttpSession session = req.getSession();
            session.setAttribute("userId", user.getId());
            session.setAttribute("user", user);

            resp.sendRedirect(req.getContextPath() + "/pages/secure/events");

        } catch (InvalidCredentialsException e) {
            req.getSession().setAttribute("error", e.getMessage());
            resp.sendRedirect(req.getContextPath() + "/pages/login");


        } catch (RuntimeException e) {
            e.printStackTrace();
            req.getSession().setAttribute("error", "Ошибка при входе. Попробуйте позже.");
            resp.sendRedirect(req.getContextPath() + "/pages/login");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
    }
}