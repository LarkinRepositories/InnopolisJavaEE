package netty_chat.commons.messages;

import java.time.LocalDateTime;

public class SystemMessage extends Message {
    public SystemMessage(String username, LocalDateTime time, String text) {
        super(username, time, text);
    }
}
