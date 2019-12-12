package netty_chat.commons.commands;

import lombok.Getter;

import java.util.Arrays;
import java.util.stream.Collectors;

public class WhisperCommand extends Command {
    @Getter
    private final String acceptor;
    @Getter
    private final String message;

    public WhisperCommand(String[] args) {
        super(args);
        if (args.length < 2) throw new IllegalArgumentException("Illegal whisper command arguments");
        this.acceptor = args[0];
        this.message = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
    }
}
