package dao;

import ConnectionManager.ConnectionManager;
import ConnectionManager.ConnectionManagerJdbcImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoJdbcImpl implements UserDao {
//    private static final ConnectionManagerJdbcImpl CONNECTION_MANAGER_JDBC = ConnectionManagerJdbcImpl.getInstance();
    private final String INSERT_INTO_USERS = "INSERT INTO PUBLIC.USERS (NAME, BIRTHDAY, LOGIN_ID, CITY, EMAIL, DESCRIPTION) VALUES (?, ?, ?, ?, ?, ?)";
    private final String GET_USER_BY_ID = "SELECT * FROM PUBLIC.USERS WHERE ID = ?";
    private final String UPDATE_USER_BY_ID = "UPDATE PUBLIC.USERS set NAME=?, BIRTHDAY = ?, LOGIN_ID = ?, CITY = ?, EMAIL=?, DESCRIPTION = ? WHERE id = ?";
    private final String DELETE_USER_BY_ID = "DELETE FROM PUBLIC.USERS WHERE ID =?";
    private final ConnectionManager connectionManager;
    private final Logger LOGGER = LoggerFactory.getLogger(UserDaoJdbcImpl.class);

    public UserDaoJdbcImpl(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }


    @Override
    public boolean addUser(User user) {
        LOGGER.info("addUser begins to work");
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_USERS)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setDate(2, user.getBirthday());
            preparedStatement.setInt(3, user.getLoginId());
            preparedStatement.setString(4, user.getCity());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getDescription());
            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error("Error in addUser method: " + e);
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public User getUserById(Integer id) {
       LOGGER.info("getUserById method begins to work");
       try (Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_ID)) {
           preparedStatement.setInt(1, id);
           try (ResultSet resultSet = preparedStatement.executeQuery()) {
               if (resultSet.next()) {
                   return new User(
                     resultSet.getInt(1),
                     resultSet.getString(2),
                     resultSet.getDate(3),
                     resultSet.getInt(4),
                     resultSet.getString(5),
                     resultSet.getString(6),
                     resultSet.getString(7)
                   );
               }
           }
       } catch (SQLException e) {
           LOGGER.error("Error in getUserById method:" + e);
           e.printStackTrace();
       }
       return null;
    }

    @Override
    public boolean updateUserById(User user) {
        LOGGER.info("updateUserById method begins to work");
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_BY_ID)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setDate(2, user.getBirthday());
            preparedStatement.setInt(3, user.getLoginId());
            preparedStatement.setString(4, user.getCity());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getDescription());
            preparedStatement.setInt(7, user.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error("Error in updateUserById method:" + e);
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteUserById(Integer id) {
       LOGGER.info("deleteUserById begins to work");
       try (Connection connection = connectionManager.getConnection();
       PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_BY_ID)) {
           preparedStatement.setInt(1, id);
           preparedStatement.execute();
       } catch (SQLException e) {
           LOGGER.error("Error in deleteUserById method:" + e);
           e.printStackTrace();
           return false;
       }
       return true;
    }
}
