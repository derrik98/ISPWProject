package it.ispw.daniele.backpacker.view.fxml_view;

import it.ispw.daniele.backpacker.bean.HomeBean;
import it.ispw.daniele.backpacker.bean.ItineraryBean;
import it.ispw.daniele.backpacker.booktour.BookTourController;
import it.ispw.daniele.backpacker.controller.search.SearchController;
import it.ispw.daniele.backpacker.exceptions.MonumentNotFoundException;
import it.ispw.daniele.backpacker.utils.Controller;
import it.ispw.daniele.backpacker.utils.Roles;
import it.ispw.daniele.backpacker.utils.SessionUser;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public class ResultController extends Controller {
    @FXML
    public Text suggestedItinerary = new Text();
    @FXML
    public VBox vBoxResultGuide = new VBox();
    @FXML
    public Text selfItinerary = new Text();
    @FXML
    public ImageView guideImage = new ImageView();
    public static final StackPane stackPaneResult = new StackPane();
    @FXML
    public HBox hBoxInput = new HBox();
    @FXML
    public ImageView guideOff;
    @FXML
    public VBox vBoxDynamic = new VBox();
    @FXML
    private Label countrySearch = new Label();
    @FXML
    private Label citySearch = new Label();
    @FXML
    private Label addressSearch = new Label();
    @FXML
    private Label isRestaurant = new Label();
    @FXML
    private Label radiusSearch = new Label();
    @FXML
    private VBox vBoxResult = new VBox();
    @FXML
    private HBox menuBar = new HBox();

    private static final String home = "home";
    private static final String result = "result";

    private static ResultController instance = null;

    public void init(HomeBean homeBean) throws MonumentNotFoundException, NoSuchAlgorithmException {

        if(SessionUser.getInstance().getSession().getRole().equals(Roles.TOURIST_GUIDE.name().toLowerCase())) {
            TouristGuideGraphicChange i = TouristGuideGraphicChange.getInstance();
            i.menuBar(this.menuBar, result);
        }
        else {
            UserGraphicChange ugc = UserGraphicChange.getInstance();
            ugc.menuBar(this.menuBar, result);
        }

        if (!homeBean.getCountry().equals("") && !homeBean.getCity().equals("") && !homeBean.getAddress().equals("")) {
            this.countrySearch.setText(homeBean.getCountry());
            this.citySearch.setText(homeBean.getCity());
            this.addressSearch.setText(homeBean.getAddress());
            this.isRestaurant.setText(homeBean.isRestaurant());
            this.radiusSearch.setText(homeBean.getRange());
        }
        else {
            this.countrySearch.setText("");
            this.citySearch.setText("");
            this.addressSearch.setText("");
            this.isRestaurant.setText("");
            this.radiusSearch.setText("");
            hBoxInput.setVisible(false);
            selfItinerary.setVisible(false);
            guideOff.setVisible(false);

            hBoxInput.getChildren().removeAll();
            Hyperlink link = new Hyperlink("Start from the Home-Page");
            link.setOnMouseClicked(mouseEvent -> {
                if(SessionUser.getInstance().getSession().getRole().equals(Roles.TOURIST_GUIDE.name().toLowerCase())) {
                    TouristGuideGraphicChange i = TouristGuideGraphicChange.getInstance();
                    i.menuBar(this.menuBar, home);
                }
                else {
                    UserGraphicChange ugc = UserGraphicChange.getInstance();
                    ugc.switchToHomePage(this.menuBar.getScene());
                    ugc.menuBar(this.menuBar, home);
                }
            });

            vBoxDynamic.getChildren().add(0, link);
        }
////TOLTO IL CLICK SULLA LABEL DEI RESULT////
        BookTourController btc = new BookTourController();
        List<ItineraryBean> suggItinerary;
        suggItinerary = btc.getItinerary(citySearch.getText(), "city");

        if(suggItinerary == null){
            System.out.println("EMPTY_DATABASE ");
        }
        else{
            suggestedItinerary.setText("Suggested Itinerary");
            guideImage.setImage(new Image("guideOn.png"));
            guideImage.setFitHeight(50);
            guideImage.setFitHeight(50);

            Accordion accordionSuggested = this.createTable(suggItinerary, "suggested", result, stackPaneResult);
            vBoxResultGuide.getChildren().add(accordionSuggested);
        }

        SearchController sc = new SearchController();
        List<ItineraryBean> iti;
        iti = sc.createItinerary(homeBean);

        if(iti == null){
            System.out.println("EMPTY_DATABASE ");
        }
        else {
            selfItinerary.setText("Self Itinerary");

            Accordion accordionSelf = this.createTable(iti, "self", result, null);
            vBoxResult.getChildren().add(accordionSelf);
        }
    }

    public static ResultController getInstance() {
        if(instance == null){
            instance = new ResultController();
        }
        return instance;
    }

}