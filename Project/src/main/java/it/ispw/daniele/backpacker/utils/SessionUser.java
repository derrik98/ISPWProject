package it.ispw.daniele.backpacker.utils;

import it.ispw.daniele.backpacker.bean.GeneralUserBean;
import it.ispw.daniele.backpacker.bean.HomeBean;

public class SessionUser {

    private GeneralUserBean userSession = null;
    private HomeBean searchSession = null;
    private static SessionUser instance = null;

    private SessionUser(){}

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

    public void setSearchSession(HomeBean homeBean){
        this.searchSession = homeBean;
    }

    public HomeBean getSearchSession(){
        return this.searchSession;
    }

    public void closeSession(){
        this.userSession = null;
    }
}
