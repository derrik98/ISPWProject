package it.ispw.daniele.backpacker.utils;

import it.ispw.daniele.backpacker.bean.GeneralUserBean;

public class SessionUser {

    private GeneralUserBean userSession = null;
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

    public void closeSession(){
        this.userSession = null;
    }
}
