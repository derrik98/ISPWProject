package it.ispw.daniele.backpacker.dao;

import it.ispw.daniele.backpacker.entity.TouristGuide;
import it.ispw.daniele.backpacker.entity.User;
import it.ispw.daniele.backpacker.utils.DBTouristGuideConnection;
import it.ispw.daniele.backpacker.utils.DBUserConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TouristGuideDao extends DaoTemplate{

    private static final String SEARCH_T_GUIDE = "search_t_guide";

    public List<TouristGuide> getSearchUser(String searchString, String caller){
        return this.queryDatabase(searchString, caller, SEARCH_T_GUIDE);
    }

    private List<TouristGuide> queryDatabase(String string, String caller, String operation){
        List <TouristGuide> ret = this.execute(new DaoAction<List<TouristGuide>>() {
            @Override
            public List<TouristGuide> act() throws ClassNotFoundException, SQLException {
                List<TouristGuide> l = new ArrayList<>();
                Connection conn = DBUserConnection.getUserConnection();
                PreparedStatement stm = null;
                try {
                    String sql;
                    switch (operation) {

                        case SEARCH_T_GUIDE:
                            sql = "call backpacker.search_t_guide(?);\r\n";
                            stm = conn.prepareStatement(sql);
                            // stm.setString(1, string);
                            stm.setString(1, caller);
                            break;
                        /*case VIEWFRIENDS:
                            sql = "call livethemusic.view_friends(?);\r\n";
                            stm = conn.prepareStatement(sql);
                            stm.setString(1, string);
                            break;
                        case VIEWFRIENDSREQUESTS:
                            sql = "call livethemusic.view_friend_requests(?);\r\n";
                            stm = conn.prepareStatement(sql);
                            stm.setString(1, string);
                            break;*/
                        default:
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
            }
        });
        if (ret != null) {
            return ret;
        } else {
            return Collections.emptyList();
        }
    }

    public Boolean createTouristGuide(String username, String name, String surname,
                              String email, String password, String profilePicture, String identificationCode) {
        return (this.execute(new DaoAction<Boolean>() {
            @Override
            public Boolean act() throws ClassNotFoundException, SQLException {
                Connection con = DBTouristGuideConnection.getTouristGuideConnection();
                if(con == null){
                    System.out.println("connessoine non stabilita");
                }
                else{
                    System.out.println("connessoine stabilitaaaaaaa");
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
            }
        }) != null);
    }
}
