package task03;

import java.sql.*;

import static task02.DbManagerApp.insertUserWithBatch;

/**
 * 3) Сделать параметризированную выборку по login_ID и name одновременно
 */
public class DbManagerApp {
    private static final String connectionUrl = "jdbc:h2:./src/main/resources/database.mv.db";

    public static void main(String[] args) {
        try {
            DriverManager.registerDriver(new org.h2.Driver());
            insertUserWithBatch();
            printUsers("username", "UserName");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод, выводит пользователя(ей) с соответствующими loginId и name
     * @param loginId login_iD пользователя
     * @param username name пользователя
     */
    public static void printUsers(String loginId, String username) throws SQLException {
        String query = "SELECT FROM \"user\" u WHERE u.login_ID = ? AND name = ?";
        Connection connection = DriverManager.getConnection(connectionUrl);
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, loginId);
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
    }
}
