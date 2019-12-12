package netty_chat.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import netty_chat.server.handlers.ServerChannelInitializer;

public class ChatServerApp {
    private static final int PORT = 8189;

    public static void main(String[] args) {
        try {
            new ChatServerApp().run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() throws Exception {
        final EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(eventLoopGroup)
                    .channel(NioServerSocketChannel.class)
//                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ServerChannelInitializer());

            ChannelFuture future = bootstrap.bind(PORT).sync();
            future.channel().closeFuture().sync();
        } finally {
            eventLoopGroup.shutdownGracefully().sync();
        }
    }
}
