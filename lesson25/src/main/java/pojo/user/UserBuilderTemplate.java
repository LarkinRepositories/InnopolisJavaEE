package pojo.user;

public abstract class UserBuilderTemplate {
    public final User run(String...args) {
        setLogin(args[0]);
        setPassword(args[1]);
        setPhone(args[2]);
        setEmail(args[3]);

        return build();
    }

    abstract void setLogin(String login);
    abstract void setPassword(String password);
    abstract void setPhone(String phone);
    abstract void setEmail(String email);

    abstract User build();
}
