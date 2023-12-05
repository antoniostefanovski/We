package mk.ukim.finki.wp.laboratory1.model.exceptions;

public class PasswordsDoNotMatchException extends RuntimeException {

    public PasswordsDoNotMatchException() {
        super("Wrong password entered.");
    }
}
