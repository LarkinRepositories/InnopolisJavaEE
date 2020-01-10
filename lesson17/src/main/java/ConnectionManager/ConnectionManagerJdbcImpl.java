package ConnectionManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManagerJdbcImpl implements ConnectionManager {
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
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:./src/main/resources/lesson17.mv.db");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
