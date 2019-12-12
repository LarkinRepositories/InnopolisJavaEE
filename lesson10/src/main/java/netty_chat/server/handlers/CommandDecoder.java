package netty_chat.server.handlers;


import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import netty_chat.commons.commands.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@ChannelHandler.Sharable
public class CommandDecoder extends MessageToMessageDecoder<String> {

    private final Map<String, Function<String[], Command>> operationsAndCommandsMap = new HashMap<>();

    public CommandDecoder() {
        operationsAndCommandsMap.put("/login", LoginCommand::new);
        operationsAndCommandsMap.put("/logout", LogoutCommand::new);
        operationsAndCommandsMap.put("/join", JoinChannelCommand::new);
        operationsAndCommandsMap.put("/leave", LeaveCommand::new);
        operationsAndCommandsMap.put("/w", WhisperCommand::new);
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, String msg, List<Object> list) throws Exception {
        Command command;
        if (msg.startsWith("/")) {
            String[] msgParts = msg.trim().split("\\s");
            String prefix = msgParts[0];
            String[] args = msgParts.length > 1 ? Arrays.copyOfRange(msgParts, 1, msgParts.length) : new String[0];
            if (operationsAndCommandsMap.containsKey(prefix)) {
                command = operationsAndCommandsMap.get(prefix).apply(args);
            } else  throw new IllegalArgumentException("invalid command");
        }
        else {
            command = new ChatCommand(new String[]{msg});
        }
        list.add(command);
    }
}
