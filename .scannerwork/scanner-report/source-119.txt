package it.ispw.daniele.backpacker.exceptions;

import java.io.Serial;

public class LoginFailException extends EmptyFieldException{

    @Serial
    private static final long serialVersionUID = 0;

    public LoginFailException(String message) {
        super(message);
    }
}
