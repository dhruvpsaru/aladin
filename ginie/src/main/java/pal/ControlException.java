package pal;

/**
 * Created by dhruvr on 29/6/16.
 */
public class ControlException extends RuntimeException {

    public ControlException() {
        super();
    }

    public ControlException(String message) {
        super(message);
    }

    public ControlException(String message, Throwable cause) {
        super(message, cause);
    }

    public ControlException(Throwable cause) {
        super(cause);
    }
}
