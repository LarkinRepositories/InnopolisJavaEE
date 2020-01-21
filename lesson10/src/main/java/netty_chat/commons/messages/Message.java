package netty_chat.commons.messages;

import lombok.AllArgsConstructor;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;


@AllArgsConstructor
public abstract class Message {
    protected static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    protected final String username;
    protected final LocalDateTime time;
    protected String text;


    public Message(String username, String text) {
        this(username, LocalDateTime.now(), text);
    }

    @Override
    public String toString() {
       return String.format("%s (%s):\r\n %s\r\n", username, DATE_FORMATTER.format(time), text);
    }
}
