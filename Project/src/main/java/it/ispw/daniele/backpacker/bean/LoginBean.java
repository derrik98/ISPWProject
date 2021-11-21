package it.ispw.daniele.backpacker.bean;

public class LoginBean {

    private String password;
    private String username;
    private boolean isLogged;

    private static LoginBean INSTANCE = null;

    public final LoginBean getInstance() {
        if(INSTANCE==null) {
            INSTANCE = new LoginBean();
        }

        return INSTANCE;
    }

    public LoginBean() {
    }

    public LoginBean(String user, String pass) {
        this.username = user;
        this.password = pass;
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