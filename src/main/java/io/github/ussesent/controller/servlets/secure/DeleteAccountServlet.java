package io.github.ussesent.controller.servlets.secure;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import io.github.ussesent.model.User;
import io.github.ussesent.service.UserService;
import io.github.ussesent.util.ServletUtils;

import java.io.IOException;

@WebServlet("/secure/deleteAccount")
public class DeleteAccountServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            UserService userService = ServletUtils.getUserService(req);

            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");

            userService.deleteUser(user.getId());
            session.invalidate();
            resp.sendRedirect(req.getContextPath() + "/pages/login");

        } catch (RuntimeException e) {
            e.printStackTrace();
            req.getSession().setAttribute("error", "Ошибка при удалении аккаунта. Попробуйте позже");
            resp.sendRedirect(req.getContextPath()+"/pages/secure/profile");

        }
    }
}
