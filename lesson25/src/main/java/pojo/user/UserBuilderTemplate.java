package pojo.user;

public abstract class UserBuilderTemplate {
    public final User run(String...args) {
        setId(Integer.valueOf(args[0]));
        setLogin(args[1]);
        setPassword(args[2]);
        setPhone(args[3]);
        setEmail(args[4]);

        return build();
    }

    abstract void setId(Integer id);
    abstract void setLogin(String login);
    abstract void setPassword(String password);
    abstract void setPhone(String phone);
    abstract void setEmail(String email);

    abstract User build();
}
