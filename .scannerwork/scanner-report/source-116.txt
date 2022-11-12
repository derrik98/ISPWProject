package it.ispw.daniele.backpacker.exceptions;

import java.io.Serial;

public class CityNotFoundException extends Exception {

    @Serial
    private static final long serialVersionUID = 0;

    public CityNotFoundException(String message){
        super(message);
    }

}
