package netty_chat.server.handlers;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.GlobalEventExecutor;
import netty_chat.commons.commands.*;
import netty_chat.commons.сhatroom.ChatRoom;
import netty_chat.server.auth.AuthService;
import netty_chat.server.auth.AuthServiceImpl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

@ChannelHandler.Sharable
public class ChatServerHandler extends SimpleChannelInboundHandler<Command> {
    private final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private final Map<Class<? extends Command>, CommandHandler> commandHandlerMap = new HashMap<>();
    private final AuthService authService = new AuthServiceImpl();
    private final ConcurrentHashMap<String, ChatRoom> chatRoomMap = new ConcurrentHashMap<>();

    private final AttributeKey<String> usernameAttr = AttributeKey.valueOf("username");
    private final AttributeKey<String> chatRoomAttr = AttributeKey.valueOf("chatRoom");





    private Function<ChannelHandlerContext, Optional<String>> usernameGetter =
            ctx -> Optional.ofNullable(ctx.channel().attr(usernameAttr).get());

    private Function<ChannelHandlerContext, Optional<String>> chatRoomGetter =
            ctx -> Optional.ofNullable(ctx.channel().attr(chatRoomAttr).get());



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
//        ctx.writeAndFlush("invalid command, try /help to see available commands list");
        ctx.writeAndFlush("invalid command");
    }

    /**
     * Метод инициализации
     */
    private void initialize() {
        commandHandlerMap.put(LoginCommand.class, new LoginHandler());
        commandHandlerMap.put(LogoutCommand.class, new LogoutHandler());
        commandHandlerMap.put(JoinChannelCommand.class, new JoinChannelHandler());
        commandHandlerMap.put(LeaveCommand.class, new LeaveChannelHandler());
        commandHandlerMap.put(ChatCommand.class, new ChatCommandHandler());
        commandHandlerMap.put(WhisperCommand.class, new WhisperCommandHandler());
    }


    /**
     * Обработчик команды логина
     */
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

    /**
     * Обработчик команды выхода из чата
     */
    private class LogoutHandler implements CommandHandler<LogoutCommand> {

        @Override
        public void handle(ChannelHandlerContext ctx, LogoutCommand command) {
            usernameGetter.apply(ctx).ifPresent(authService::logout);
            ctx.writeAndFlush("logout success...closing");
            ctx.channel().close();
        }
    }

    private class JoinChannelHandler implements CommandHandler<JoinChannelCommand> {

        @Override
        public void handle(ChannelHandlerContext ctx, JoinChannelCommand command) {
            Optional<String> username = usernameGetter.apply(ctx);
            if (!username.isPresent()) {
                ctx.writeAndFlush("Anonymous attempt to join. Please authorize first");
                return;
            }

            Optional<String> chatRoomName = chatRoomGetter.apply(ctx);

            if(chatRoomName.map(chatRoom -> chatRoom.equals(command.getChatRoom())).orElse(false)) {
                ctx.writeAndFlush("You've already joined that channel");
                return;
            }

            chatRoomName.ifPresent(chatRoom -> chatRoomMap.get(chatRoom).leave(ctx.channel(), username.get()));
            chatRoomMap.putIfAbsent(command.getChatRoom(), new ChatRoom(
                    new DefaultChannelGroup(GlobalEventExecutor.INSTANCE)));

            ChatRoom chatRoom = chatRoomMap.get(command.getChatRoom());

            if (chatRoom.join(ctx.channel(), username.get())) {
                ctx.channel().attr(chatRoomAttr).set(command.getChatRoom());
                ctx.writeAndFlush("You've successfully joined a channel");
            }
        }
    }


    private class LeaveChannelHandler implements CommandHandler<LeaveCommand> {

        @Override
        public void handle(ChannelHandlerContext ctx, LeaveCommand command) {
            usernameGetter.apply(ctx).ifPresent(username -> {
                chatRoomGetter.apply(ctx).ifPresent(chatChannel -> chatRoomMap.get(chatChannel)
                        .leave(ctx.channel(), username));
                authService.logout(username);
            });
            ctx.writeAndFlush("you left the channel");
        }
    }

    /**
     * Обработчик команды добавления сообщения в чат
     */
    private class ChatCommandHandler implements CommandHandler<ChatCommand> {

        @Override
        public void handle(ChannelHandlerContext ctx, ChatCommand command) {
            Optional<String> username = usernameGetter.apply(ctx);
            Optional<String> chatRoomName = chatRoomGetter.apply(ctx);

            if (username.isPresent() && chatRoomName.isPresent()) {
                chatRoomMap.get(chatRoomName.get()).broadcast(username.get(), command.getMessage());
            } else  {
                String msg = !username.isPresent() ? "You are not authorized. Please use /login command to authorize" : "There's no such channel";
                ctx.writeAndFlush(msg);
            }
        }
    }

    private class WhisperCommandHandler implements CommandHandler<WhisperCommand> {

        @Override
        public void handle(ChannelHandlerContext ctx, WhisperCommand command) {
            Optional<String> username = usernameGetter.apply(ctx);
            Optional<String> chatRoomName = chatRoomGetter.apply(ctx);
            if (username.isPresent() && chatRoomName.isPresent()) {
                chatRoomMap.get(chatRoomName.get()).whisper(username.get(), command.getAcceptor(), command.getMessage());
            } else {
                String msg = !username.isPresent() ? "You are not authorized. Please use /login command to authorize" : "There's no such channel";
                ctx.writeAndFlush(msg);
            }
        }
    }
}
