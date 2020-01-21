package ConnectionManager;

import java.sql.Connection;

public interface ConnectionManager {
    public Connection getConnection();
    public boolean closeConnection(Connection connection);
}
