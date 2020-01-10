package dao;

import pojo.User;

public interface UserDao {

    public boolean addUser(User user);
    public User getUserById(Integer id);
    public boolean updateUserById(User user);
    public boolean deleteUserById(Integer id);

}
