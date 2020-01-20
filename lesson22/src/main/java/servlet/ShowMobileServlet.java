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

@WebServlet(urlPatterns = "/showmobile")
public class ShowMobileServlet extends HttpServlet {
    private MobileService mobileService;

    @Override
    public void init() throws ServletException {
        ConnectionManager connectionManager = (ConnectionManager) getServletContext().getAttribute("ConnectionManager");
        mobileService = new MobileServiceImpl(connectionManager);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mobileId = req.getParameter("id");
        if (mobileId == null) {
            throw new ServletException("Missing parameter id");
        }
        Mobile mobile = mobileService.getMobileByid(Integer.valueOf(mobileId));
        if (mobile == null) {
            resp.setStatus(404);
            req.getRequestDispatcher("/notfound.jsp").forward(req, resp);
            return;
        }
        req.setAttribute("mobile", mobile);
        req.getRequestDispatcher("/showmobile.jsp").forward(req, resp);
    }


}
