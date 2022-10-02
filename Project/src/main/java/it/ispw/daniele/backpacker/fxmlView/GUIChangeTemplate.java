package it.ispw.daniele.backpacker.fxmlView;

import it.ispw.daniele.backpacker.utils.Roles;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
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
                LoginViewController lvc = new LoginViewController();
                FXMLLoader loader = new FXMLLoader();
                FileInputStream fileInputStream = new FileInputStream("src/main/java/it/ispw/daniele/backpacker/fxmlView/LoginViewPage.fxml");
                Parent fxmlLoader = loader.load(fileInputStream);
                loader.setController(lvc);
                scene.setRoot(fxmlLoader);
            }
        });
    }

    public void switchToResult(Scene scene) {
        this.catcher(new GUIAction() {
            @Override
            public void action() throws IOException {
                ResultController rc = new ResultController();
                FXMLLoader loader = new FXMLLoader();
                FileInputStream fileInputStream = new FileInputStream("src/main/java/it/ispw/daniele/backpacker/fxmlView/Result-Page.fxml");
                Parent fxmlLoader = loader.load(fileInputStream);
                loader.setController(rc);
                scene.setRoot(fxmlLoader);
            }
        });
    }

    public void switchToProfile(Scene scene){//(Scene scene, UserBean ub, String from, String searchstring) {
        this.catcher(new GUIAction() {
            @Override
            public void action() throws IOException {
                ProfileController pc = new ProfileController();
                FXMLLoader loader = new FXMLLoader();
                FileInputStream fileInputStream = new FileInputStream("src/main/java/it/ispw/daniele/backpacker/fxmlView/Profile-Page.fxml");
                Parent fxmlLoader = loader.load(fileInputStream);
                loader.setController(pc);
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
                        MenuBarController mbc = new MenuBarController();
                        loader = new FXMLLoader();
                        FileInputStream fileInputStream = new FileInputStream("src/main/java/it/ispw/daniele/backpacker/fxmlView/MenuBar.fxml");
                        Parent fxmlLoader = loader.load(fileInputStream);
                        loader.setController(mbc);
                        pos.getChildren().add(fxmlLoader);

                    }
                    case TOURIST_GUIDE -> {
                        TouristGuideMenuBarController tgmbc = new TouristGuideMenuBarController();
                        loader = new FXMLLoader();
                        FileInputStream fileInputStream = new FileInputStream("src/main/java/it/ispw/daniele/backpacker/fxmlView/TouristGuideMenuBar.fxml");
                        Parent fxmlLoader = loader.load(fileInputStream);
                        loader.setController(tgmbc);
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

