package it.ispw.daniele.backpacker.view.fxmlView;

import it.ispw.daniele.backpacker.bean.GeneralUserBean;
import it.ispw.daniele.backpacker.bean.ItineraryBean;
import it.ispw.daniele.backpacker.booktour.BookTourController;
import it.ispw.daniele.backpacker.utils.SessionUser;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class ItineraryDetailsController {
    @FXML
    public Button subscribeButton;
    @FXML
    public ImageView closePageImage;
    @FXML
    public AnchorPane APDetails;

    private final BookTourController controller = new BookTourController();
    private final GeneralUserBean sessionUser = SessionUser.getInstance().getSession();
    private ItineraryBean ib = new ItineraryBean();
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

    @FXML
    public void closePage() {
        StackPane sp = (StackPane) APDetails.getParent();
        sp.getChildren().remove(1);
        sp.getChildren().get(0).setDisable(false);
    }

    @FXML
    public void subscribe() {

        boolean isPart = controller.isParticipating(this.sessionUser, ib);
        if(isPart){
            controller.removeParticipation(this.sessionUser, ib);
            this.subscribeButton.setText("Subscribe");

        } else {
            controller.addParticipation(this.sessionUser, ib);
            this.subscribeButton.setText("Remove");

        }
    }

    public void init(ItineraryBean itineraryBean) {

        itineraryId.setText(String.valueOf(itineraryBean.getItineraryId()));
        guideId.setText(itineraryBean.getGuideId());
        location = new Text(itineraryBean.getLocation());
        date.setText(itineraryBean.getDate());
        time.setText(itineraryBean.getTime());
        participants.setText(String.valueOf(itineraryBean.getParticipants()));
        price.setText(String.valueOf(itineraryBean.getPrice()));
        steps.setText(itineraryBean.getSteps());

        ib = itineraryBean;
    }
}
