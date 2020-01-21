package servlet;

import ConnectionManager.ConnectionManager;
import service.mobile.MobileService;
import service.mobile.MobileServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deletemobile")
public class DeleteMobileServlet extends HttpServlet {
    private MobileService mobileService;

    @Override
    public void init() throws ServletException {
        ConnectionManager connectionManager = (ConnectionManager) getServletContext().getAttribute("ConnectionManager");
        mobileService = new MobileServiceImpl(connectionManager);
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("PageTitle", "Delete Mobile By Id");
        req.setAttribute("PageBody", "deletemobileform.jsp");
        req.getRequestDispatcher("/layout.jsp").forward(req, resp);
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       req.setCharacterEncoding("utf-8");
       Integer id = Integer.valueOf(req.getParameter("id"));
       mobileService.deleteMobileById(id);
       resp.sendRedirect(req.getContextPath() + "/allmobiles");
    }

}
