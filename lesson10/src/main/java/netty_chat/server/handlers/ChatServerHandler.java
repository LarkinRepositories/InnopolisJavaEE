package netty_chat.server.handlers;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;
import netty_chat.commons.commands.ChatCommand;
import netty_chat.commons.commands.Command;
import netty_chat.commons.commands.LoginCommand;
import netty_chat.commons.commands.LogoutCommand;
import netty_chat.server.auth.AuthService;
import netty_chat.server.auth.AuthServiceImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@ChannelHandler.Sharable
public class ChatServerHandler extends SimpleChannelInboundHandler<Command> {

    private final Map<Class<? extends Command>, CommandHandler> commandHandlerMap = new HashMap<>();
    private final AuthService authService = new AuthServiceImpl();
    private final AttributeKey<String> usernameAttr = AttributeKey.valueOf("username");




    private Function<ChannelHandlerContext, Optional<String>> usernameGetter =
            ctx -> Optional.ofNullable(ctx.channel().attr(usernameAttr).get());

    public ChatServerHandler() {
        initialize();
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        String welcomeMessage = "WELCOME TO THE CHAT SERVER WHERE SARCASM IS A DEFAULT TONE AND EVERY CONVERSATION ENDS WITH WORLD DOMINATION";
        ctx.channel().writeAndFlush(welcomeMessage);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Command command) throws Exception {
        if (commandHandlerMap.containsKey(command.getClass())) {
            commandHandlerMap.get(command.getClass()).handle(channelHandlerContext, command);
        } else {
            channelHandlerContext.writeAndFlush("Error, such command doesn't have a handler implementation");
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    private void initialize() {
        commandHandlerMap.put(LoginCommand.class, new LoginHandler());
        commandHandlerMap.put(LogoutCommand.class, new LogoutHandler());
    }

    private class LoginHandler implements CommandHandler<LoginCommand> {

        @Override
        public void handle(ChannelHandlerContext ctx, LoginCommand command) {
            if (usernameGetter.apply(ctx).isPresent()) {
                ctx.writeAndFlush("this user is already authorized");
                return;
            }
            switch (authService.authenticate(command.getName(), command.getPass())) {
                case INCORRECT_PASSWORD:
                    ctx.writeAndFlush("Login Error: incorrect password");
                    break;
                case ALREADY_AUTHENTICATED:
                    ctx.writeAndFlush("this user is already authorized");
                    break;
                case AUTHENTICATED:
                    ctx.channel().attr(usernameAttr).set(command.getName());
                    ctx.writeAndFlush("Login success");
                    break;
                default:
                    ctx.writeAndFlush("Unexpected login error");
            }
        }
    }

    private class LogoutHandler implements CommandHandler<LogoutCommand> {

        @Override
        public void handle(ChannelHandlerContext ctx, LogoutCommand command) {
            usernameGetter.apply(ctx).ifPresent(authService::logout);
        }
    }

    private class ChatCommandHandler implements CommandHandler<ChatCommand> {

        @Override
        public void handle(ChannelHandlerContext ctx, ChatCommand command) {
            Optional<String> username = usernameGetter.apply(ctx);
            if (username.isPresent()) {
            }
        }
    }
}
