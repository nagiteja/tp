package exceptions;

/**
 * Represents the exceptions to be thrown by class methods to catch predictable errors.
 */

public class NusfrException extends Exception {

    private String exceptionMessage;

    public NusfrException(String exceptionMessage) {

        this.exceptionMessage = exceptionMessage;
    }

    public String getMessage() {
        return this.exceptionMessage;
    }
}
