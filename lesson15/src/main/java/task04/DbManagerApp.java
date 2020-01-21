package task04;

import java.sql.*;

/**
 * 4) Перевести connection в ручное управление транзакциями
 * a) Выполнить 2-3 SQL операции на ваше усмотрение (например, Insert в 3 таблицы – USER, ROLE, USER_ROLE)
 * между sql операциями установить логическую точку сохранения(SAVEPOINT)
 * б) Выполнить 2-3 SQL операции на ваше усмотрение (например, Insert в 3 таблицы – USER, ROLE, USER_ROLE)
 * между sql операциями установить точку сохранения (SAVEPOINT A), намеренно ввести некорректные данные на последней операции,
 * что бы транзакция откатилась к логической точке SAVEPOINT A
 */
public class DbManagerApp {
    private static final String connectionUrl = "jdbc:h2:./src/main/resources/database.mv.db";




    public static void main(String[] args) {
        try {
            DriverManager.registerDriver(new org.h2.Driver());
//            setSavePointOnSQLOperation();
            setSavePointWithRollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод, выполняющий 2 операции и устанавливающий логическую точку сохранения
     * @throws SQLException
     */
    private static void setSavePointOnSQLOperation() throws SQLException {
        Connection connection = DriverManager.getConnection(connectionUrl);
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
        connection.close();
    }

    /**
     * Метод, устанавливающий точку сохранения SAVEPOINT A и выполнгяющий ROLLBACK
     */
    private static void setSavePointWithRollback()  {
        Connection connection = null;
        Savepoint savepoint = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection(connectionUrl);
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
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                assert connection != null;
                connection.rollback(savepoint);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }
}
