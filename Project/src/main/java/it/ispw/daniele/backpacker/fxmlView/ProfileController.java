package it.ispw.daniele.backpacker.fxmlView;

import it.ispw.daniele.backpacker.bean.GeneralUserBean;
import it.ispw.daniele.backpacker.bean.TouristGuideBean;
import it.ispw.daniele.backpacker.bean.UserBean;
import it.ispw.daniele.backpacker.dao.UserDAO;
import it.ispw.daniele.backpacker.entity.User;
import it.ispw.daniele.backpacker.utils.Roles;
import it.ispw.daniele.backpacker.utils.SessionUser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

    @FXML
    private ImageView imageSettings;
    @FXML
    private Text textSettings;
    @FXML
    private VBox vBoxProfile;
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

    public void init() {

        //init controller
        UserGraphicChange ugc = UserGraphicChange.getInstance();
        //FriendsController fc=new FriendsController();
        //init menuBar
        //ugc.menuBar(this.menuBar,"friends");

        //init labels
        //this.username = new Label(ub.getUsername());
       // this.username.setText(ub.getUsername());
//        this.name.setText(ub.getName());
//        this.surname.setText(ub.getSurname());
        //this.email.setText(ub.getEmail());

        GeneralUserBean gu= SessionUser.getInstance().getSession();
        this.username.setText(gu.getUsername());
        this.name.setText(gu.getEmail());

        //init buttons
//        GeneralUserBean gu = SessionUser.getInstance().getSession();
//        boolean isFriend = fc.isFriend(gu, ub);
//        String who = fc.whoSentRequest(gu, ub);
//
//        String style = "-fx-background-color:  #F5CB5C";
//
//        Button btn1 = new Button();
//        btn1.setStyle(style);
//        Button btn2 = null;
    }

    public void switchToSettings() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        FileInputStream fileInputStream = new FileInputStream("src/main/resources/it/ispw/daniele/backpacker/Edit-Profile-Page.fxml");
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

            UserGraphicChange ugc = UserGraphicChange.getInstance();
            ugc.menuBar(this.menuBar, "profile");
            System.out.println(SessionUser.getInstance().getSession().getRole() + Roles.USER.name().toLowerCase());
            gub = SessionUser.getInstance().getSession();

            this.username.setText(this.gub.getUsername());
            this.name.setText(this.gub.getPassword());
            this.email.setText(this.gub.getEmail());

            System.out.println("Email " + this.gub.getEmail());
            System.out.println("Pass " + this.gub.getPassword());
            System.out.println("User " + this.gub.getUsername());

            System.out.println(gub);

            List<UserBean> users = this.getSearchUser("searchuser", SessionUser.getInstance().getSession().getUsername());
        System.out.println(SessionUser.getInstance().getSession().getUsername());
            System.out.println("aaaaa" + users);

            for(int i = 0;i < users.size(); i++){
                System.out.println(users.get(i).getName());
                System.out.println(users.get(i).getSurname());
                System.out.println(users.get(i).getEmail());
                System.out.println(users.get(i).getPassword());
                System.out.println(users.get(i).getUsername());
            }

        this.username.setText(users.get(0).getUsername());
        this.name.setText(users.get(0).getName());
        this.email.setText(users.get(0).getEmail());
        this.surname.setText(users.get(0).getSurname());

        //UserGraphicChange ugc = UserGraphicChange.getInstance();

        //this.username.setText(gu.getUsername());
        //this.name.setText(gu.getPassword());

        //ugc.menuBar(this.menuBar, "profile");

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

    public void init(UserBean ub, String from, String searchstring) {

        UserGraphicChange ugc = UserGraphicChange.getInstance();
        ugc.menuBar(this.menuBar, "profile");

        this.email.setText(ub.getEmail());

    }

    public List<UserBean> getSearchUser(String searchString, String caller){
        UserDAO ud = new UserDAO();
        List<User> l = ud.getSearchUser(searchString, caller);
        return this.convert(l);
    }

    private List<UserBean> convert(List<User> l) {
        List<UserBean> lb = new ArrayList<>();
        for(int i = 0; i < l.size(); i++){
            User u = l.get(i);
            UserBean ub = new UserBean();
            ub.setUsername(u.getUsername());
            ub.setName(u.getName());
            ub.setSurname(u.getSurname());
            ub.setProfilePicture(u.getProfilePicture());
            ub.setEmail(u.getEmail());
            lb.add(ub);
        }
        return lb;
    }
}
