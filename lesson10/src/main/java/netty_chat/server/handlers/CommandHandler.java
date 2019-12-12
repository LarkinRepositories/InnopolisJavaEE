package netty_chat.server.handlers;

import io.netty.channel.ChannelHandlerContext;
import netty_chat.commons.commands.Command;

public interface CommandHandler<T extends Command> {
    void handle(ChannelHandlerContext ctx, T command);
}
