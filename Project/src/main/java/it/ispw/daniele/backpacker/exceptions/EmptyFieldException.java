package it.ispw.daniele.backpacker.exceptions;

public class EmptyFieldException extends Exception{

    private static final long serialVersionUID = 0;

    public EmptyFieldException(String message){
        super(message);
    }
}
