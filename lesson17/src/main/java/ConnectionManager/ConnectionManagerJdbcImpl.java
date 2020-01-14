package ConnectionManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        LOGGER.info("getConnection method begins to work");
        try {
//            Class.forName("org.h2.Driver");
//            connection = DriverManager.getConnection("jdbc:h2:./src/main/resources/lesson17.mv.db;IGNORECASE=TRUE;");
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?user=postgres&password=postgres");
            LOGGER.info("connection estabilished");
        } catch (ClassNotFoundException | SQLException e) {
            LOGGER.error("Connection error: "+ e);
            e.printStackTrace();
        }
        return connection;
    }

    public static Connection getDatabaseConnection() {
        return ConnectionManagerJdbcImpl.getInstance().getConnection();
    }
}
