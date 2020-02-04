package MySimpleConfiguration.dao;

import ConnectionManager.ConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import MySimpleConfiguration.pojo.User;

@Component
public class UserDaoImpl implements UserDao {
    private final ConnectionManager connectionManager;

    @Autowired
    UserDaoImpl(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public boolean getUser(User user) {
        return false;
    }
}
