package servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger(LoginFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("LoginFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpServletRequest.getSession(false);

        boolean isAuthorized = (session !=null && session.getAttribute("user") != null);
        String authURI = httpServletRequest.getContextPath() + "/login";
        boolean isAuthRequest = httpServletRequest.getRequestURI().equals(authURI);
        boolean isAuthPage = httpServletRequest.getRequestURI().endsWith("login.jsp");

        if (isAuthorized && (isAuthRequest || isAuthPage)) {
            RequestDispatcher dispatcher = servletRequest.getRequestDispatcher("/");
            dispatcher.forward(servletRequest, servletResponse);
        } else if (isAuthorized || isAuthRequest) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher("/login");
            requestDispatcher.forward(servletRequest, servletResponse);
        }
//        session.setAttribute("ForwardedUrl", httpServletRequest.getContextPath());

    }

    @Override
    public void destroy() {
        logger.info("Logfilter Destroyed");
    }
}
