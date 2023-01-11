package it.ispw.daniele.backpacker.view.commandlineinterface;

import it.ispw.daniele.backpacker.bean.ItineraryBean;
import it.ispw.daniele.backpacker.bean.UserBean;
import it.ispw.daniele.backpacker.booktour.BookTourController;
import it.ispw.daniele.backpacker.booktour.SaveTour;
import it.ispw.daniele.backpacker.dao.UserDAO;
import it.ispw.daniele.backpacker.entity.User;
import it.ispw.daniele.backpacker.utils.Controller;
import it.ispw.daniele.backpacker.utils.SessionUser;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import static it.ispw.daniele.backpacker.view.commandlineinterface.CLI.BOLD;
import static it.ispw.daniele.backpacker.view.commandlineinterface.CLI.RESET;

public class CliUserDetailsController extends Controller {

    private UserBean getSearchUser(String searchString, String caller) {
        UserDAO ud = new UserDAO();
        List<User> l = ud.getSearchUser(searchString, caller);
        return this.convert(l.get(0));
    }

    public void init() {

        UserBean users = this.getSearchUser("search_user", SessionUser.getInstance().getSession().getUsername());

        do {
            /*String loggerName = "com.something";
            Logger log = Logger.getLogger(loggerName);
            ConsoleHandler handler = new ConsoleHandler();
            handler.setLevel(Level.ALL);
            log.addHandler(handler);
            log.setLevel(Level.ALL);
            log.fine("just testing");*/


            Logger.getLogger("\033[H\033[2J");
            //System.out.print("\033[H\033[2J");
            Logger.getLogger(BOLD + "PROFILE PAGE\n" + RESET);
            //System.out.println(BOLD + "PROFILE PAGE\n" + RESET);
            System.out.println("Username: " + users.getUsername());
            System.out.println("Name: " + users.getName());
            System.out.println("Email: " + users.getEmail());
            System.out.println("Surname: " + users.getSurname());
            System.out.println("\n");

            BookTourController btc = new BookTourController();
            List<ItineraryBean> booked;
            booked = btc.getItinerary(users.getUsername(), "user");

            SaveTour st = new SaveTour();
            List<ItineraryBean> saved;
            saved = st.getItinerary(users.getUsername());

            if (booked.isEmpty()) {
                System.out.println("Booked itineraries: ");
                System.out.println("EMPTY_DATABASE\n");
            } else {
                for(int indexB = 0; indexB < booked.size(); indexB++) {
                    System.out.println("Booked itineraries: ");
                    System.out.print("ID [" + booked.get(indexB).getItineraryId() + "] " + booked.get(indexB).getSteps() + "\n");
                }
            }
            if (saved.isEmpty()) {
                System.out.println("Saved itineraries: ");
                System.out.println("EMPTY_DATABASE\n");
            } else {
                for (int indexS = 0; indexS < saved.size(); indexS++) {
                    System.out.println("Saved itineraries: ");
                    System.out.print("ID [" + saved.get(indexS).getItineraryId() + "] " + saved.get(indexS).getSteps() + "\n");
                }
            }

                System.out.println("\nGo Back [press 'b']: ");
                Scanner scanner = new Scanner(System.in);

                if (scanner.nextLine().equals("b")) {
                    return;
                } else {
                    System.out.println("Command not found");
                }

                System.out.flush();
        } while (true);
    }
}
