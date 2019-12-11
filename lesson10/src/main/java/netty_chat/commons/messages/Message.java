package netty_chat.commons.messages;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@AllArgsConstructor
public class Message {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    private final String username;
    private final LocalDateTime time;
    private String text;


    public Message(String username, String text) {
        this(username, LocalDateTime.now(), text);
    }

    @Override
    public String toString() {
        return String.format("%s (%s):\r\n %s\r\n", username, DATE_FORMATTER.format(time), text);
    }
}
