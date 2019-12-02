package task03.exceptions;

public class SameAgeAndNameException extends RuntimeException {
    public SameAgeAndNameException() {

    }

    public SameAgeAndNameException(String s) {
        super(s);
    }
}
