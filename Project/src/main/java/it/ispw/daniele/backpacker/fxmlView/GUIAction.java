package it.ispw.daniele.backpacker.fxmlView;

import it.ispw.daniele.backpacker.exceptions.MonumentNotFoundException;

import java.io.IOException;

public interface GUIAction {

    public void action() throws IOException, MonumentNotFoundException;

}
