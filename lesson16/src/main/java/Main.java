import DBManager.DBManager;
import SQLExecutor.SQLExecutor;

import java.sql.Connection;

public class Main {

    public static void main(String[] args) {
            final Connection connection = DBManager.getInstance().getConnection();
            final SQLExecutor executor = SQLExecutor.getInstance();
            DBManager.getInstance().createTables();
            executor.insertUser(connection);
            executor.insertUserWithBatch(connection);
            executor.setSavePointOnSQLOperation(connection);
            executor.setSavePointOnSQLOperation(connection);
            DBManager.getInstance().closeConnection(connection);
        }
    }

