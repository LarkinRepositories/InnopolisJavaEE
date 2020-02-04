package mvc_tutorial.db.dao;

import mvc_tutorial.db.model.User;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserDaoImpl implements UserDao {
    private Map<UUID, User> users = new ConcurrentHashMap<>();
    @Override
    public void addUser(User user) {
        users.put(user.getId(), user);
    }
}
