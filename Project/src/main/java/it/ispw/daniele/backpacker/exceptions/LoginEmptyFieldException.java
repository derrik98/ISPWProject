package it.ispw.daniele.backpacker.exceptions;

public class LoginEmptyFieldException extends EmptyFieldException{

    private static final long serialVersionUID = 0;

    public LoginEmptyFieldException(String message) {
        super(message);
    }
}
