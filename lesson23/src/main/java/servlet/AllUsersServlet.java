package servlet;

import ConnectionManager.ConnectionManager;
import pojo.user.User;
import service.user.UserService;
import service.user.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet("/allusers")
public class AllUsersServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        ConnectionManager connectionManager = (ConnectionManager) getServletContext().getAttribute("ConnectionManager");
        userService = new UserServiceImpl(connectionManager);
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Collection<User> users = userService.getUsers();
        req.setAttribute("users", users);
        req.setAttribute("PageTitle", "users");
        req.setAttribute("PageBody", "allusers.jsp");
        req.getRequestDispatcher("/layout.jsp").forward(req, resp);
    }

}
