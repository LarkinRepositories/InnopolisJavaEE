import ConnectionManager.ConnectionManager;
import  ConnectionManager.ConnectionManagerJdbcImpl;
import dao.UserDao;
import dao.UserDaoJdbcImpl;
import pojo.User;

import java.sql.Date;

public class Main {
    public static void main(String[] args) {
        ConnectionManager connectionManager = ConnectionManagerJdbcImpl.getInstance();
        UserDao userDao = new UserDaoJdbcImpl(connectionManager);
        Main main = new Main();
        main.method1(userDao);
    }

    public void method1(UserDao userDao) {
        User user = new User(
                null,
                "Username",
                Date.valueOf("2005-05-11 10:00:00"),
                1,
                "City",
                "mail@mail.com",
                "random description"
        );
//        Logger here
        userDao.addUser(user);
        userDao.updateUserById(user);
        user = userDao.getUserById(1);
//        Logger here
//         User(Integer id, String name, Date birthday, Integer loginId, String city, String email, String description)
    }
}
