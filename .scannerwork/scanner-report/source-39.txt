package it.ispw.daniele.backpacker;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;


public class HelloController extends HelloApplication {

    private Stage stage = new Stage();

    @FXML
    Label LabelHome;
    @FXML
    Label LabelResult;
    @FXML
    Label LabelLogin;
    @FXML
    Label LabelSignUp;
    @FXML
    Label LabelProfile;
    @FXML
    Slider sliderRange;
    @FXML
    Label labelRange;
    @FXML
    ImageView imageSettings;
    @FXML
    Text textSettings;
    @FXML
    ImageView imageUndo;
    @FXML
    TextField textFieldCountry;
    @FXML
    TextField textFieldCity;
    @FXML
    TextField textFieldAddress;
    @FXML
    Button buttonSearch;
    @FXML
    RadioButton radioButtonRestaurant;
    @FXML
    TextField textFieldUserLogin;
    @FXML
    TextField textFieldPassLogin;
    @FXML
    TextField textFieldNameSetting;
    @FXML
    TextField textFieldSurnameSetting;
    @FXML
    TextField textFieldCurrentPassSetting;
    @FXML
    TextField textFieldNewPassSetting;
    @FXML
    TextField textFieldConfNewPassSetting;
    @FXML
    Label genericUserSignUp;
    @FXML
    Label restaurantOwnerSignUp;


    //VBox vBoxResult = createStackedTitledPanes();

    private static final Image BLUE_FISH = new Image("http://icons.iconarchive.com/icons/fasticon/fish-toys/128/Blue-Fish-icon.png");
    private static final Image RED_FISH = new Image("http://icons.iconarchive.com/icons/fasticon/fish-toys/128/Red-Fish-icon.png");
    private static final Image YELLOW_FISH = new Image("http://icons.iconarchive.com/icons/fasticon/fish-toys/128/Yellow-Fish-icon.png");
    private static final Image GREEN_FISH = new Image("http://icons.iconarchive.com/icons/fasticon/fish-toys/128/Green-Fish-icon.png");


    public void switchToHome() throws IOException {
        fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Home-Page.fxml")));
        stage = (Stage) LabelHome.getScene().getWindow();
        scene = new Scene(fxmlLoader, stage.getScene().getWidth(), stage.getScene().getHeight());
        stage.setScene(scene);
        stackScene.push(scene);
        System.out.println(stackScene);
    }

    @FXML
    public void switchToResult() throws IOException {
        fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Result-Page.fxml")));
        stage = (Stage) LabelResult.getScene().getWindow();
        scene = new Scene(fxmlLoader, stage.getScene().getWidth(), stage.getScene().getHeight());

        stackScene.push(scene);
        stage.setScene(scene);
//        Accordion accordion = new Accordion ();
//        //final TitledPane tps = new TitledPane();
//        TitledPane tpss =new TitledPane();
//        accordion.getPanes().addAll(tpss);
//        accordion.setExpandedPane(tpss);
//        vBoxResult.getChildren().add(accordion);
//        System.out.println(accordion.getPanes());
//        vBoxResult.setPrefHeight(500);
//        vBoxResult.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));


//        stage.setOnShown(e -> {
//            TitledPane title = new TitledPane("Title",
//                    new StackPane(new Label("Graphic to the Right")));
//        ImageView imageView = new ImageView(new Image(getClass().getResource("impostazioni.png").toExternalForm()));
//
//        title.setGraphic(imageView);
//        title.setContentDisplay(ContentDisplay.RIGHT);
//
//        scene.setRoot(titledpane);
//
//        // apply css and force layout of nodes
//        title.applyCss();
//        title.layout();
//
//        // title region
//        Node titleRegion=title.lookup(".title");
//        // padding
//        Insets padding=((StackPane)titleRegion).getPadding();
//        // image width
//        double graphicWidth=imageView.getLayoutBounds().getWidth();
//        // arrow
//        double arrowWidth=titleRegion.lookup(".arrow-button").getLayoutBounds().getWidth();
//        // text
//        double labelWidth=titleRegion.lookup(".text").getLayoutBounds().getWidth();
//
//        double nodesWidth = graphicWidth+padding.getLeft()+padding.getRight()+arrowWidth+labelWidth;
//
//        title.graphicTextGapProperty().bind(title.widthProperty().subtract(nodesWidth));
//        accordionResult.getPanes().add(title);
//        accordionResult.setExpandedPane(title);
//    });
}

    private VBox createStackedTitledPanes() {
        final VBox stackedTitledPanes = new VBox();
        stackedTitledPanes.getChildren().setAll(
                createTitledPane("One Fish", GREEN_FISH),
                createTitledPane("Two Fish", YELLOW_FISH, GREEN_FISH),
                createTitledPane("Red Fish", RED_FISH),
                createTitledPane("Blue Fish", BLUE_FISH)
        );
        ((TitledPane) stackedTitledPanes.getChildren().get(0)).setExpanded(true);

        return stackedTitledPanes;
    }

    public TitledPane createTitledPane(String title, Image... images) {
        FlowPane content = new FlowPane();
        for (Image image : images) {
            ImageView imageView = new ImageView(image);
            content.getChildren().add(imageView);

            FlowPane.setMargin(imageView, new Insets(10));
        }
        content.setAlignment(Pos.TOP_CENTER);

        TitledPane pane = new TitledPane(title, content);
        pane.setExpanded(false);

        return pane;
    }


    public void switchToProfile() throws IOException {
        fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Profile-Page.fxml")));
        stage = (Stage) LabelProfile.getScene().getWindow();
        scene = new Scene(fxmlLoader, stage.getScene().getWidth(), stage.getScene().getHeight());
        stage.setScene(scene);
        stackScene.push(scene);
        System.out.println(stackScene);
    }

    public void switchToLogin() throws IOException {
        fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Login-Page.fxml")));
        stage = (Stage) LabelHome.getScene().getWindow();
        scene = new Scene(fxmlLoader, stage.getScene().getWidth(), stage.getScene().getHeight());
        stage.setScene(scene);
        stackScene.push(scene);
        System.out.println(stackScene);
        //textSettings.setVisible(false);
    }

    public void switchToSignUp() throws IOException {
        fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("SignUp-Page.fxml")));
        stage = (Stage) LabelHome.getScene().getWindow();
        scene = new Scene(fxmlLoader, stage.getScene().getWidth(), stage.getScene().getHeight());
        stage.setScene(scene);
        stackScene.push(scene);
        System.out.println(stackScene);
    }

    public void onSliderChanged() {
        BigDecimal sliderValue = BigDecimal.valueOf(sliderRange.getValue()).setScale(1, RoundingMode.HALF_UP);
        labelRange.setText(sliderValue.setScale(1, RoundingMode.HALF_UP)+" km");
    }

    public void switchToSettings() throws IOException {
        fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Edit-Profile-Page.fxml")));
        stage = (Stage) LabelHome.getScene().getWindow();
        scene = new Scene(fxmlLoader, stage.getScene().getWidth(), stage.getScene().getHeight());
        stage.setScene(scene);
        stackScene.push(scene);
        System.out.println(stackScene);
    }

    public void undoScene() {
        if (stackScene.size() > 1) {
            stage = (Stage) imageUndo.getScene().getWindow().getScene().getWindow();
            stackScene.remove(stackScene.size()-1);
            stage.setScene(stackScene.get(stackScene.size()-1));

            System.out.println(stackScene);
        }
        else{
            imageUndo.isDisable();
            imageUndo.setOpacity(0.5);
            imageUndo.setCursor(null);

        }
    }

    public void SearchRoutes() {
        System.out.println("Country " + textFieldCountry.getText() + "\tCity " + textFieldCity.getText() + "\tAddress " + textFieldAddress.getText() + "\tRadius " + labelRange.getText()
         + "\tRestaurant " + radioButtonRestaurant.isSelected());
    }

    public void googleLogin(MouseEvent mouseEvent) {
        System.out.println("Login with Google");
    }

    public void facebookLogin(MouseEvent mouseEvent) {
        System.out.println("Login with Facebook");
    }

    public void login() {
        System.out.println("User " + textFieldUserLogin.getText() + "\tPass " + textFieldPassLogin.getText());
    }

    public void showInfoSettings(MouseEvent mouseEvent) {
        System.out.println("entrato");
        textSettings.setVisible(true);
        //return textSettings.isVisible();

    }

    public void notShowInfoSettings(MouseEvent mouseEvent) {
        textSettings.setVisible(false);
    }

    public void switchToGenericUserSignUpPage() throws IOException {
        fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("SignUp-Page.fxml")));
        stage = (Stage) LabelProfile.getScene().getWindow();
        scene = new Scene(fxmlLoader, stage.getScene().getWidth(), stage.getScene().getHeight());
        stage.setScene(scene);
        stackScene.push(scene);
        System.out.println(stackScene);
    }

    public void switchToRestaurantOwnerSignUpPage() throws IOException {
        fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Restaurant-Owner-SignUp-Page.fxml")));
        stage = (Stage) LabelProfile.getScene().getWindow();
        scene = new Scene(fxmlLoader, stage.getScene().getWidth(), stage.getScene().getHeight());
        stage.setScene(scene);
        stackScene.push(scene);
        System.out.println(stackScene);
    }
}