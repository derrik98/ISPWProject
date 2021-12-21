package it.ispw.daniele.backpacker.exceptions;

public class AddressNotFoundException extends Exception{

    private static final long serialVersionUID = 0;

    public AddressNotFoundException(String message){
        super(message);
    }

}
