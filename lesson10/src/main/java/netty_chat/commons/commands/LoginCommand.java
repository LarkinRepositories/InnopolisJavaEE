package netty_chat.commons.commands;

import lombok.Getter;

public class LoginCommand extends Command {
    @Getter
    private final String name;
    @Getter
    private final String pass;

    public LoginCommand(String[] args) {
        super(args);
        if (args.length != 2) throw new IllegalArgumentException("invalid arguments for /login command");
        this.name = args[0];
        this.pass = args[1];
    }
}
