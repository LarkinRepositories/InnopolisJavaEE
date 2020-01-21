package servlet;

import ConnectionManager.ConnectionManager;
import pojo.mobile.Mobile;
import pojo.user.User;
import service.mobile.MobileService;
import service.mobile.MobileServiceImpl;
import service.user.UserService;
import service.user.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/showuser")
public class ShowUserServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        ConnectionManager connectionManager = (ConnectionManager) getServletContext().getAttribute("ConnectionManager");
        userService = new UserServiceImpl(connectionManager);
        super.init();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("id");
        if (userId == null) {
            throw new ServletException("Missing parameter id");
        }
        User user = userService.getUserById(Integer.valueOf(userId));
        if (user == null) {
            resp.setStatus(404);
            req.getRequestDispatcher("/notfound.jsp").forward(req, resp);
            return;
        }
        req.setAttribute("user", user);
        req.setAttribute("PageTitle", user.getLogin());
        req.setAttribute("PageBody", "showuser.jsp");
        req.getRequestDispatcher("/layout.jsp").forward(req, resp);
    }
}
