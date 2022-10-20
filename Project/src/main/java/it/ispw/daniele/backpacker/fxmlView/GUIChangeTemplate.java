package it.ispw.daniele.backpacker.fxmlView;

import it.ispw.daniele.backpacker.bean.GeneralUserBean;
import it.ispw.daniele.backpacker.utils.Roles;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class GUIChangeTemplate{

    protected final Logger logger = Logger.getLogger("GraphicChange");

    protected Roles whoAmI;

    public GUIChangeTemplate(){
    }

    public void catcher(GUIAction guiAction){
        try {
            guiAction.action();
        }catch (IOException ioException){
            logger.log(Level.WARNING, ioException.toString(), ioException.getCause());
        }
    }

    @FXML
    public void switchToLogin(Scene scene){
        this.catcher(new GUIAction() {
            @Override
            public void action() throws IOException {
                FXMLLoader loader = new FXMLLoader();
                FileInputStream fileInputStream = new FileInputStream("src/main/java/it/ispw/daniele/backpacker/fxmlView/LoginViewPage.fxml");
                Parent fxmlLoader = loader.load(fileInputStream);
                LoginViewController lvc = loader.getController();
                lvc.init();
                scene.setRoot(fxmlLoader);
            }
        });
    }

    public void switchToResult(Scene scene) {
        this.catcher(new GUIAction() {
            @Override
            public void action() throws IOException {
                FXMLLoader loader = new FXMLLoader();
                FileInputStream fileInputStream = new FileInputStream("src/main/java/it/ispw/daniele/backpacker/fxmlView/Result-Page.fxml");
                Parent fxmlLoader = loader.load(fileInputStream);
                ResultController rc = loader.getController();
                rc.init();
                scene.setRoot(fxmlLoader);


            }
        });
    }

    public void switchToProfile(Scene scene){//(Scene scene, UserBean ub, String from, String searchstring) {
        this.catcher(new GUIAction() {
            @Override
            public void action() throws IOException {
                FXMLLoader loader = new FXMLLoader();
                FileInputStream fileInputStream = new FileInputStream("src/main/java/it/ispw/daniele/backpacker/fxmlView/Profile-Page.fxml");
                Parent fxmlLoader = loader.load(fileInputStream);
                ProfileController pc = loader.getController();
                pc.init();
                scene.setRoot(fxmlLoader);
            }
        });
    }

    public void menuBar (HBox pos, String sel){
        this.catcher(new GUIAction() {
            @Override
            public void action() throws IOException {
                FXMLLoader loader;
                switch (whoAmI){
                    case USER -> {
                        loader = new FXMLLoader();
                        FileInputStream fileInputStream = new FileInputStream("src/main/java/it/ispw/daniele/backpacker/fxmlView/MenuBar.fxml");
                        Parent fxmlLoader = loader.load(fileInputStream);
                        MenuBarController mbc = loader.getController();
                        mbc.init();
                        pos.getChildren().add(fxmlLoader);
                    }
                    case TOURIST_GUIDE -> {
                        loader = new FXMLLoader();
                        FileInputStream fileInputStream = new FileInputStream("src/main/java/it/ispw/daniele/backpacker/fxmlView/TouristGuideMenuBar.fxml");
                        Parent fxmlLoader = loader.load(fileInputStream);
                        TouristGuideMenuBarController gbc = loader.getController();
                        gbc.init();
                        pos.getChildren().add(fxmlLoader);
                    }
                    default -> {
                    }
                }
            }
        });
    }

    public abstract void switchToHomePage(Scene scene) throws IOException;

    public void backButton(Scene scene, String from) {
        this.catcher(new GUIAction() {
            @Override
            public void action() throws IOException {
                //BackController bc = BackControllerFactory.getInstance().creator(whoAmI);
                switch (from){
                    case "home":
                        switchToHomePage(scene);
                    case "result":
                        switchToResult(scene);
                    case "profile":
                        switchToProfile(scene);
                }
                /*FXMLLoader loader = new FXMLLoader();
                FileInputStream fileInputStream = new FileInputStream("src/main/java/it/ispw/daniele/backpacker/fxmlView/Profile-Page.fxml");
                Parent fxmlLoader = loader.load(fileInputStream); // loader.setController(bc);
                scene.setRoot(fxmlLoader);*/
                //ap.getChildren().add(loader.load());
                //bc.init(from, searchString);
            }
        });
    }
}

