package netty_chat.commons.messages;



import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

public class WhisperMessage extends Message  {
    private final String acceptor;

    public WhisperMessage(String username, String acceptor, String text) {
        super(username, text);
        this.acceptor = acceptor;
    }

    @Override
    public String toString() {
        return String.format("%s (%s) whispered to %s:\r\n %s\r\n", username, DATE_FORMATTER.format(time), acceptor, text);
    }
}
