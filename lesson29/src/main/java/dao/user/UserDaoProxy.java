package dao.user;

import ConnectionManager.ConnectionManager;
import pojo.user.User;

import java.util.Collection;

public class UserDaoProxy implements UserDao {
    private final UserDao userDao;

    public UserDaoProxy(ConnectionManager connectionManager) {
        this.userDao = new UserDaoJdbcImpl(connectionManager);
    }

    @Override
    public boolean addUser(User user) {
       return userDao.addUser(user);
    }

    @Override
    public User getUserById(Integer id) {
        return userDao.getUserById(id);
    }

    @Override
    public boolean isSame(User user) {
        return userDao.isSame(user);
    }

    @Override
    public boolean updateUserById(User user) {
        return userDao.updateUserById(user);
    }

    @Override
    public boolean deleteUserById(Integer id) {
       return userDao.deleteUserById(id);
    }

    @Override
    public Collection<User> getUsers() {
        Collection<User> users = userDao.getUsers();
        users.forEach(e -> e.setLogin(e.getLogin().concat("proxied")));
        return users;
    }
}
