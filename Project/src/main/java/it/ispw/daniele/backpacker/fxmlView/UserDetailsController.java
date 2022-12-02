package it.ispw.daniele.backpacker.fxmlView;

import it.ispw.daniele.backpacker.bean.GeneralUserBean;
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
import javafx.scene.input.MouseEvent;
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

    private final Accordion accordionResult = new Accordion();

    private GeneralUserBean gub;

    public void switchToSettings() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        FileInputStream fileInputStream = new FileInputStream("src/main/resources/it/ispw/daniele/backpacker/Edit-User-Details-Page.fxml");
        Parent fxmlLoader = loader.load(fileInputStream);
        Scene scene = this.imageSettings.getScene();
        scene.setRoot(fxmlLoader);
        //stackScene.push(fxmlLoader);
    }

    public void showInfoSettings(MouseEvent mouseEvent) {
        textSettings.setVisible(true);
    }

    public void notShowInfoSettings(MouseEvent mouseEvent) {
        textSettings.setVisible(false);
    }

    /*public List<UserBean> getSearchUser(String searchString, String caller){
        UserDAO ud = new UserDAO();
        List<User> l = ud.getSearchUser(searchString, caller);
        return this.convert(l);
    }*/

    public UserBean getSearchUser(String searchString, String caller){
        UserDAO ud = new UserDAO();
        List<User> l = ud.getSearchUser(searchString, caller);
        return this.convert(l.get(0));
    }



    public void init(UserBean myUser) {

        UserGraphicChange ugc = UserGraphicChange.getInstance();
        ugc.menuBar(this.menuBar, "profile");

        gub = SessionUser.getInstance().getSession();

        /*this.username.setText(this.gub.getUsername());
        this.name.setText(this.gub.getPassword());
        this.email.setText(this.gub.getEmail());*/

        //List<UserBean> users = this.getSearchUser("search_user", SessionUser.getInstance().getSession().getUsername());
        UserBean users = this.getSearchUser("search_user", SessionUser.getInstance().getSession().getUsername());

        /*for(int i = 0; i < users.size(); i++){
            System.out.println(users.get(i).getName());
            System.out.println(users.get(i).getSurname());
            System.out.println(users.get(i).getEmail());
            System.out.println(users.get(i).getPassword());
            System.out.println(users.get(i).getUsername());
        }*/

        /*this.username.setText(users.get(0).getUsername());
        this.name.setText(users.get(0).getName());
        this.email.setText(users.get(0).getEmail());
        this.surname.setText(users.get(0).getSurname());*/

        this.username.setText(users.getUsername());
        this.name.setText(users.getName());
        this.email.setText(users.getEmail());
        this.surname.setText(users.getSurname());

        String path = FileManager.PROFILE + File.separator + users.getProfilePicture();

        File file = new File(path);
        Image image = new Image(file.toURI().toString());
        this.profilePicture.setImage(image);
        this.profilePicture.setFitHeight(150);
        this.profilePicture.setFitWidth(150);

        //UserGraphicChange ugc = UserGraphicChange.getInstance();

        //this.username.setText(gu.getUsername());
        //this.name.setText(gu.getPassword());

        //ugc.menuBar(this.menuBar, "profile");

        ////vBoxProfile.getChildren().add(accordionResult);

        ResultController r = new ResultController();

        /*BookTourController btc = new BookTourController();
        List<ItineraryBean> it;
        it = btc.getItinerary(users.getUsername(), "user");

        if(it == null){
            System.out.println("EMPTY_DATABASE ");
        }
        else{
            Accordion accordion = r.createTable(it, "suggested");
            vBoxProfile.getChildren().add(accordion);
        }*/

        BookTourController btc = new BookTourController();
        List<ItineraryBean> booked;
        booked = btc.getItinerary(users.getUsername(), "user");

        SaveTour st = new SaveTour();
        List<ItineraryBean> saved;
        saved = st.getItinerary(users.getUsername());

        if(booked == null){
            textBookedItineraries.setText(textBookedItineraries.getText() + ": EMPTY");
            System.out.println("EMPTY_DATABASE ");
        }
        else {
            //selfItinerary.setText("Self Itinerary");

            Accordion accordionSuggested = r.createTable(booked, "suggested", "profile");
            vBoxBooked.getChildren().addAll(accordionSuggested);
        }
        if(saved == null){
            textSavedItineraries.setText(textSavedItineraries.getText() + ": EMPTY");
            System.out.println("EMPTY_DATABASE ");
        }
        else {
            //selfItinerary.setText("Self Itinerary");

            Accordion accordionSelf = r.createTable(saved, "self", "profile");
            vBoxSaved.getChildren().addAll(accordionSelf);
        }

        /*vBoxResultGuide.getChildren().add(accordion);


        Accordion accordion = new Accordion();
        for(int i = 0; i < 4; i++) {

            TitledPane titledPane = new TitledPane();
            titledPane.setCollapsible(false);
            titledPane.setAlignment(Pos.CENTER);
            HBox contentPane = new HBox();
            contentPane.setAlignment(Pos.CENTER);

            contentPane.setPadding(new Insets(0, 10, 0, 35));

            contentPane.minWidthProperty().bind(titledPane.widthProperty());

            HBox region = new HBox();
            region.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(region, Priority.ALWAYS);

            HBox region1 = new HBox();
            region1.setMinWidth(15);
            region1.setMaxWidth(Double.MAX_VALUE);

            Label l = new Label("prova" + i);
            l.setFont(new Font("Arial", 20));
            ImageView ivMap = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/googleMaps.png")).toExternalForm()));
            ivMap.setFitWidth(40);
            ivMap.setFitHeight(40);
            ivMap.setCursor(Cursor.HAND);
            ImageView ivSave = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/delete.png")).toExternalForm()));
            ivSave.setCursor(Cursor.HAND);
            ivSave.setFitWidth(40);
            ivSave.setFitHeight(40);

            ivMap.setOnMouseClicked(mouseEvent -> {
                if (!titledPane.isCollapsible()) {
                    titledPane.setCollapsible(true);
                    titledPane.setExpanded(true);
                } else {
                    titledPane.setCollapsible(false);
                    titledPane.setExpanded(false);

                }
            });

            ivSave.setOnMouseClicked(mouseEvent -> {
                Node s = contentPane.getChildren().get(0);
            });

            contentPane.getChildren().addAll(l, region, ivMap, region1, ivSave);

            titledPane.setGraphic(contentPane);
            WebView webView = new WebView();
            webView.getEngine().load("https://google.com");
            VBox v = new VBox(webView);
            titledPane.setContent(v);
            accordion.getPanes().add(titledPane);
        }
        vBoxProfile.getChildren().add(accordion);*/


    }

}
