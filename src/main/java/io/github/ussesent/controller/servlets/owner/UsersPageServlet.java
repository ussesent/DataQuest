package io.github.ussesent.controller.servlets.owner;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import io.github.ussesent.model.User;
import io.github.ussesent.service.UserService;
import io.github.ussesent.util.ServletUtils;

import java.io.IOException;
import java.util.List;

@WebServlet("/pages/secure/owner/users")
public class UsersPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            UserService userService = ServletUtils.getUserService(req);

            List<User> users = userService.getAllUsers();
            long countAdmins = userService.countAdmins();

            req.setAttribute("users", users);
            req.setAttribute("countAdmins", countAdmins);
            req.getRequestDispatcher("/WEB-INF/pages/secure/owner/users.jsp").forward(req, resp);

        } catch (RuntimeException e) {
            e.printStackTrace();
            req.setAttribute("error", "Не удалось загрузить пользователй.");
            req.getRequestDispatcher("/WEB-INF/pages/secure/owner/users.jsp").forward(req, resp);
        }
    }
}
