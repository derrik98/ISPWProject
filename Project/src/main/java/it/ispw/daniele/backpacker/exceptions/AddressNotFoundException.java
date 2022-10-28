package it.ispw.daniele.backpacker.exceptions;

import java.io.Serial;

public class AddressNotFoundException extends Exception{

    @Serial
    private static final long serialVersionUID = 0;

    public AddressNotFoundException(String message){
        super(message);
    }

}
