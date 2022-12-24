package it.ispw.daniele.backpacker.controller.login;

import it.ispw.daniele.backpacker.bean.GeneralUserBean;
import it.ispw.daniele.backpacker.bean.TouristGuideBean;
import it.ispw.daniele.backpacker.bean.UserBean;
import it.ispw.daniele.backpacker.dao.GeneralUserDao;
import it.ispw.daniele.backpacker.dao.TouristGuideDao;
import it.ispw.daniele.backpacker.dao.UserDAO;
import it.ispw.daniele.backpacker.entity.GeneralUser;
import it.ispw.daniele.backpacker.exceptions.EmptyFieldException;
import it.ispw.daniele.backpacker.exceptions.LoginFailException;

public class LoginController {

    public GeneralUserBean login(GeneralUserBean userBean) throws EmptyFieldException {
        GeneralUserDao gud = new GeneralUserDao();

        if(userBean.getUsername().equals("")) {
            throw new EmptyFieldException("Username necessary");
        }
        if(userBean.getPassword().equals("")) {
            throw new EmptyFieldException("Password necessary");
        }

        GeneralUser result = gud.findUser(userBean.getUsername(), userBean.getPassword());

        if (result == null)	{
            throw new LoginFailException("Incorrect Credentials");
        } else {
            GeneralUserBean gu = new GeneralUserBean();
            gu.setUsername(result.getUsername());
            gu.setPassword(result.getPassword());
            gu.setEmail(result.getEmail());
            gu.setRole(result.getRole());
            return gu;
        }
    }

    public boolean createUser(UserBean ub) {
        UserDAO ud = new UserDAO();
        return ud.createUser(ub.getUsername(), ub.getName(), ub.getSurname(), ub.getEmail(), ub.getPassword(), ub.getProfilePicture());
    }

//    public boolean createRestaurantOwner(RestaurantOwnerBean ab) {
//        RestaurantOwnerDao ad = new RestaurantOwnerDao();
//        return ad.createRestaurantOwner(ab.getUsername(), ab.getPassword(), ab.getName(), ab.getProfilePicture(), ab.getEmail());
//    }

    public boolean createTouristGuide(TouristGuideBean tgb) {
        TouristGuideDao tgd = new TouristGuideDao();
        return tgd.createTouristGuide(tgb.getUsername(), tgb.getName(), tgb.getSurname(), tgb.getEmail(), tgb.getPassword(), tgb.getProfilePicture(), tgb.getIdentificationCode());
    }

}
