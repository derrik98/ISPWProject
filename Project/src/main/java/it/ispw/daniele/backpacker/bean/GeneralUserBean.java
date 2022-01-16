package it.ispw.daniele.backpacker.bean;

import java.io.Serializable;

public class GeneralUserBean implements Serializable {

    private String username;
    private String password;
    protected String email;
    protected String role;
    protected String profilePicture;

//    private static GeneralUserBean INSTANCE = null;
//
//    public final GeneralUserBean getInstance() {
//        if(INSTANCE==null) {
//            INSTANCE = new GeneralUserBean();
//        }
//
//        return INSTANCE;
//    }
//
//    public GeneralUserBean() {
//    }
//
//    public GeneralUserBean(String user, String pass) {
//        this.username = user;
//        this.password = pass;
//    }

    public void setRole(String name) {
        this.role = name;
    }

    public String getRole() {
        return this.role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
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
