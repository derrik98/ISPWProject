package it.ispw.daniele.backpacker.entity;

import java.io.Serializable;

public class GeneralUser implements Serializable {

    protected String name;
    protected String surname;
    protected String email;
    protected String password;

    public GeneralUser() {
        this.name = "";
        this.surname = "";
        this.email = "";
        this.password = "";
    }

    public GeneralUser(String name, String surname, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
