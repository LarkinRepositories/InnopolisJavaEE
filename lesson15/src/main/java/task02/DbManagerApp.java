package task02;

import java.sql.*;

/**
 * 2) Через jdbc интерфейс сделать запись данных(INSERT) в таблицу
 * a) Используя параметризированный запрос
 * b)  Используя batch процесс
 */

public class DbManagerApp {
    private static final String connectionUrl = "jdbc:h2:./src/main/resources/database.mv.db";
    private static final String QUERY = "INSERT INTO USER (name, birthday, login_id, city, email, description) VALUES(?, ?, ?, ?, ?, ?)";

    public static void main(String[] args) {
        try {
            DriverManager.registerDriver(new org.h2.Driver());
            insertUser();
            insertUserWithBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод, выполняет INSERT в таблицу с помощью параметризированного запроса
     * @return id пользователя, созданного в результате запроса или -1 если произошла ошибка
     */
    public static int insertUser() throws SQLException {
        Connection connection = DriverManager.getConnection(connectionUrl);
        PreparedStatement ps = connection.prepareStatement(QUERY, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, "Name");
        ps.setTimestamp(2, Timestamp.valueOf("1988-10-12 00:00:00"));
        ps.setString(3, "name");
        ps.setString(4, "City");
        ps.setString(5, "mail@mail.com");
        ps.setString(6, "description");
        ps.execute();
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1);
        }
        return -1;
    }

    /**
     * Метод, выполняющий INSERT в таблицу с помощью batch процесса
     * @throws SQLException
     */
    public static void insertUserWithBatch() throws SQLException {
        Connection connection = DriverManager.getConnection(connectionUrl);
        PreparedStatement ps = connection.prepareStatement(QUERY);
        connection.setAutoCommit(false);
        ps.setString(1,"UserName");
        ps.setTimestamp(2, Timestamp.valueOf("1978-15-19 05:10:12"));
        ps.setInt(3, 1);
        ps.setString(4, "AnotherCity");
        ps.setString(5, "email@mail.ru");
        ps.setString(6, "username's description");
        ps.addBatch();

        ps.setString(1, "Just another user");
        ps.setTimestamp(2, Timestamp.valueOf("1978-15-19 05:10:12"));
        ps.setInt(3, 1);
        ps.setString(4, "AnotherCity1");
        ps.setString(5, "email1@mail.ru");
        ps.setString(6, "username's description1");
        ps.addBatch();

        ps.executeBatch();
    }

}
