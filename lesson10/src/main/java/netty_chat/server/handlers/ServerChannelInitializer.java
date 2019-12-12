package netty_chat.server.handlers;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.StandardCharsets;

public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    private static final StringDecoder STRING_DECODER = new StringDecoder(StandardCharsets.UTF_8);
    private static final StringEncoder STRING_ENCODER = new StringEncoder(StandardCharsets.UTF_8);

    private static final CommandDecoder COMMAND_DECODER = new CommandDecoder();
    private static final ChatServerHandler CHAT_SERVER_HANDLER = new ChatServerHandler();


    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        ch.pipeline().addLast(STRING_DECODER);
        ch.pipeline().addLast(STRING_ENCODER);
        ch.pipeline().addLast(COMMAND_DECODER);
        ch.pipeline().addLast(CHAT_SERVER_HANDLER);
    }
}
