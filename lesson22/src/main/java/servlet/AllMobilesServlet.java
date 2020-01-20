package servlet;

import ConnectionManager.ConnectionManager;
import pojo.mobile.Mobile;
import service.mobile.MobileService;
import service.mobile.MobileServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;


@WebServlet(urlPatterns = "/allmobiles")
public class AllMobilesServlet extends HttpServlet {
    private MobileService mobileService;

    @Override
    public void init() throws ServletException {
        ConnectionManager connectionManager = (ConnectionManager) getServletContext().getAttribute("ConnectionManager");
        mobileService = new MobileServiceImpl(connectionManager);
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Collection<Mobile> mobiles = mobileService.getMobiles();
        req.setAttribute("mobiles", mobiles);
        req.setAttribute("PageTitle", "Mobiles");
        req.setAttribute("PageBody", "allmobiles.jsp");
        req.getRequestDispatcher("/layout.jsp").forward(req, resp);
    }
}
