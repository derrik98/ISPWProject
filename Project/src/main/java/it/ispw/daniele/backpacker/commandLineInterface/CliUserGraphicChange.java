package it.ispw.daniele.backpacker.commandLineInterface;

import it.ispw.daniele.backpacker.utils.Roles;

import java.util.Scanner;

import static it.ispw.daniele.backpacker.commandLineInterface.CLI.RED;
import static it.ispw.daniele.backpacker.commandLineInterface.CLI.RESET;

public class CliUserGraphicChange extends CliGuiChangeTemplate{

    private static CliUserGraphicChange instance = null;

    private CliUserGraphicChange(){
        whoAmI = Roles.USER;
    }

    public static CliUserGraphicChange getInstance(){
        if(instance == null){
            instance = new CliUserGraphicChange();
        }
        return instance;
    }

    public void switchToHomePage(Scanner scanner) {

        System.out.println("-----------------------------------UNDO [u]--");
        System.out.println("---------------------------------------------");
        System.out.println("----------------Country [0]------------------");
        System.out.println("----------------City [1]---------------------");
        System.out.println("---------------------------------------------");
        System.out.println("---------------------------------------------");
                /*FXMLLoader loader = new FXMLLoader();
                FileInputStream fileInputStream = new FileInputStream("src/main/java/it/ispw/daniele/backpacker/fxmlView/Home-Page.fxml");
                Parent fxmlLoader = loader.load(fileInputStream);
                HomeUserController huc = loader.getController();
                scene.setRoot(fxmlLoader);
                huc.init();*/


        /*    }
        });*/
    }

    public void switchToMenuPage(Scanner scanner) {

        do {
            System.out.println("-----------------------------------UNDO [u]--");
            System.out.println("---------------------------------------------");
            System.out.println("----------------Home [0]---------------------");
            System.out.println("----------------Profile [1]------------------");
            System.out.println("----------------Logout [2]-------------------");
            System.out.println("---------------------------------------------");

            System.out.println("Command: ");

            switch (scanner.nextLine()) {
                case "0":
                    CliHomeController homeController = new CliHomeController();
                    homeController.init(scanner);
                    break;
                case "1":
                    CliSignUpController signUpController = new CliSignUpController();
                    signUpController.init(scanner);
                    break;
                case "2":
                    return;
                default:
                    System.out.println(RED + "Command not found" + RESET);
                    break;
            }
            System.out.flush();
        } while (true);
    }
}
