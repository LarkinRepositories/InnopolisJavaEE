package netty_chat.commons.messages;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

public class ChatMessage extends Message {

    public ChatMessage(String username, String text) {
        super(username, text);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
