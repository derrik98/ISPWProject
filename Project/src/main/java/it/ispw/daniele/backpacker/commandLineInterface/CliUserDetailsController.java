package it.ispw.daniele.backpacker.commandLineInterface;

import it.ispw.daniele.backpacker.bean.GeneralUserBean;
import it.ispw.daniele.backpacker.bean.UserBean;
import it.ispw.daniele.backpacker.dao.UserDAO;
import it.ispw.daniele.backpacker.entity.User;
import it.ispw.daniele.backpacker.fxmlView.UserGraphicChange;
import it.ispw.daniele.backpacker.utils.Controller;
import it.ispw.daniele.backpacker.utils.SessionUser;

import java.util.List;
import java.util.logging.Logger;

public class CliUserDetailsController extends Controller {

    public UserBean getSearchUser(String searchString, String caller){
        UserDAO ud = new UserDAO();
        List<User> l = ud.getSearchUser(searchString, caller);
        return this.convert(l.get(0));
    }

    public void init() {
        UserGraphicChange ugc = UserGraphicChange.getInstance();
        //ugc.menuBar(this.menuBar, "profile");
        GeneralUserBean gub = SessionUser.getInstance().getSession();

        UserBean users = this.getSearchUser("search_user", SessionUser.getInstance().getSession().getUsername());

        System.out.println("Username: " + users.getUsername());
        System.out.println("Name: " + users.getName());
        System.out.println("Email: " + users.getEmail());
        System.out.println("Surname: " + users.getSurname());

        Logger.getLogger(users.getUsername());
        Logger.getLogger(users.getName());
        Logger.getLogger(users.getEmail());
        Logger.getLogger(users.getSurname());
    }
}
