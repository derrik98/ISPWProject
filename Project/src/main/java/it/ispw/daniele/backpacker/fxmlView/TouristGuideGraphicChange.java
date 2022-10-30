package it.ispw.daniele.backpacker.fxmlView;

import it.ispw.daniele.backpacker.bean.TouristGuideBean;
import it.ispw.daniele.backpacker.utils.Roles;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.FileInputStream;
import java.io.IOException;

public class TouristGuideGraphicChange extends GUIChangeTemplate{

    private static TouristGuideGraphicChange instance = null;

    TouristGuideGraphicChange(){
        whoAmI = Roles.TOURIST_GUIDE;
    }

    public static TouristGuideGraphicChange getInstance(){
        if(instance == null){
            instance = new TouristGuideGraphicChange();
        }
        return instance;
    }

    public void switchToHomePage(Scene scene) {
        this.catcher(() -> {
            FXMLLoader loader = new FXMLLoader();
            FileInputStream fileInputStream = new FileInputStream("Project/src/main/java/it/ispw/daniele/backpacker/fxmlView/Home-Page.fxml");
            Parent fxmlLoader = loader.load(fileInputStream);
            HomeUserController huc = loader.getController();
            scene.setRoot(fxmlLoader);
            huc.init();
        });
    }


    public void switchToAddItinerary(Scene scene) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        FileInputStream fileInputStream = new FileInputStream("Project/src/main/java/it/ispw/daniele/backpacker/fxmlView/Add-Itinerary-Page.fxml");
        Parent fxmlLoader = loader.load(fileInputStream);
        scene.setRoot(fxmlLoader);
    }

    public void switchToNotifications(Scene scene) {
    }

    public void switchToTGuideDet(Scene scene, TouristGuideBean myUser){//(Scene scene, UserBean ub, String from, String searchstring) {
        this.catcher(new GUIAction() {
            @Override
            public void action() throws IOException {
                FXMLLoader loader = new FXMLLoader();
                FileInputStream fileInputStream = new FileInputStream("Project/src/main/java/it/ispw/daniele/backpacker/fxmlView/TGuide-Details-Page.fxml");
                Parent fxmlLoader = loader.load(fileInputStream);
                TGuideDetailsController gdc = loader.getController();
                scene.setRoot(fxmlLoader);
                gdc.init(myUser);
            }
        });
    }
}
