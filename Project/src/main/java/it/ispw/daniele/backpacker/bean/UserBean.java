package it.ispw.daniele.backpacker.bean;

public class UserBean extends GeneralUserBean{

    private static final long serialVersionUID = 42L;
    private String name;
    private String surname;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return this.surname;
    }
}
