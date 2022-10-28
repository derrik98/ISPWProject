package it.ispw.daniele.backpacker.exceptions;

import java.io.Serial;

public class EmptyFieldException extends Exception{

    @Serial
    private static final long serialVersionUID = 0;

    public EmptyFieldException(String message){
        super(message);
    }
}
