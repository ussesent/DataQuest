package io.github.ussesent.controller.servlets.common;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import io.github.ussesent.exceptions.InvalidPasswordException;
import io.github.ussesent.exceptions.InvalidUsernameException;
import io.github.ussesent.exceptions.UsernameAlreadyExistsException;
import io.github.ussesent.service.UserService;
import io.github.ussesent.util.ServletUtils;

import java.io.IOException;

@WebServlet("/pages/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            UserService userService = ServletUtils.getUserService(req);

            userService.registerUser(req.getParameter("username"), req.getParameter("password"));

            resp.sendRedirect(req.getContextPath() + "/pages/login");

        } catch (UsernameAlreadyExistsException | InvalidUsernameException | InvalidPasswordException e){
            req.getSession().setAttribute("error", e.getMessage());
            resp.sendRedirect(req.getContextPath() + "/pages/register");

        } catch (RuntimeException e) {
            e.printStackTrace();
            req.getSession().setAttribute("error", "Ошибка регистрации пользователя. Попробуйте позже");
            resp.sendRedirect(req.getContextPath() + "/pages/register");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(req, resp);
    }
}