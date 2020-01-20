package service.user;

import ConnectionManager.ConnectionManager;
import dao.user.UserDao;
import dao.user.UserDaoJdbcImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.user.User;

public class UserServiceImpl implements UserService{
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserDao userDao;

    public UserServiceImpl(ConnectionManager connectionManager) {
        userDao = new UserDaoJdbcImpl(connectionManager);
    }


    @Override
    public boolean isSame(String login, String password) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        return userDao.isSame(user);
    }
}
