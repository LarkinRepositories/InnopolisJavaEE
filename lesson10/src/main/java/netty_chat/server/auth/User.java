package netty_chat.server.auth;


import lombok.Getter;

import java.util.concurrent.atomic.AtomicBoolean;

public class User {
    @Getter
    private final String username;
    @Getter
    private final String password;
    @Getter
    private final AtomicBoolean active;

    public User(String username, String password) {
        if (username == null) throw new IllegalArgumentException("username is null ");
        if (password == null) throw new IllegalArgumentException("password is null");
        this.username = username;
        this.password = password;
        this.active = new AtomicBoolean(false);
    }

    public boolean correctPassword(String expectedPassword) {
        return this.password.equals(expectedPassword);
    }

    public boolean sessionStart() {
        return active.compareAndSet(false, true);
    }

    public boolean sessionClosed() {
        return active.compareAndSet(true, false);
    }
}
