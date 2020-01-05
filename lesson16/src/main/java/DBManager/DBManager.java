package DBManager;

import Logging.LoggingUtil;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBManager {
    private static volatile DBManager instance;
    private final Logger LOGGER = LoggingUtil.LOGGER;
    private DBManager() {};

    public static DBManager getInstance() {
        DBManager localInstance = instance;
        if (localInstance == null) {
            synchronized (DBManager.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DBManager();
                }
            }
        }
        return localInstance;
    }

    /**
     * Метод, устанавливающий соединение с БД
     * @return соединение с бд
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws SQLException
     */
    public Connection getConnection() {
        Connection connection = null;
        try {
            LOGGER.info("Trying to connect...");
            Class.forName("org.h2.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:h2:./src/main/resources/lesson16.mv.db");
            LOGGER.info("Connection successful");
        } catch (InstantiationException | SQLException | ClassNotFoundException | IllegalAccessException e) {
            e.printStackTrace();
            LOGGER.error("Connection ends up with ERROR: " + e);
        }
        return connection;
    }

    /**
     * Метод, закрывающий соединение с БД
     * @param connection соединение, которое необходимо закрыть
     * @return
     */

    public boolean closeConnection(Connection connection) {
        try {
            connection.close();
            LOGGER.info("Connection has been closed");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error("Error while closing the connection");
            return false;
        }
    }

    /**
     * Метод, создающий таблицы в БД
     */
    public void createTables() {
        LOGGER.info("createTables method begins to work");
        Connection connection = getConnection();
        try {
            connection.setAutoCommit(false);
            String SQL = "CREATE TABLE IF NOT EXISTS USER (id INTEGER PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255) NOT NULL, birthday DATE, login_ID INTEGER NOT NULL UNIQUE, city VARCHAR(255) NOT NULL, email VARCHAR(255) NOT NULL UNIQUE, description VARCHAR(255))";
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.addBatch();
            SQL = "CREATE TYPE ROLE as ENUM ('Administration', 'Clients', 'Billing')";
            ps = connection.prepareStatement(SQL);
            ps.addBatch();
            ps.executeBatch();
            SQL = "CREATE TABLE IF NOT EXISTS ROLE(id INTEGER PRIMARY KEY AUTO_INCREMENT, name ROLE, description VARCHAR(255))";
            ps = connection.prepareStatement(SQL);
            ps.addBatch();
            SQL = "CREATE TABLE IF NOT EXISTS USER_ROLE(id INTEGER PRIMARY KEY AUTO_INCREMENT, user_id INTEGER NOT NULL UNIQUE, role_id INTEGER NOT NULL)";
            ps = connection.prepareStatement(SQL);
            ps.addBatch();
            SQL = "CREATE TABLE IF NOT EXISTS LOGS (id INTEGER PRIMARY KEY AUTO_INCREMENT, date DATE, log_level VARCHAR(5), message VARCHAR(255), exception VARCHAR(200))";
            ps = connection.prepareStatement(SQL);
            ps.addBatch();
            ps.executeBatch();
            LOGGER.info("tables successfully created");
            System.out.println("TABLES SUCCESSFULLY CREATED");
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error("ERROR in createTables method:" + e);
        }
    }

}
