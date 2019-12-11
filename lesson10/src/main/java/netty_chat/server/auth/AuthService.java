package netty_chat.server.auth;

public interface AuthService {
    AuthResult authenticate(String username, String password);
    boolean logout(String username);
}
