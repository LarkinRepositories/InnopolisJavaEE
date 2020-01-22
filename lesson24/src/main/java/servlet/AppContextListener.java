package servlet;

import ConnectionManager.ConnectionManager;
import ConnectionManager.ConnectionManagerJdbcImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {
    private Logger logger = LoggerFactory.getLogger(AppContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("context initialized");
        ConnectionManager connectionManager = ConnectionManagerJdbcImpl.getInstance();
        sce.getServletContext().setAttribute("ConnectionManager", connectionManager);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("context destroyed");
        ConnectionManager connectionManager = (ConnectionManager) sce.getServletContext().getAttribute("ConnectionManager");
        connectionManager.closeConnection(connectionManager.getConnection());
    }
}
