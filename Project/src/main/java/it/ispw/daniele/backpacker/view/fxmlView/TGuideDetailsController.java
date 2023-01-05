package it.ispw.daniele.backpacker.view.fxmlView;

import it.ispw.daniele.backpacker.bean.HomeBean;
import it.ispw.daniele.backpacker.bean.ItineraryBean;
import it.ispw.daniele.backpacker.bean.TouristGuideBean;
import it.ispw.daniele.backpacker.booktour.BookTourController;
import it.ispw.daniele.backpacker.booktour.SaveTour;
import it.ispw.daniele.backpacker.dao.TouristGuideDao;
import it.ispw.daniele.backpacker.entity.TouristGuide;
import it.ispw.daniele.backpacker.utils.Controller;
import it.ispw.daniele.backpacker.utils.FileManager;
import it.ispw.daniele.backpacker.utils.SessionUser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class TGuideDetailsController extends Controller {
    @FXML
    public HBox menuBar;
    @FXML
    public Label username;
    @FXML
    public Label name;
    @FXML
    public Label surname;
    @FXML
    public Label email;
    @FXML
    public Label vat;
    @FXML
    public ImageView imageSettings;
    @FXML
    public Text textSettings;
    @FXML
    public ImageView profilePicture;
    @FXML
    public Text textBookedItineraries;
    @FXML
    public VBox vBoxBooked;
    @FXML
    public Text textSavedItineraries;
    @FXML
    public VBox vBoxSaved;

    public void switchToSettings() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        FileInputStream fileInputStream = new FileInputStream("src/main/resources/it/ispw/daniele/backpacker/Edit-User-Details-Page.fxml");
        Parent fxmlLoader = loader.load(fileInputStream);
        Scene scene = this.imageSettings.getScene();
        scene.setRoot(fxmlLoader);
        //stackScene.push(fxmlLoader);
    }

    public void showInfoSettings() {
        textSettings.setVisible(true);
    }

    public void notShowInfoSettings() {
        textSettings.setVisible(false);
    }

    private TouristGuideBean getSearchUser(String searchString, String caller){
        TouristGuideDao ud = new TouristGuideDao();
        List<TouristGuide> l = ud.getSearchUser(searchString, caller);
        return this.convert(l.get(0));
    }

    public void init(TouristGuideBean myUser) {

        TouristGuideGraphicChange tgc = new TouristGuideGraphicChange();
        tgc.menuBar(this.menuBar, "profile");

        TouristGuideBean tUsers = this.getSearchUser("search_t_guide", SessionUser.getInstance().getSession().getUsername());

        this.username.setText(tUsers.getUsername());
        this.name.setText(tUsers.getName());
        this.email.setText(tUsers.getEmail());
        this.surname.setText(tUsers.getSurname());
        this.vat.setText(tUsers.getIdentificationCode());

        File file = new File(FileManager.PROFILE + File.separator + tUsers.getProfilePicture());
        Image image = new Image(file.toURI().toString());
        this.profilePicture.setImage(image);
        this.profilePicture.setFitHeight(150);
        this.profilePicture.setFitWidth(150);

        BookTourController btc = new BookTourController();
        List<ItineraryBean> booked;
        booked = new BookTourController().getItinerary(tUsers.getUsername(), "user");

        SaveTour st = new SaveTour();
        List<ItineraryBean> saved;
        saved = st.getItinerary(tUsers.getUsername());

        if(booked == null){
            textBookedItineraries.setText(textBookedItineraries.getText() + ": EMPTY");
            System.out.println("EMPTY_DATABASE ");
        }
        else {
            Accordion accordionSuggested = this.createTable(booked, "suggested", "profile", null);
            vBoxBooked.getChildren().addAll(accordionSuggested);
        }
        if(saved == null){
            textSavedItineraries.setText(textSavedItineraries.getText() + ": EMPTY");
            System.out.println("EMPTY_DATABASE ");
        }
        else {
            Accordion accordionSelf = this.createTable(saved, "self", "profile", null);
            vBoxSaved.getChildren().addAll(accordionSelf);
        }

    }

}
