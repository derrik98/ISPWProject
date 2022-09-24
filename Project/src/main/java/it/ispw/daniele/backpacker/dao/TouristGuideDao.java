package it.ispw.daniele.backpacker.dao;

import it.ispw.daniele.backpacker.utils.DBTouristGuideConnection;
import it.ispw.daniele.backpacker.utils.DBUserConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TouristGuideDao extends DaoTemplate{

    public Boolean createTouristGuide(String username, String name, String surname,
                              String email, String password, String identificationCode) {
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

                String sql = "call backpacker.add_tourist_guide(?, ?, ?, ?, ?, ?);\r\n";
                try (PreparedStatement stm = con.prepareStatement(sql)) {
                    stm.setString(1, username);
                    stm.setString(2, name);
                    stm.setString(3, surname);
                    stm.setString(4, email);
                    stm.setString(5, password);
                    //stm.setString(6, profilePicture);
                    stm.setString(6, identificationCode);
                    stm.executeUpdate();
                }
                return true;
            }
        }) != null);
    }
}
