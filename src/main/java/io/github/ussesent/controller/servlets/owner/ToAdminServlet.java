package io.github.ussesent.controller.servlets.owner;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import io.github.ussesent.service.UserService;
import io.github.ussesent.util.ServletUtils;

import java.io.IOException;

@WebServlet("/secure/owner/toAdmin")
public class ToAdminServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            UserService userService = ServletUtils.getUserService(req);

            int userId = Integer.parseInt(req.getParameter("userId"));
            userService.makeAdmin(userId);
            resp.sendRedirect(req.getContextPath() + "/pages/secure/owner/users");

        }catch (RuntimeException e){
            e.printStackTrace();
            resp.sendRedirect(req.getContextPath() + "/pages/secure/owner/users");
        }
    }
}
