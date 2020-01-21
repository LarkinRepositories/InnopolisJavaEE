package netty_chat.commons.commands;

import lombok.Getter;

public class JoinChannelCommand extends Command {
    @Getter
    private final String chatRoom;

    public JoinChannelCommand(String[] args) {
        super(args);
        if (args.length != 1) throw new IllegalArgumentException("invalid argument count for /join command");
        this.chatRoom = args[0];
    }
}
