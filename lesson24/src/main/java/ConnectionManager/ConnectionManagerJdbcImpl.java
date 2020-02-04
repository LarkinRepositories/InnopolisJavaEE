package ConnectionManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionManagerJdbcImpl implements ConnectionManager {
    private final Logger LOGGER = LoggerFactory.getLogger(ConnectionManagerJdbcImpl.class);

    private static volatile ConnectionManagerJdbcImpl instance;

    private ConnectionManagerJdbcImpl() {};

    public static ConnectionManagerJdbcImpl getInstance() {
        ConnectionManagerJdbcImpl localInstance = instance;
        if (localInstance == null) {
            synchronized (ConnectionManagerJdbcImpl.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ConnectionManagerJdbcImpl();
                }
            }
        }
        return localInstance;
    }


    @Override
    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(
                         "jdbc:postgresql://host.docker.internal:5434/mobile",
//                    "jdbc:postgresql://localhost:5434/mobile",
                    "postgres",
                    "qwerty");
        } catch (ClassNotFoundException | SQLException e) {
            LOGGER.warn("Some thing wrong in getConnection method", e);
        }
        return connection;
    }

    @Override
    public boolean closeConnection(Connection connection) {
        try {
            LOGGER.info("closing connection");
            connection.close();
            LOGGER.info("connection successfully closed");
        } catch (SQLException e) {
            LOGGER.warn("Error closing the connection: " + e);
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static Connection getDatabaseConnection() {
        return ConnectionManagerJdbcImpl.getInstance().getConnection();
    }
}
