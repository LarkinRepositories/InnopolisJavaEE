package mocks;

import ConnectionManager.ConnectionManager;

import java.sql.Connection;

public class ConnectionManagerMock implements ConnectionManager {


    @Override
    public Connection getConnection() {
        return new ConnectionMock();
    }

    @Override
    public boolean closeConnection(Connection connection) {
        return false;
    }
}
