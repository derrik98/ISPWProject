package it.ispw.daniele.backpacker.dao;

import it.ispw.daniele.backpacker.entity.User;
import it.ispw.daniele.backpacker.utils.dbLoginConnection;
import it.ispw.daniele.backpacker.utils.dbUserConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserDAO extends DaoTemplate{

    private static final String SEARCH_USER = "search_user";

    public Boolean createUser(String username, String name, String surname,
                              String email, String password, String profilePicture) {
        return (this.execute(() -> {

            Connection con = dbLoginConnection.getLoginConnection();

            String sql = "call backpacker.add_user(?, ?, ?, ?, ?, ?);\r\n";
            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setString(1, username);
                stm.setString(2, name);
                stm.setString(3, surname);
                stm.setString(4, email);
                stm.setString(5, password);
                stm.setString(6, profilePicture);
                stm.executeUpdate();
            }
            return true;
        }) != null);
    }

    public List<User> getSearchUser(String caller){
        return this.queryDatabase(caller, SEARCH_USER);
    }

    private List<User> queryDatabase(String caller, String operation){
        List <User> ret = this.execute(() -> {
            List<User> l = new ArrayList<>();
            Connection conn = dbUserConnection.getUserConnection();
            PreparedStatement stm = null;
            try {
                String sql;
                if (operation.equals(SEARCH_USER)) {

                    sql = "call backpacker.search_user(?);\r\n";
                    stm = conn.prepareStatement(sql);
                    stm.setString(1, caller);
                } else {

                    return Collections.emptyList();
                }
                try (ResultSet rs = stm.executeQuery()) {

                    if (!rs.first()) // rs not empty
                        return Collections.emptyList();

                    do{
                        String username = rs.getString("username");
                        String name = rs.getString("name");
                        String surname = rs.getString("surname");
                        String profilePicture = rs.getString("profile_picture_path");
                        String email = rs.getString("email");

                        if(profilePicture == null || profilePicture.equals("")) {
                            profilePicture = "user.png";
                        }

                        l.add(new User(username, name, surname, profilePicture, email));

                    } while (rs.next());
                    return l;
                }
            } finally {

                if (stm != null)
                    stm.close();
            }
        });
        if (ret != null) {
            return ret;
        } else {
            return Collections.emptyList();
        }
    }

}
