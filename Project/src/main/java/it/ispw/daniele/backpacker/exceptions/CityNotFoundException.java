package it.ispw.daniele.backpacker.exceptions;

public class CityNotFoundException extends Exception {

    private static final long serialVersionUID = 0;

    public CityNotFoundException(String message){
        super(message);
    }

}
