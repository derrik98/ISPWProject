package it.ispw.daniele.backpacker.view.fxmlView;

import it.ispw.daniele.backpacker.bean.GeneralUserBean;
import it.ispw.daniele.backpacker.bean.TouristGuideBean;
import it.ispw.daniele.backpacker.dao.TouristGuideDao;
import it.ispw.daniele.backpacker.entity.TouristGuide;
import it.ispw.daniele.backpacker.utils.Controller;
import it.ispw.daniele.backpacker.utils.FileManager;
import it.ispw.daniele.backpacker.utils.SessionUser;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;

import java.io.File;
import java.util.List;
import java.util.Objects;

public class TGuideDetailsController extends Controller {
    public HBox menuBar;
    public Label username;
    public Label name;
    public Label surname;
    public Label email;
    public Label vat;
    public ImageView imageSettings;
    public Text textSettings;
    public VBox vBoxProfile;
    public ImageView profilePicture;

    private GeneralUserBean gub;
    private final Accordion accordionResult = new Accordion();

    public void switchToSettings(MouseEvent mouseEvent) {
    }

    public void showInfoSettings(MouseEvent mouseEvent) {
    }

    public void notShowInfoSettings(MouseEvent mouseEvent) {
    }

    /*public List<TouristGuideBean> getSearchUser(String searchString, String caller){
        TouristGuideDao ud = new TouristGuideDao();
        List<TouristGuide> l = ud.getSearchUser(searchString, caller);
        return this.convert(l.get(0));
    }*/

    public TouristGuideBean getSearchUser(String searchString, String caller){
        TouristGuideDao ud = new TouristGuideDao();
        List<TouristGuide> l = ud.getSearchUser(searchString, caller);
        return this.convert(l.get(0));
    }

    public void init(TouristGuideBean myUser) {

        TouristGuideGraphicChange tgc = new TouristGuideGraphicChange();
        tgc.menuBar(this.menuBar, "profile");

        gub = SessionUser.getInstance().getSession();

        this.username.setText(this.gub.getUsername());
        this.name.setText(this.gub.getPassword());
        this.email.setText(this.gub.getEmail());

        //List<TouristGuideBean> users = this.getSearchUser("search_tguide", SessionUser.getInstance().getSession().getUsername());
        TouristGuideBean users = this.getSearchUser("search_t_guide", SessionUser.getInstance().getSession().getUsername());

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
        this.vat.setText(users.getIdentificationCode());

        //UserGraphicChange ugc = UserGraphicChange.getInstance();

        //this.username.setText(gu.getUsername());
        //this.name.setText(gu.getPassword());

        //ugc.menuBar(this.menuBar, "profile");

        String path = FileManager.PROFILE + File.separator + users.getProfilePicture();

        File file = new File(path);
        Image image = new Image(file.toURI().toString());
        this.profilePicture.setImage(image);
        this.profilePicture.setFitHeight(150);
        this.profilePicture.setFitWidth(150);

        vBoxProfile.getChildren().add(accordionResult);


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
        vBoxProfile.getChildren().add(accordion);
    }

}
