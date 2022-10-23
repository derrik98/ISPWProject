package it.ispw.daniele.backpacker.utils;

import it.ispw.daniele.backpacker.bean.TouristGuideBean;
import it.ispw.daniele.backpacker.bean.UserBean;
import it.ispw.daniele.backpacker.entity.TouristGuide;
import it.ispw.daniele.backpacker.entity.User;

import java.util.ArrayList;
import java.util.List;

public abstract class Controller {

    protected UserBean convert(User l) {
       /* List<UserBean> lb = new ArrayList<>();
        for(int i = 0; i < l.size(); i++){
            User u = l.get(i);*/
            UserBean ub = new UserBean();
            ub.setUsername(l.getUsername());
            ub.setName(l.getName());
            ub.setSurname(l.getSurname());
            ub.setProfilePicture(l.getProfilePicture());
            ub.setEmail(l.getEmail());
            //lb.add(ub);
        //}
        return ub;
    }

    protected TouristGuideBean convert(TouristGuide l) {
        /*List<TouristGuideBean> lb = new ArrayList<>();
        for(int i = 0; i < l.size(); i++){
            TouristGuide tg = l.get(i);*/
            TouristGuideBean tgb = new TouristGuideBean();
            tgb.setUsername(l.getUsername());
            tgb.setName(l.getName());
            tgb.setSurname(l.getSurname());
            tgb.setProfilePicture(l.getProfilePicture());
            tgb.setEmail(l.getEmail());
            tgb.setIdentificationCode(l.getVatNum());
            //lb.add(tgb);
        //}
        return tgb;
    }
}
