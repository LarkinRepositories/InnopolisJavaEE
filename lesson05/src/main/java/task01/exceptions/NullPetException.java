package task01.exceptions;

import java.io.IOException;

public class NullPetException extends IOException {
    public NullPetException() {
    }

    public NullPetException(String s) {
        super(s);
    }
}
