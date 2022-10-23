package it.ispw.daniele.backpacker.fxmlView;

import it.ispw.daniele.backpacker.bean.UserBean;
import it.ispw.daniele.backpacker.utils.Roles;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.FileInputStream;
import java.io.IOException;

public class UserGraphicChange extends GUIChangeTemplate{

    private static UserGraphicChange instance = null;

    private UserGraphicChange(){
        whoAmI = Roles.USER;
    }

    public static UserGraphicChange getInstance(){
        if(instance == null){
            instance = new UserGraphicChange();
        }
        return instance;
    }
    @Override
    public void switchToHomePage(Scene scene) {
        this.catcher(new GUIAction() {
            @Override
            public void action() throws IOException {
                FXMLLoader loader = new FXMLLoader();
                FileInputStream fileInputStream = new FileInputStream("src/main/java/it/ispw/daniele/backpacker/fxmlView/Home-Page.fxml");
                Parent fxmlLoader = loader.load(fileInputStream);
                HomeUserController huc = loader.getController();
                scene.setRoot(fxmlLoader);
                huc.init();
                //stackScene.push(fxmlLoader);
                //homeUserController.init();

            }
        });
    }

    public void switchToUserDet(Scene scene, UserBean myUser){//(Scene scene, UserBean ub, String from, String searchstring) {
        this.catcher(new GUIAction() {
            @Override
            public void action() throws IOException {
                FXMLLoader loader = new FXMLLoader();
                FileInputStream fileInputStream = new FileInputStream("src/main/java/it/ispw/daniele/backpacker/fxmlView/User-Details-Page.fxml");
                Parent fxmlLoader = loader.load(fileInputStream);
                UserDetailsController pc = loader.getController();
                scene.setRoot(fxmlLoader);
                pc.init(myUser);
            }
        });
    }

    //public void toProfilePage(Scene scene, UserBean ub, String from, String searchString) {
   /* public void switchToProfilePage(Scene scene) {
        this.catcher(new GUIAction() {
            @Override
            public void action() throws IOException {
                ProfileController pc = new ProfileController();
                FXMLLoader loader = new FXMLLoader();
                FileInputStream fileInputStream = new FileInputStream("src/main/java/it/ispw/daniele/backpacker/fxmlView/User-Details-Page.fxml");
                Parent fxmlLoader = loader.load(fileInputStream);
                loader.setController(pc);
                scene.setRoot(fxmlLoader);
                //                controller.init();
            }
        });
    }*/

    /*public void switchToResultPage(Scene scene) {
        this.catcher(new GUIAction() {
            @Override
            public void action() throws IOException {
                ResultController rc = new ResultController();
                FXMLLoader loader = new FXMLLoader();
                FileInputStream fileInputStream = new FileInputStream("src/main/java/it/ispw/daniele/backpacker/fxmlView/User-Details-Page.fxml");
                Parent fxmlLoader = loader.load(fileInputStream);
                loader.setController(rc);
                scene.setRoot(fxmlLoader);
                //                controller.init();
            }
        });
    }*/
}
