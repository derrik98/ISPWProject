package it.ispw.daniele.backpacker.fxmlView;

import it.ispw.daniele.backpacker.utils.Roles;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TouristGuideGraphicChange extends GUIChangeTemplate{

    private static TouristGuideGraphicChange instance = null;

    private TouristGuideGraphicChange(){
        whoAmI = Roles.TOURIST_GUIDE;
    }

    public static TouristGuideGraphicChange getInstance(){
        if(instance == null){
            instance = new TouristGuideGraphicChange();
        }
        return instance;
    }

    public void switchToHomePage(Scene scene) throws IOException {
        this.catcher(new GUIAction() {
            @Override
            public void action() throws IOException {
                HomeUserController huc = new HomeUserController();
                FXMLLoader loader = new FXMLLoader();
                FileInputStream fileInputStream = new FileInputStream("src/main/java/it/ispw/daniele/backpacker/fxmlView/Home-Page.fxml");
                Parent fxmlLoader = loader.load(fileInputStream);
                loader.setController(huc);
                scene.setRoot(fxmlLoader);
            }
        });
    }


    public void switchToAddItinerary(Scene scene) throws IOException {
        AddItineraryController itineraryController = new AddItineraryController();
        FXMLLoader loader = new FXMLLoader();
        FileInputStream fileInputStream = new FileInputStream("src/main/java/it/ispw/daniele/backpacker/fxmlView/Add-Itinerary-Page.fxml");
        Parent fxmlLoader = loader.load(fileInputStream);
        loader.setController(itineraryController);
        scene.setRoot(fxmlLoader);
    }
}
