package servlet;

import dao.MobileDao;
import pojo.Mobile;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet(urlPatterns = "/allmobiles", name="Mobiles")
public class AllMobileServlet extends HttpServlet {
    @Inject
    private MobileDao mobileDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Collection<Mobile> mobiles = mobileDao.getAllMobile();

        req.setAttribute("model", mobiles);
        req.getRequestDispatcher("WEB-INF/jsp/allmobiles.jsp").forward(req, resp);
    }
}
