package dao.user;

import pojo.user.User;

import java.util.Collection;

public interface UserDao {

    boolean addUser(User user);
    User getUserById(Integer id);
    boolean isSame(User user);
    boolean updateUserById(User user);
    boolean deleteUserById(Integer id);
    Collection<User> getUsers();

}
