package it.ispw.daniele.backpacker.commandLineInterface;

import it.ispw.daniele.backpacker.bean.GeneralUserBean;
import it.ispw.daniele.backpacker.controller.login.LoginController;
import it.ispw.daniele.backpacker.exceptions.LoginEmptyFieldException;
import it.ispw.daniele.backpacker.utils.SessionUser;

import java.util.Scanner;

public class CliLoginController {
    public void init(Scanner scan) {

        System.out.print("\033[H\033[2J");
        System.out.flush();

        //Chiede di introdurre nome e cognome
        System.out.println("Username:");
        String username = scan.nextLine(); //il programma adesso prende TUTTO il testo introdotto fino alla pressione di ENTER

//Chiede di introdurre la città
        System.out.println("Password:");
        String password = scan.nextLine();

        System.out.println("Username : " + username);
        System.out.println("Password : " + password);

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
                System.out.println("Login success");
                System.out.println("Login success");
                switch (role) {
                    //case "user" -> UserGraphicChange.getInstance().switchToHomePage(this.textFieldUsername.getScene());
                    //case "tourist_guide" -> TouristGuideGraphicChange.getInstance().switchToHomePage(this.textFieldUsername.getScene());*//*
                    default -> {
                    }
                }
            }
        } catch (
                LoginEmptyFieldException e) {
            throw new RuntimeException(e);
        }
    }
}
