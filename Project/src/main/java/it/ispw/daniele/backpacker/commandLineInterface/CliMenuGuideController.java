package it.ispw.daniele.backpacker.commandLineInterface;

import it.ispw.daniele.backpacker.exceptions.AddressNotFoundException;
import it.ispw.daniele.backpacker.exceptions.CityNotFoundException;
import it.ispw.daniele.backpacker.exceptions.MonumentNotFoundException;

import java.io.IOException;
import java.util.Scanner;

import static it.ispw.daniele.backpacker.commandLineInterface.CLI.RED;
import static it.ispw.daniele.backpacker.commandLineInterface.CLI.RESET;

public class CliMenuGuideController {
    public void init() throws AddressNotFoundException, IOException, CityNotFoundException, MonumentNotFoundException {

        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("MENU'------------------------------UNDO [u]--");
            System.out.println("---------------------------------------------");
            System.out.println("----------------Home [0]---------------------");
            System.out.println("----------------Add Itinerary [1]------------");
            System.out.println("----------------Profile [2]------------------");
            System.out.println("----------------Logout [3]-------------------");
            System.out.println("---------------------------------------------");

            System.out.println("Command: ");

            switch (scanner.nextLine()) {
                case "0":
                    CliHomeController homeController = new CliHomeController();
                    homeController.init(scanner);
                    break;
                case "1":
                    CliAddItineraryController cliAddItineraryController = new CliAddItineraryController();
                    cliAddItineraryController.init();
                    break;
                case "2":
                    return;
                default:
                    System.out.println(RED + "Command not found\n" + RESET);
                    break;
            }
        } while (true);
    }
}
