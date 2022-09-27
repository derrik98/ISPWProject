package it.ispw.daniele.backpacker.fxmlView;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class EditProfileController {

    @FXML
    private TextField textFieldNameSetting;
    @FXML
    private TextField textFieldSurnameSetting;
    @FXML
    private TextField textFieldCurrentPassSetting;
    @FXML
    private TextField textFieldNewPassSetting;
    @FXML
    private TextField textFieldConfNewPassSetting;


    public EditProfileController() throws IOException {
    }
}
