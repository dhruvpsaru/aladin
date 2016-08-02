package ginie;

/**
 * Created by dhruvr on 29/6/16.
 */
public class GinieException extends RuntimeException {

    public GinieException() {
        super();
    }

    public GinieException(String message) {
        super(message);
    }

    public GinieException(String message, Throwable cause) {
        super(message, cause);
    }

    public GinieException(Throwable cause) {
        super(cause);
    }
}
