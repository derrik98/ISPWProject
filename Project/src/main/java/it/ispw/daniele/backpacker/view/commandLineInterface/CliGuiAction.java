package it.ispw.daniele.backpacker.view.commandLineInterface;

import it.ispw.daniele.backpacker.exceptions.AddressNotFoundException;
import it.ispw.daniele.backpacker.exceptions.CityNotFoundException;
import it.ispw.daniele.backpacker.exceptions.MonumentNotFoundException;

import java.io.IOException;

public interface CliGuiAction {
    public void action() throws IOException, AddressNotFoundException, CityNotFoundException, MonumentNotFoundException;
}
