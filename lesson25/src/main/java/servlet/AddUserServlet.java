package servlet;

import ConnectionManager.ConnectionManager;
import service.user.UserService;
import service.user.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/adduser")
public class AddUserServlet extends HttpServlet {
    private UserService userService;


    @Override
    public void init() throws ServletException {
        ConnectionManager connectionManager = (ConnectionManager) getServletContext().getAttribute("ConnectionManager");
//        mobileService = new MobileServiceImpl(connectionManager);
        userService = new UserServiceImpl(connectionManager);
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("PageTitle", "New User");
        req.setAttribute("PageBody", "adduserform.jsp");
        req.getRequestDispatcher("/layout.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
//        mobileService.addMobile(model, Integer.valueOf(price), manufacturer);
        userService.addUser(login, password, phone, email);
        resp.sendRedirect(req.getContextPath() + "/allusers");
//        super.doPost(req, resp);
    }


}
