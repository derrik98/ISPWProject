package it.ispw.daniele.backpacker.fxmlView;

import it.ispw.daniele.backpacker.bean.GeneralUserBean;
import it.ispw.daniele.backpacker.controller.login.LoginController;
import it.ispw.daniele.backpacker.exceptions.LoginEmptyFieldException;
import it.ispw.daniele.backpacker.utils.SessionUser;

import java.util.Scanner;

public class CLI {
    public static void main(String[] args) throws LoginEmptyFieldException {
        /*//Trying some variants how to start.

        //String cmd = "C:\\WINDOWS\\system32\\cmd.exe";
        //String[] cmd = {"C:\\WINDOWS\\system32\\cmd.exe","start"};

        String[] cmd = {"C:\\WINDOWS\\system32\\cmd.exe","/c","start"};

        // notepad works fine
        //String notepad = "C:\\WINDOWS\\system32\\notepad.exe";


        try {
            Runtime runtime = Runtime.getRuntime();
            //Process p = runtime.exec(notepad);
            Process p = runtime.exec(cmd);



        }

        catch (java.io.IOException exception) {
            System.out.println("Caught IOException: " + exception.getMessage());

        }*/

        //Istanzia scanner con standard input
        Scanner scan = new Scanner(System.in);

//Chiede di introdurre nome e cognome
        System.out.println("Introduci nome e cognome:");
        String nomeCognome = scan.nextLine(); //il programma adesso prende TUTTO il testo introdotto fino alla pressione di ENTER

//Chiede di introdurre la città
        System.out.println("Introduci città:");
        String citta = scan.nextLine();

        System.out.println("Nome e cognome : " + nomeCognome);
        System.out.println("Città : " + citta);

        GeneralUserBean gub = new GeneralUserBean();
        gub.setUsername(nomeCognome);
        gub.setPassword(citta);

        LoginController controller = new LoginController();
        GeneralUserBean gu;
        try {
            gu = controller.login(gub);
            if(gu == null){
                System.out.println("Login failed!");
            }
            else{
                String role = gu.getRole();

                //SET SESSION GENERAL USER
                SessionUser su = SessionUser.getInstance();
                su.setSession(gu);
System.out.println("Login success");
                switch (role) {
                    /*case "user" -> UserGraphicChange.getInstance().switchToHomePage(this.textFieldUsername.getScene());
                    case "tourist_guide" -> TouristGuideGraphicChange.getInstance().switchToHomePage(this.textFieldUsername.getScene());*/
                    default -> {
                    }
                }
            }
        } catch (LoginEmptyFieldException e) {
            throw new RuntimeException(e);
        }
    }
}
