package task01.exceptions;

import java.io.IOException;

public class DuplicateEntryException extends IOException {
    public DuplicateEntryException() {
    }

    public DuplicateEntryException(String s) {
        super(s);
    }
}
