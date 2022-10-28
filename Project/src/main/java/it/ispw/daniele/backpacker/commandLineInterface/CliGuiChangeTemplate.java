package it.ispw.daniele.backpacker.commandLineInterface;

import it.ispw.daniele.backpacker.fxmlView.*;
import it.ispw.daniele.backpacker.utils.Roles;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class CliGuiChangeTemplate {
    protected final Logger logger = Logger.getLogger("GraphicChange");

    protected Roles whoAmI;

    public CliGuiChangeTemplate(){
    }

    public void catcher(CliGuiAction cGuiAction){
        try {
            cGuiAction.action();
        }catch (IOException ioException){
            logger.log(Level.WARNING, ioException.toString(), ioException.getCause());
        }
    }

    public void switchToLogin(Scanner scanner){
        this.catcher((CliGuiAction) () -> {
            CliLoginController clc = new CliLoginController();
            clc.init(scanner);
            /*FXMLLoader loader = new FXMLLoader();
            FileInputStream fileInputStream = new FileInputStream("src/main/java/it/ispw/daniele/backpacker/fxmlView/LoginView-Page.fxml");
            Parent fxmlLoader = loader.load(fileInputStream);
            LoginViewController lvc = loader.getController();
            scene.setRoot(fxmlLoader);
            lvc.init();*/
        });
    }

    public void switchToResult(Scanner scanner) {
        this.catcher(() -> {
            /*FXMLLoader loader = new FXMLLoader();
            FileInputStream fileInputStream = new FileInputStream("src/main/java/it/ispw/daniele/backpacker/fxmlView/Result-Page.fxml");
            Parent fxmlLoader = loader.load(fileInputStream);*/
            CliResultController rc = new CliResultController();
            //scene.setRoot(fxmlLoader);
            rc.init(scanner);


        });
    }

    /*public void switchToProfile(Scene scene, UserBean myUser){//(Scene scene, UserBean ub, String from, String searchstring) {
        this.catcher(new GUIAction() {
            @Override
            public void action() throws IOException {
                FXMLLoader loader = new FXMLLoader();
                FileInputStream fileInputStream = new FileInputStream("src/main/java/it/ispw/daniele/backpacker/fxmlView/User-Details-Page.fxml");
                Parent fxmlLoader = loader.load(fileInputStream);
                ProfileController pc = loader.getController();
                scene.setRoot(fxmlLoader);
                pc.init(myUser);
            }
        });
    }*/

    public void menuBar (HBox pos, String sel){
        this.catcher(new CliGuiAction() {
            @Override
            public void action() throws IOException {
                FXMLLoader loader;
                switch (whoAmI){
                    case USER -> {
                        loader = new FXMLLoader();
                        FileInputStream fileInputStream = new FileInputStream("src/main/java/it/ispw/daniele/backpacker/fxmlView/MenuBar.fxml");
                        Parent fxmlLoader = loader.load(fileInputStream);
                        MenuBarController mbc = loader.getController();
                        pos.getChildren().add(fxmlLoader);
                        mbc.init(sel);
                    }
                    case TOURIST_GUIDE -> {
                        loader = new FXMLLoader();
                        FileInputStream fileInputStream = new FileInputStream("src/main/java/it/ispw/daniele/backpacker/fxmlView/TouristGuideMenuBar.fxml");
                        Parent fxmlLoader = loader.load(fileInputStream);
                        TouristGuideMenuBarController gbc = loader.getController();
                        pos.getChildren().add(fxmlLoader);
                        gbc.init(sel);
                    }
                    default -> {
                    }
                }
            }
        });
    }

    public abstract void switchToHomePage(Scanner scanner) throws IOException;

}
