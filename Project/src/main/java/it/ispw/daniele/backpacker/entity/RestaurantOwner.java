package it.ispw.daniele.backpacker.entity;

public class RestaurantOwner extends GeneralUser{

    private String VATNumber;

    public RestaurantOwner(String name, String surname, String email, String password, String VATNumber) {
        super(name, surname, email, password);
        this.VATNumber = VATNumber;
    }

    public String getVATNumber() {
        return VATNumber;
    }
}
