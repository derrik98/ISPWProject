package it.ispw.daniele.backpacker.commandLineInterface;

import it.ispw.daniele.backpacker.utils.Roles;

import java.util.Scanner;

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
}
