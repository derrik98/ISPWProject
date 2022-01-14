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

    public void switchToHomepage(Scene scene) throws IOException {
        this.catcher(new GUIAction() {
            @Override
            public void action() throws IOException {
                HomeUserController homeUserController = new HomeUserController();
                FXMLLoader loader = new FXMLLoader();
                FileInputStream fileInputStream = new FileInputStream("src/main/java/it/ispw/daniele/backpacker/fxmlView/Home-Page.fxml");
                Parent fxmlLoader = loader.load(fileInputStream);
                scene.setRoot(fxmlLoader);
                //loader.setController(homeUserController);
                //stackScene.push(fxmlLoader);
                //homeUserController.init();
            }
        });
    }

    //public void toProfilePage(Scene scene, UserBean ub, String from, String searchString) {
//        public void switchToProfilePage(Scene scene) {
//        this.catcher(new GUIAction() {
//            @Override
//            public void action() throws IOException {
//                ProfileController controller = new ProfileController();
//                FXMLLoader loader=  new FXMLLoader(getClass().getResource("src/main/java/it/ispw/daniele/backpacker/fxmlView/Home-Page.fxml"));
//                loader.setController(controller);
//                scene.setRoot(loader.load());
//                controller.init();
//            }
//        });
 //   }
}
