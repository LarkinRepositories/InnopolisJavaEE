package netty_chat.commons.сhatroom;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import netty_chat.commons.messages.Message;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

public class ChatRoom {
    private final ChannelGroup channels;
    private final ConcurrentHashMap<String, LocalDateTime> users;
    private final ConcurrentLinkedDeque<Message> messageDeque;

    public ChatRoom(ChannelGroup channels) {
        this.channels = channels;
        users = new ConcurrentHashMap<>();
        messageDeque = new ConcurrentLinkedDeque<>();

    }

    /**
     * Метод, добавляет пользователя в канал чата
     * @param channel название канала
     * @param username имя пользователя
     * @return добавился или нет пользователь в канал
     */
    public boolean join(Channel channel, String username) {
        if (channel == null) throw new IllegalArgumentException("channel is null");
        if (username == null) throw new IllegalArgumentException("username is null");
        if (!users.containsKey(username)) {
            if (users.putIfAbsent(username, LocalDateTime.now()) == null) {
                channel.flush();
                channels.add(channel);
                return true;
            }
        }
        return false;
    }

    /**
     * Метод, отвечающий за выход пользователя из канала
     * @param channel
     * @param username
     * @return
     */
    public boolean leave(Channel channel, String username) {
        if (username == null || username.isEmpty()) throw new IllegalArgumentException("username is null or is empty");
        if (channel == null) throw new IllegalArgumentException("channel is null");
        if (users.remove(username) != null) {
            channels.remove(channel);
            return true;
        }
        return false;
    }

    /**
     * Метод, выводит сообщение пользователя в канал
     * @param username имя пользователя
     * @param message сообщение
     */
    public void broadcast(String username, String message) {
        if (username == null || username.isEmpty()) throw new IllegalArgumentException("username is null or is empty");
        if (message == null || message.isEmpty()) throw new IllegalArgumentException("message is null or is empty");
        Message chatMessage = new Message(username, message);
        messageDeque.addLast(chatMessage);
        channels.writeAndFlush(chatMessage.toString()).addListener(future -> {
            messageDeque.removeFirst();
        });
    }

}
