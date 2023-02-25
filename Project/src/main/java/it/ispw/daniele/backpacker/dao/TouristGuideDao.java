package it.ispw.daniele.backpacker.dao;

import it.ispw.daniele.backpacker.entity.TouristGuide;
import it.ispw.daniele.backpacker.utils.dbLoginConnection;
import it.ispw.daniele.backpacker.utils.dbTouristGuideConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class TouristGuideDao extends DaoTemplate{

    private static final String SEARCH_T_GUIDE = "search_t_guide";

    public List<TouristGuide> getSearchUser(String caller){
        return this.queryDatabase(caller, SEARCH_T_GUIDE);
    }

    private List<TouristGuide> queryDatabase(String caller, String operation){
        List <TouristGuide> ret = this.execute(() -> {
            List<TouristGuide> l = new ArrayList<>();
            Connection conn = dbTouristGuideConnection.getTouristGuideConnection();
            PreparedStatement stm = null;
            try {
                String sql;
                if (operation.equals(SEARCH_T_GUIDE)) {

                    sql = "call backpacker.search_t_guide(?);\r\n";
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
                        String vatNum = rs.getString("identification_code");

                        if(profilePicture == null || profilePicture.equals("")) {
                            profilePicture = "tourist_guide.png";
                        }

                        l.add(new TouristGuide(username, name, surname, profilePicture, email, vatNum));
                    } while (rs.next());

                    return l;
                }
            } finally {

                if (stm != null)
                    stm.close();
            }
        });

        return Objects.requireNonNullElse(ret, Collections.emptyList());
    }

    public Boolean createTouristGuide(String username, String name, String surname,
                              String email, String password, String profilePicture, String identificationCode) {
        return (this.execute(() -> {

            Connection con = dbLoginConnection.getLoginConnection();
            if(con == null){

                return false;
            }

            String sql = "call backpacker.add_tourist_guide(?, ?, ?, ?, ?, ?, ?);\r\n";
            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setString(1, username);
                stm.setString(2, name);
                stm.setString(3, surname);
                stm.setString(4, email);
                stm.setString(5, password);
                stm.setString(6, profilePicture);
                stm.setString(7, identificationCode);
                stm.executeUpdate();
            }

            return true;
        }) != null);
    }
}
