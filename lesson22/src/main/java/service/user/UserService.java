package service.user;

import pojo.user.User;

import java.util.Collection;

public interface UserService {
    boolean addUser(String login, String password, String phone, String email);
    boolean updateUserById(Integer id, String login, String password, String phone, String email);
    boolean deleteUserById(Integer id);
    boolean isSame(String login, String password);
    User getUserById(Integer id);
    Collection<User> getUsers();
}
