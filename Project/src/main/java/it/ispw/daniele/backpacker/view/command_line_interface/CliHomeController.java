package it.ispw.daniele.backpacker.view.command_line_interface;

import it.ispw.daniele.backpacker.bean.HomeBean;
import it.ispw.daniele.backpacker.controller.search.SearchController;
import it.ispw.daniele.backpacker.exceptions.AddressNotFoundException;
import it.ispw.daniele.backpacker.exceptions.CityNotFoundException;
import it.ispw.daniele.backpacker.exceptions.GenericException;
import it.ispw.daniele.backpacker.exceptions.MonumentNotFoundException;
import it.ispw.daniele.backpacker.view.utils_view.InterfaceController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import static it.ispw.daniele.backpacker.view.command_line_interface.CLI.*;

public class CliHomeController extends InterfaceController {

    public void init(Scanner scanner) throws IOException, AddressNotFoundException, CityNotFoundException, MonumentNotFoundException {

        System.out.print("\033[H\033[2J");
        System.out.println(BOLD + "SEARCH PAGE\n" + RESET);
        System.out.flush();

        System.out.println("Country:");
        String country = scanner.nextLine();
        System.out.flush();

        System.out.println("City:");
        String city = scanner.nextLine();
        System.out.flush();


        System.out.println("Address:");
        String address = scanner.nextLine();
        System.out.flush();


        System.out.println("Restaurant: [Yes or No]");
        String restaurant = scanner.nextLine();
        System.out.flush();


        System.out.println("Range:");
        String range = scanner.nextLine();
        System.out.flush();



        HomeBean homeBean = this.setHomeBean(country, city, address, restaurant, range);//new HomeBean();
//        homeBean.setCountry(country);
//        homeBean.setCity(city);
//        homeBean.setAddress(address);
//        homeBean.setRestaurant(restaurant);
//        homeBean.setRange(range);
        try{
        if (country.equals("") || city.equals("") || address.equals("")) {
            throw new FileNotFoundException("ERROR");
        }
        SearchController sc = new SearchController();
        sc.checkInput(homeBean);

        CliResultController crc = new CliResultController();
        crc.init(homeBean);
        //CliUserGraphicChange.getInstance().switchToResult(country, city, address, restaurant, range);
    } catch (CityNotFoundException | AddressNotFoundException | MonumentNotFoundException exception) {
        System.out.println(RED + exception.getMessage() + RESET + "\n");
    } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (GenericException e) {
            throw new RuntimeException(e);
        }

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
