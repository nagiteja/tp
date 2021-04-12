package exceptions;

/**
 * Represents the exceptions to be thrown by class methods to catch predictable errors.
 */
public class NusfrExceptions extends Exception {

    private String exceptionMessage;

    public NusfrExceptions(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getMessage() {
        return this.exceptionMessage;
    }
}
