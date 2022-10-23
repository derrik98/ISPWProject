package it.ispw.daniele.backpacker.entity;

public class User extends GeneralUser{
    private final String name;
    private final String surname;
    private final String profilePicture;

    public User(String username, String password, String name,
                String surname, String profilePicture, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.profilePicture = profilePicture;
        this.email = email;
    }

    public User(String username, String name, String surname, String profilePicture) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.profilePicture = profilePicture;
    }

    public User(String username, String name, String surname, String profilePicture, String email) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.profilePicture = profilePicture;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

}
