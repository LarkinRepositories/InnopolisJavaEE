package SQLExecutor;

import Logging.LoggingUtil;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Random;

public class SQLExecutor {
    public static volatile SQLExecutor instance;

    private static final Random RANDOM = new Random();
    private static final String QUERY = "INSERT INTO USER (name, birthday, login_id, city, email, description) VALUES(?, ?, ?, ?, ?, ?)";
    private static final Logger LOGGER = LoggingUtil.LOGGER;

    private SQLExecutor() {};

    public static SQLExecutor getInstance() {
        SQLExecutor localInstance = instance;
        if (localInstance == null) {
            synchronized (SQLExecutor.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new SQLExecutor();
                }
            }
        }
        return localInstance;
    }

    /**
     * Метод, выполняет INSERT в таблицу с помощью параметризированного запроса
     * @param connection соединение
     * @return id пользователя, созданного в результате запроса или -1 если произошла ошибка
     * @throws SQLException
     */
    public int insertUser(Connection connection)  {
        if (connection == null) throw new IllegalArgumentException("Connection is null");
        LOGGER.info("insertUser begins to work");
        PreparedStatement ps = null;
        try {

            ps = connection.prepareStatement(QUERY, Statement.RETURN_GENERATED_KEYS );
            ps.setString(1, "Name");
            ps.setTimestamp(2, Timestamp.valueOf("1988-10-12 01:10:01"));
            ps.setInt(3, 1);
            ps.setString(4, "City");
            ps.setString(5, "mail@mail.com");
            ps.setString(6, "description");
            ps.execute();
            LOGGER.info("User has been successfully added to table USERS");
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
//                LOGGER.info( "User id:"+ rs.getInt(1));
                return rs.getInt(1);
            }
            return -1;
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error("insertUser method ends with ERROR: " + e);
        }

    }

    /**
     * Метод, выполняющий INSERT в таблицу с помощью batch процесса
     * @param connection соединение с БД
     * @throws SQLException
     */
    public void insertUserWithBatch(Connection connection)  {
        if (connection == null) throw new IllegalArgumentException("Connection is null");
        LOGGER.info("insertUserWithBatch begins to work");
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(QUERY);
            connection.setAutoCommit(false);
            ps.setString(1,"UserName");
            ps.setTimestamp(2, Timestamp.valueOf("2001-05-01 01:10:11"));
            ps.setInt(3, RANDOM.nextInt());
            ps.setString(4, "AnotherCity");
            ps.setString(5, "email@mail.ru");
            ps.setString(6, "username's description");
            ps.addBatch();

            ps.setString(1, "Just another user");
            ps.setTimestamp(2, Timestamp.valueOf("2009-07-02 05:10:11"));
            ps.setInt(3, RANDOM.nextInt());
            ps.setString(4, "AnotherCity1");
            ps.setString(5, "email1@mail.ru");
            ps.setString(6, "username's description1");
            ps.addBatch();

            ps.executeBatch();
            LOGGER.info("insertUserWithBatch method successfully finished");
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error("insertUserWithBatch ends with ERROR: " + e);
        }
    }

    /**
     * Метод, выводит пользователя(ей) с соответствующими loginId и name
     * @param loginId login_iD пользователя
     * @param username name пользователя
     */
    public void printUsers(Connection connection, Integer loginId, String username)  {
        if (connection == null) throw new IllegalArgumentException("Connection is null");
        LOGGER.info("printUsers is working");
        String query = "SELECT * FROM PUBLIC.USER WHERE user.login_ID = ? AND user.NAME = ?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, loginId);
            ps.setString(2, username);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + "^" +
                                rs.getString("name") + "^" +
                                rs.getString("login_id") + "^" +
                                rs.getString("city") + "^" +
                                rs.getString("email") + "^" +
                                rs.getString("description")
                );
            }
            LOGGER.info("printUsers method successfully finished working");
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error("printUsers method ends with ERROR: " + e);
        }
    }

    /**
     * Метод, выполняющий 2 операции и устанавливающий логическую точку сохранения
     * @param connection соединение с БД
     * @throws SQLException
     */
    public  void setSavePointOnSQLOperation(Connection connection)  {
        if (connection == null) throw new IllegalArgumentException("Connection is null");
        LOGGER.info("setSavePointSQLOperation begins to work");
        try {
            Savepoint savepoint = connection.setSavepoint("SAVEPOINT");
            Statement st = connection.createStatement();
            connection.setAutoCommit(false);
            st.executeUpdate(
                    "INSERT INTO USER (name, login_Id, city, email, description) " +
                            "VALUES ('NewUser', 100, 'City', 'mail@mail.co.uk', 'Description')"
            );
            st.executeUpdate(
                    "INSERT INTO USER_ROLE (USER_ID, ROLE_ID)" +
                            "VALUES (SELECT id FROM USER WHERE name = 'NewUser' ), " +
                            "(SELECT id FROM ROLES WHERE name = 'Administration'))"
            );
            connection.commit();
            connection.setAutoCommit(true);
            LOGGER.info("setSavePointOnSQLOperation method successfully finished");
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.info("setSavePointOnSQLOperation ends with ERROR: " + e);
        }

    }

    /**
     * Метод, устанавливающий точку сохранения SAVEPOINT A и выполнгяющий ROLLBACK
     * @param connection соединение с БД
     */
   public void setSavePointWithRollback(Connection connection)  {
        if (connection == null) throw new IllegalArgumentException("Connection is null");
        LOGGER.info("setSavePointWithRollback begins to work");
        Savepoint savepoint = null;
        Statement statement = null;
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.executeUpdate(
                    "INSERT INTO USER (name, login_id, city, email, description)" +
                            "VALUES ('user100', 950, 'City100', 'mail100@mail.com', 'Description 100')"

            );
            savepoint = connection.setSavepoint("SAVEPOINT A");
            statement.executeUpdate(
                    "INSERT INTO PUBLIC.USER (name, login_id, city, email, description) " +
                            "VALUES ('user150', 950, 'City100', 'mail100@gmail.com', 'Description 100')"
            );
            connection.commit();
            LOGGER.info("setSavePointWithRollback method successfully finished");
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error("setSavePointWithRollback method ends up with ERROR: " + e);
            try {
                connection.rollback(savepoint);
                LOGGER.info("rollback to SAVEPOINT A in setSavePointWithRollback method success");
            } catch (SQLException ex) {
                ex.printStackTrace();
                LOGGER.error("rollback to SAVEPOINT A in setSavePointWithRollback ends with ERROR: " + e);
            }
        }

    }
}
