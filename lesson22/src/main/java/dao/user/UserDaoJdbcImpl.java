package dao.user;

import ConnectionManager.ConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoJdbcImpl implements UserDao {
//    private static final ConnectionManagerJdbcImpl CONNECTION_MANAGER_JDBC = ConnectionManagerJdbcImpl.getInstance();
    private final ConnectionManager connectionManager;
    private final Logger LOGGER = LoggerFactory.getLogger(UserDaoJdbcImpl.class);

    public static final String INSERT_INTO_USERS = "INSERT INTO PUBLIC.USERS VALUES (DEFAULT, ?, ?, ?, ?)";
//    public static final String GET_USER_BY_ID = "SELECT * FROM PUBLIC.USERS WHERE ID = ?";
    public static final String GET_USER_BY_LOGIN = "SELECT * FROM users WHERE LOGIN = ?";
    public static final String UPDATE_USER_BY_ID = "UPDATE PUBLIC.USERS set LOGIN=?, PASSWORD = ?, PHONE = ?, EMAIL=? WHERE ID = ?";
    public static final String DELETE_USER_BY_ID = "DELETE FROM PUBLIC.USERS WHERE ID =?";

    public UserDaoJdbcImpl(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }


    @Override
    public boolean addUser(User user) {
        LOGGER.info("addUser begins to work");
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_USERS)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getPhone());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.trace("Error in addUser method: " + e);
            e.printStackTrace();
            return false;
        }
        return true;
    }

//    @Override
//    public User getUserById(Integer id) {
//       LOGGER.info("getUserById method begins to work");
//       try (Connection connection = connectionManager.getConnection();
//            PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_ID)) {
//           preparedStatement.setInt(1, id);
//           try (ResultSet resultSet = preparedStatement.executeQuery()) {
//               if (resultSet.next()) {
//                   return new User(
//                     resultSet.getInt(1),
//                     resultSet.getString(2),
//                     resultSet.getString(3),
//                     resultSet.getString(4),
//                     resultSet.getString(5)
//                   );
//               }
//           }
//       } catch (SQLException e) {
//           LOGGER.trace("Error in getUserById method:" + e);
//           e.printStackTrace();
//       }
//       return null;
//    }


    @Override
    public boolean isSame(User user) {
        LOGGER.info("checking if users are equal in isSame method");
        try (Connection connection = connectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_LOGIN)) {
            preparedStatement.setString(1, user.getLogin());
            User dbReturnedUser = null;
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    dbReturnedUser = new User();
                    dbReturnedUser.setId(resultSet.getInt(1));
                    dbReturnedUser.setLogin(resultSet.getString(2));
                    dbReturnedUser.setPassword(resultSet.getString(3));
                    dbReturnedUser.setPhone(resultSet.getString(4));
                    dbReturnedUser.setEmail(resultSet.getString(5));
                }
            }
            return user.equals(dbReturnedUser);
        } catch (SQLException e) {
            LOGGER.trace("Error in isSame method: " +e);
            return false;
        }
    }

    @Override
    public boolean updateUserById(User user) {
        LOGGER.info("updateUserById method begins to work");
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_BY_ID)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getPhone());
            preparedStatement.setString(4, user.getPhone());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.trace("Error in updateUserById method:" + e);
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
           LOGGER.trace("Error in deleteUserById method:" + e);
           e.printStackTrace();
           return false;
       }
       return true;
    }
}
