package it.ispw.daniele.backpacker.commandLineInterface;

import it.ispw.daniele.backpacker.bean.GeneralUserBean;
import it.ispw.daniele.backpacker.controller.login.LoginController;
import it.ispw.daniele.backpacker.exceptions.AddressNotFoundException;
import it.ispw.daniele.backpacker.exceptions.CityNotFoundException;
import it.ispw.daniele.backpacker.exceptions.EmptyFieldException;
import it.ispw.daniele.backpacker.exceptions.MonumentNotFoundException;
import it.ispw.daniele.backpacker.utils.SessionUser;

import java.io.IOException;
import java.util.Scanner;

public class CliLoginController {

    public static final String RESET = "\033[0m";  // Text Reset
    public static final String RED = "\033[0;31m";     // RED

    public void init(Scanner scan) {

        System.out.print("\033[H\033[2J");
        System.out.flush();

        //Chiede di introdurre nome e cognome
        System.out.println("Username:");
        String username = scan.nextLine(); //il programma adesso prende TUTTO il testo introdotto fino alla pressione di ENTER

//Chiede di introdurre la città
        System.out.println("Password:");
        String password = scan.nextLine();

        /*System.out.println("Username : " + username);
        System.out.println("Password : " + password);*/

        GeneralUserBean gub = new GeneralUserBean();
        gub.setUsername(username);
        gub.setPassword(password);

        LoginController controller = new LoginController();
        GeneralUserBean gu;
        try {
            gu = controller.login(gub);
            if (gu == null) {
                System.out.println("Login failed!");
            } else {
                String role = gu.getRole();

                //SET SESSION GENERAL USER
                SessionUser su = SessionUser.getInstance();
                su.setSession(gu);

                switch (role) {
                    case "user" -> {
                        CliUserGraphicChange.getInstance().switchToMenuPage(scan);
                        scan.nextLine();
                        //case "tourist_guide" -> TouristGuideGraphicChange.getInstance().switchToHomePage(this.textFieldUsername.getScene());*//*
                    }
                    default -> {
                    }
                }
            }
        } catch (EmptyFieldException e) {
            System.out.println("\n" + RED + e.getMessage() + RESET + "\n");
            //throw new RuntimeException(e);
        } catch (AddressNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CityNotFoundException e) {
            throw new RuntimeException(e);
        } catch (MonumentNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
