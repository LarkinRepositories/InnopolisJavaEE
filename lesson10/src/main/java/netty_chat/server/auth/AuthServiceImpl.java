package netty_chat.server.auth;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class AuthServiceImpl implements AuthService {
    private final ConcurrentHashMap<String, User> users = new ConcurrentHashMap<>();


    @Override
    public AuthResult authenticate(String username, String password) {
        users.putIfAbsent(username, new User(username, password));
        User user = users.get(username);
        return !user.correctPassword(password)
                ? AuthResult.INCORRECT_PASSWORD : user.sessionStart()
                ? AuthResult.AUTHENTICATED : AuthResult.ALREADY_AUTHENTICATED;
    }

    @Override
    public boolean logout(String username) {
        if (username == null) throw new IllegalArgumentException("username is null | should not be so");
        return Optional.ofNullable(users.get(username)).map(User::sessionClosed).orElse(false);
    }
}
