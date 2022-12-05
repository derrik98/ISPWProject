package it.ispw.daniele.backpacker.fxmlView;

import it.ispw.daniele.backpacker.bean.GeneralUserBean;
import it.ispw.daniele.backpacker.bean.ItineraryBean;
import it.ispw.daniele.backpacker.booktour.BookTourController;
import it.ispw.daniele.backpacker.utils.SessionUser;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ItineraryDetailsController {
    @FXML
    public Button subscribeButton;
    @FXML
    public ImageView closePageImage;
    @FXML
    public AnchorPane APDetails;

    private final BookTourController controller = new BookTourController();
    private final GeneralUserBean sessionUser = SessionUser.getInstance().getSession();

   public static ItineraryDetailsController instance = null;

    private static ItineraryBean ib = new ItineraryBean();
    @FXML
    public Text itineraryId = new Text();
    @FXML
    public Text guideId = new Text();
    @FXML
    public Text location = new Text();
    @FXML
    public Text date = new Text();
    @FXML
    public Text time = new Text();
    @FXML
    public Text participants = new Text();
    @FXML
    public Text price = new Text();
    @FXML
    public Text steps;

    public static ItineraryDetailsController getInstance(){
        if(instance == null){
            instance = new ItineraryDetailsController();
        }
        return instance;
    }

    @FXML
    public void closePage() {
        StackPane sp = (StackPane) APDetails.getParent();
        sp.getChildren().remove(1);
        sp.getChildren().get(0).setDisable(false);
    }

    @FXML
    public void subscribe() {
        System.out.println(ib);
        //boolean isPart = controller.isParticipating(this.sessionUser, this.itineraryBean);
        boolean isPart = controller.isParticipating(this.sessionUser, ib);
        if(isPart){
            controller.removeParticipation(this.sessionUser, ib);
            this.subscribeButton.setText("Subscribe");
                           //this.part.setText("Add Participation");
        } else {
            controller.addParticipation(this.sessionUser, ib);
            this.subscribeButton.setText("Remove");
                           //this.part.setText("Remove Participation");
        }
    }

    /*public void convertItinerary(ItineraryBean it) {
        itineraryBean = it;
    }*/

    public void init(ItineraryBean itineraryBean) {

        itineraryId.setText(itineraryBean.getItineraryId());
        guideId.setText(itineraryBean.getGuideId());
        //location.setText(itineraryBean.getLocation());
        location = new Text(itineraryBean.getLocation());
        date.setText(itineraryBean.getDate());
        time.setText(itineraryBean.getTime());
        participants.setText(itineraryBean.getParticipants());
        price.setText(itineraryBean.getPrice());
        steps.setText(itineraryBean.getSteps());

        ib = itineraryBean;
    }
}
