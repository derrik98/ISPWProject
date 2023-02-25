package it.ispw.daniele.backpacker.view.fxml_view;

import it.ispw.daniele.backpacker.bean.ItineraryBean;
import it.ispw.daniele.backpacker.bean.UserBean;
import it.ispw.daniele.backpacker.booktour.BookTourController;
import it.ispw.daniele.backpacker.booktour.SaveTour;
import it.ispw.daniele.backpacker.dao.UserDAO;
import it.ispw.daniele.backpacker.entity.User;
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

public class UserDetailsController extends Controller {

    @FXML
    public ImageView profilePicture;
    @FXML
    public Text textBookedItineraries;
    @FXML
    public VBox vBoxBooked;
    @FXML
    public VBox vBoxSaved;
    @FXML
    public Text textSavedItineraries;
    @FXML
    private ImageView imageSettings;
    @FXML
    private Text textSettings;
    @FXML
    private Label username;
    @FXML
    private Label name = new Label();
    @FXML
    private Label surname;
    @FXML
    private Label email;
    @FXML
    private HBox menuBar = new HBox();

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

    private UserBean getSearchUser(String caller){
        UserDAO ud = new UserDAO();
        List<User> l = ud.getSearchUser(caller);
        return this.convert(l.get(0));
    }



    public void init(UserBean myUser) {

        UserGraphicChange ugc = UserGraphicChange.getInstance();
        ugc.menuBar(this.menuBar, "profile");

        UserBean users = this.getSearchUser(SessionUser.getInstance().getSession().getUsername());

        this.username.setText(users.getUsername());
        this.name.setText(users.getName());
        this.email.setText(users.getEmail());
        this.surname.setText(users.getSurname());

        File file = new File(FileManager.PROFILE + File.separator + users.getProfilePicture());
        Image image = new Image(file.toURI().toString());
        this.profilePicture.setImage(image);
        this.profilePicture.setFitHeight(150);
        this.profilePicture.setFitWidth(150);

        //BookTourController btc = new BookTourController();
        List<ItineraryBean> booked;
        booked = new BookTourController().getItinerary(users.getUsername(), "user");//btc.getItinerary(users.getUsername(), "user");

        SaveTour st = new SaveTour();
        List<ItineraryBean> saved;
        saved = st.getItinerary(users.getUsername());

        if(booked == null){
            textBookedItineraries.setText(textBookedItineraries.getText() + ": EMPTY");
        }
        else {
            Accordion accordionSuggested = this.createTable(booked, "suggested", "profile", null);
            vBoxBooked.getChildren().addAll(accordionSuggested);
        }
        if(saved == null){
            textSavedItineraries.setText(textSavedItineraries.getText() + ": EMPTY");
        }
        else {
            Accordion accordionSelf = this.createTable(saved, "self", "profile", null);
            vBoxSaved.getChildren().addAll(accordionSelf);
        }
    }

}
