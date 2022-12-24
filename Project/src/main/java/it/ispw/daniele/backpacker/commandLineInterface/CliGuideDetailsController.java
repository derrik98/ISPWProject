package it.ispw.daniele.backpacker.commandLineInterface;

import it.ispw.daniele.backpacker.bean.GeneralUserBean;
import it.ispw.daniele.backpacker.bean.TouristGuideBean;
import it.ispw.daniele.backpacker.dao.TouristGuideDao;
import it.ispw.daniele.backpacker.entity.TouristGuide;
import it.ispw.daniele.backpacker.fxmlView.TouristGuideGraphicChange;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static it.ispw.daniele.backpacker.commandLineInterface.CLI.BOLD;
import static it.ispw.daniele.backpacker.commandLineInterface.CLI.RESET;

public class CliGuideDetailsController extends Controller {

    private TouristGuideBean getSearchUser(String searchString, String caller) {
        TouristGuideDao ud = new TouristGuideDao();
        List<TouristGuide> l = ud.getSearchUser(searchString, caller);
        return this.convert(l.get(0));
    }

    public void init() {

        GeneralUserBean gub = SessionUser.getInstance().getSession();

        TouristGuideBean users = this.getSearchUser("search_t_guide", gub.getUsername());

        do {
            System.out.print("\033[H\033[2J");
            System.out.println(BOLD + "PROFILE PAGE\n" + RESET);
            System.out.println("Username: " + users.getUsername());
            System.out.println("Name: " + users.getName());
            System.out.println("Email: " + users.getEmail());
            System.out.println("Surname: " + users.getSurname());
            System.out.println("Vat number: " + users.getIdentificationCode());
            System.out.println("\n");
            System.out.println("Go Back [press 'b']: ");
            Scanner s = new Scanner(System.in);
            if (s.nextLine().equals("b")) {
                return;
            }
            else {
                System.out.println("Incorrect command!");
            }
        }while (true);
    }
}
