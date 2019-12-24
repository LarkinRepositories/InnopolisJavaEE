package task01;


import java.sql.*;


/**
 *  Спроектировать базу
 *Таблица USER содержит поля id, name, birthday, login_ID, city, email, description
 *Таблица ROLE содержит поля id, name (принимает значения Administration, Clients, Billing), description
 *Таблица USER_ROLE содержит поля id, user_id, role_id
 *
 *
 *
 */
public class DbManagerApp {
    public static volatile DbManagerApp instance;

    private DbManagerApp() {};

    public static DbManagerApp getInstance() {
        DbManagerApp localInstance = instance;
        if (localInstance == null) {
            synchronized (DbManagerApp.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DbManagerApp();
                }
            }
        }
        return localInstance;
    }


    public void createTables() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        Class.forName("org.h2.Driver").newInstance();
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:default");
        connection.setAutoCommit(false);
        String SQL = "CREATE TABLE IF NOT EXISTS USER (id INTEGER PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255) NOT NULL, birthday DATE, login_ID INT NOT NULL, city VARCHAR(255) NOT NULL, email VARCHAR(255) NOT NULL, description VARCHAR(Integer.MAX_VALUE))";
        PreparedStatement ps = connection.prepareStatement(SQL);
        ps.addBatch();
        SQL = "CREATE TABLE IF NOT EXISTS ROLE(id INTEGER PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255) NOT NULL, description VARCHAR(Integer.MAX_VALUE)";
        ps = connection.prepareStatement(SQL);
        ps.addBatch();
        SQL = "CREATE TABLE IF NOT EXISTS USER_ROLE(id INTEGER PRIMARY KEY AUTO_INCREMENT, user_id INTEGER NOT NULL, role_id INTEGER NOT NULL)";
        ps = connection.prepareStatement(SQL);
        ps.addBatch();
        ps.executeBatch();
    }



    public static void main(String[] args) {
        try {
            DbManagerApp.getInstance().createTables();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            e.printStackTrace();
        }
    }
}
