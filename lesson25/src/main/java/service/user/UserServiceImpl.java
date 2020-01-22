package service.user;

import ConnectionManager.ConnectionManager;
import dao.user.UserDao;
import dao.user.UserDaoProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.user.User;

import java.util.Collection;

public class UserServiceImpl implements UserService{
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserDao userDao;

    public UserServiceImpl(ConnectionManager connectionManager) {
        userDao = new UserDaoProxy(connectionManager);
    }


    @Override
    public boolean addUser(String login, String password, String phone, String email) {
       User user = new User.Builder(login, password).withPhone(phone).withEmail(email).build();
       return userDao.addUser(user);
    }

    @Override
    public boolean updateUserById(Integer id, String login, String password, String phone, String email) {
        User user = userDao.getUserById(id);
        user.setLogin(login);
        user.setPassword(password);
        user.setPhone(phone);
        user.setEmail(email);
        return userDao.updateUserById(user);
    }

    @Override
    public boolean deleteUserById(Integer id) {
       return userDao.deleteUserById(id);
    }

    @Override
    public boolean isSame(String login, String password) {
        User user = new User.Builder(login, password).build();
        return userDao.isSame(user);
    }

    @Override
    public User getUserById(Integer id) {
        return userDao.getUserById(id);
    }

    @Override
    public Collection<User> getUsers() {
        return userDao.getUsers();
    }
}
