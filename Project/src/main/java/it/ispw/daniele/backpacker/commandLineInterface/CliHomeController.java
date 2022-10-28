package it.ispw.daniele.backpacker.commandLineInterface;

import it.ispw.daniele.backpacker.bean.HomeBean;
import it.ispw.daniele.backpacker.exceptions.AddressNotFoundException;
import it.ispw.daniele.backpacker.exceptions.CityNotFoundException;
import it.ispw.daniele.backpacker.exceptions.MonumentNotFoundException;
import it.ispw.daniele.backpacker.fxmlView.UserGraphicChange;

import java.io.IOException;
import java.util.Scanner;

import static it.ispw.daniele.backpacker.commandLineInterface.CLI.RED;
import static it.ispw.daniele.backpacker.commandLineInterface.CLI.RESET;

public class CliHomeController {
    public void init(Scanner scanner) {

        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println("Country:");
        String country = scanner.nextLine();

        System.out.println("City:");
        String city = scanner.nextLine();

        System.out.println("Address:");
        String address = scanner.nextLine();


        HomeBean homeBean = HomeBean.getInstance();
        homeBean.setCountry(country);
        homeBean.setCity(city);
        homeBean.setAddress(address);

        /*try {
            //homeBean.validate();
            CliUserGraphicChange.getInstance().switchToResult(scanner);
        } catch (CityNotFoundException cnfe) {
            System.out.println(RED + cnfe.getMessage() + RESET);
            //this.textFieldCity.setStyle("-fx-border-style: solid; -fx-border-width: 1; -fx-border-color: red");
            //this.showFeedback(cnfe.getMessage());
        } catch (AddressNotFoundException anfe){
            //anfe.printStackTrace();
            //this.showFeedback(anfe.getMessage());
            //this.textFieldAddress.setStyle("-fx-border-style: solid; -fx-border-width: 1; -fx-border-color: red");
        }catch (MonumentNotFoundException | IOException mnfe){
            //this.showFeedback(mnfe.getMessage());
        }*/

    }
}
