package ge.alpin.javakhet.milkfactory.model.exception;

public class NotFoundException extends Exception {
    public NotFoundException(String message) {
        super(message);
    }

    public static void check(boolean expression, String msg) throws NotFoundException {
        if (expression) {
            throw new NotFoundException(msg);
        }
    }
}
