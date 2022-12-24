package it.ispw.daniele.backpacker.utils;

import it.ispw.daniele.backpacker.bean.GeneralUserBean;
import it.ispw.daniele.backpacker.bean.TouristGuideBean;

public class SessionUser {

    private GeneralUserBean userSession = null;
    private TouristGuideBean tgbSession = null;
    private static SessionUser instance = null;

    private SessionUser(){
    }

    public static SessionUser getInstance(){
        if(instance == null){
            instance = new SessionUser();
        }
        return instance;
    }

    public void setSession(GeneralUserBean userSession){
        if(this.userSession == null){
            this.userSession = userSession;
        }
    }

    public GeneralUserBean getSession(){
        return this.userSession;
    }

    public void setTgbSession(TouristGuideBean tgb){
        if(this.tgbSession == null){
            this.tgbSession = tgb;
        }
    }

    public TouristGuideBean getTgbSession(){
        return this.tgbSession;
    }

    public void closeSession(){
        this.userSession = null;
    }
}
