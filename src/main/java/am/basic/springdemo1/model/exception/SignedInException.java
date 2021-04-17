package am.basic.springdemo1.model.exception;

public class SignedInException extends Exception {
    public SignedInException(String message) {
        super(message);
    }

    public static void check(boolean expression, String msg) throws SignedInException {
        if (expression) {
            throw new SignedInException(msg);
        }
    }
}
