package it.ispw.daniele.backpacker.bean;

import java.io.Serial;

public class TouristGuideBean extends GeneralUserBean{

    @Serial
    private static final long serialVersionUID = 42L;
    private String name;
    private String surname;
    private String identificationCode;

    public String getIdentificationCode() {
        return this.identificationCode;
    }

    public void setIdentificationCode(String identificationCode) {
        this.identificationCode = identificationCode;
    }

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
