package service.user;

import pojo.user.User;

public interface UserService {
//    public boolean addUser(User user);
//    public boolean updateUserById(User user);
//    public boolean deleteUserById(Integer id);
    boolean isSame(String login, String password);

}
