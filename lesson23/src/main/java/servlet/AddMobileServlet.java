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

@WebServlet("/addmobile")
public class AddMobileServlet extends HttpServlet {
    private MobileService mobileService;

    @Override
    public void init() throws ServletException {
        ConnectionManager connectionManager = (ConnectionManager) getServletContext().getAttribute("ConnectionManager");
        mobileService = new MobileServiceImpl(connectionManager);
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("PageTitle", "New Mobiles");
        req.setAttribute("PageBody", "addmobileform.jsp");
        req.getRequestDispatcher("/layout.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String model = req.getParameter("model");
        String price = req.getParameter("price");
        String manufacturer = req.getParameter("manufacturer");
        mobileService.addMobile(model, Integer.valueOf(price), manufacturer);
        resp.sendRedirect(req.getContextPath() + "/allmobiles");
//        super.doPost(req, resp);
    }
}
