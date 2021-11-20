package it.ispw.daniele.backpacker.bean;

public class RegisterBean {

    private String name;
    private String surname;
    private String password;
    private String confirmPassword;
    private String email;
    private String identificatorNumber;

    public RegisterBean() {
    }

    private static RegisterBean INSTANCE = null;

    public RegisterBean(String email, String name, String surname, String pass, String confPass) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.password = pass;
        this.confirmPassword = confPass;
    }

    public RegisterBean getInstance() {
        if(INSTANCE==null) {
            INSTANCE = new RegisterBean();
        }
        return INSTANCE;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getSurname() {
        return surname;
    }


    public void setSurname(String surname) {
        this.surname = surname;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


//    public boolean validate() throws Exception {
//        // controllo sintattico
//        System.out.println("registerbean " + email + password);
//
//        if (email == null || email.equals("") || password.equals(null) || password.equals("") || name.equals("") || surname.equals("")) {
//            return false;
//        }
//        System.out.println(email + password);
//        RegisterBean utenteRegistrato = RegisterController.getInstance().register(name, surname, password, email);
//        return utenteRegistrato != null;
//
//    }


}
