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
//    private void run() {
//        EventLoopGroup bossGroup = new NioEventLoopGroup();
//        EventLoopGroup workerGroup = new NioEventLoopGroup();
//
//        try {
//            ServerBootstrap server = new ServerBootstrap();
//            server.group(bossGroup, workerGroup)
//                    .channel(NioServerSocketChannel.class)
//                    .childHandler(new ServerChannelInitializer())
////                    .childHandler(new ChannelInitializer<SocketChannel>() {
////                        @Override
////                        protected void initChannel(SocketChannel socketChannel) throws Exception {
////                            socketChannel.pipeline().addLast(
//////                                    new AuthServiceHandler(),
////                                    new MessageEncoder(),
////                                    new MessageDecoder(),
////                                    new ServerChatHandler());
////                        }
////                    })
//                    .childOption(ChannelOption.SO_KEEPALIVE, true);
//            server.bind(PORT).sync().channel().closeFuture().sync();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } finally {
//            workerGroup.shutdownGracefully();
//            bossGroup.shutdownGracefully();
//        }
//    }
}
