package netty_chat.commons.commands;

public abstract class Command {
    public Command(String[] args) {
        if (args == null) throw new IllegalArgumentException("args are null");
    }
}
