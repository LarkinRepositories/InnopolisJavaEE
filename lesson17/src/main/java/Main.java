import ConnectionManager.ConnectionManager;
import  ConnectionManager.ConnectionManagerJdbcImpl;
import dao.UserDao;
import dao.UserDaoJdbcImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.User;

import java.sql.Date;
import java.util.Random;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        ConnectionManager connectionManager = ConnectionManagerJdbcImpl.getInstance();
        UserDao userDao = new UserDaoJdbcImpl(connectionManager);
        Main main = new Main();
        main.method1(userDao);
    }

    public void method1(UserDao userDao) {
        User user = new User(
                1,
                "Username",
                Date.valueOf("2005-05-11"),
                (Integer) new Random().nextInt(),
                "City",
                "mail@mail.com",
                "random description"
        );
        LOGGER.info("Начальный объект: " + user);
        userDao.addUser(user);
        user.setDescription("new description");
        userDao.updateUserById(user);
        user = userDao.getUserById(1);
        LOGGER.info("Конечный объект: " + user);
//        Logger here
//         User(Integer id, String name, Date birthday, Integer loginId, String city, String email, String description)
    }
}
