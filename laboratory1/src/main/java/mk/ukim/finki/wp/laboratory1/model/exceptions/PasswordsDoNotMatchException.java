package mk.ukim.finki.wp.laboratory1.model.exceptions;

public class PasswordsDoNotMatchException extends RuntimeException {

    public PasswordsDoNotMatchException() {
        super("Passwords do not match.");
    }


}
