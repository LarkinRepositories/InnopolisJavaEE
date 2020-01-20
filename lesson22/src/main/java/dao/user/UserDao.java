package dao.user;

import pojo.user.User;

public interface UserDao {

    public boolean addUser(User user);
//    public User getUserById(Integer id);
    public boolean isSame(User user);
    public boolean updateUserById(User user);
    public boolean deleteUserById(Integer id);

}
