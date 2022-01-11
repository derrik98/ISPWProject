package it.ispw.daniele.backpacker.controller.login;

import it.ispw.daniele.backpacker.bean.GeneralUserBean;
import it.ispw.daniele.backpacker.bean.UserBean;
import it.ispw.daniele.backpacker.dao.GeneralUserDao;
import it.ispw.daniele.backpacker.dao.UserDAO;
import it.ispw.daniele.backpacker.entity.GeneralUser;
import it.ispw.daniele.backpacker.exceptions.LoginEmptyFieldException;

public class LoginController {

    public GeneralUserBean login(GeneralUserBean userBean) throws LoginEmptyFieldException {
        GeneralUserDao gud = new GeneralUserDao();

        if(userBean.getUsername().equals("")) {
            throw new LoginEmptyFieldException("Username necessary");
        }
        if(userBean.getPassword().equals("")) {
            throw new LoginEmptyFieldException("Password necessary");
        }

        GeneralUser result = gud.findUser(userBean.getUsername(), userBean.getPassword());
        System.out.println(result);
        if (result == null)	{
            return null;
        } else {
            GeneralUserBean gu = new GeneralUserBean();
            gu.setUsername(result.getUsername());
            gu.setPassword(result.getPassword());
            gu.setRole(result.getRole());
            return gu;
        }
    }

    public boolean createUser(UserBean ub) {
        UserDAO ud = new UserDAO();
        return ud.createUser(ub.getUsername(), ub.getName(), ub.getSurname(), ub.getEmail(), ub.getPassword(), ub.getProfilePicture());
    }

//    public boolean createRestayrantOwner(RestaurantOwnerBean ab) {
//        RestaurantOwnerDao ad = new RestaurantOwnerDao();
//        return ad.createRestaurantOwner(ab.getUsername(), ab.getPassword(), ab.getBandName(), ab.getProfilePicture(), ab.getEmail());
//    }
//
//    public boolean createTouristGuide(TouristGuideBean ab) {
//        TouristGuideDao ad = new TouristGuideDao();
//        return ad.createTouristGuide(ab.getUsername(), ab.getPassword(), ab.getBandName(), ab.getProfilePicture(), ab.getEmail());
//    }

}
