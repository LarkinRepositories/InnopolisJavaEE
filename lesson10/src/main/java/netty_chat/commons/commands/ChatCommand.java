package netty_chat.commons.commands;

import lombok.Getter;

public class ChatCommand extends Command {
    @Getter
    private final String message;

    public ChatCommand(String[] args) {
        super(args);
        if (args.length != 1) throw new IllegalArgumentException("chat message is empty");
        this.message = args[0];
    }

}
