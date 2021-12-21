package it.ispw.daniele.backpacker.bean;

public class GeneralUserBean {

    private String password;
    private String username;
    private boolean isLogged;

    private static GeneralUserBean INSTANCE = null;

    public final GeneralUserBean getInstance() {
        if(INSTANCE==null) {
            INSTANCE = new GeneralUserBean();
        }

        return INSTANCE;
    }

    public GeneralUserBean() {
    }

    public GeneralUserBean(String user, String pass) {
        this.username = user;
        this.password = pass;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return username;
    }

    public void setEmail(String email) {
        this.username = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getLogged() {
        return isLogged;
    }

    public void setLogged(boolean isLogged) {
        this.isLogged = isLogged;
    }

//    public boolean validate() throws Exception {
//        System.out.println("loginbean" + getLogged() + getEmail() + getPassword());
//
//        if (email == null || email.equals("") || password.equals(null) || password.equals("")) {
//            return false;
//        }
//        System.out.println("LOGIN BEAN   " + email + password);
//        LoginBean utenteTrovato = LoginController.getInstances().login(email, password);
//        System.out.println(this.isLogged);
//        return utenteTrovato != null;
//
//    }

}
