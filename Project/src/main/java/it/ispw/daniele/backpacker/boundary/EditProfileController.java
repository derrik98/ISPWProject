package it.ispw.daniele.backpacker.boundary;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class EditProfileController extends GUIController{

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

    public EditProfileController() throws IOException {
    }
}
