package it.ispw.daniele.backpacker.dao;

import it.ispw.daniele.backpacker.utils.DBTouristGuideConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class ItineraryDao extends DaoTemplate{

    public boolean addItinerary(String name, String location, String guideId, Date date, String steps) {
        return (this.execute(new DaoAction<Boolean>() {
            @Override
            public Boolean act() throws ClassNotFoundException, SQLException {
                Connection con = DBTouristGuideConnection.getTouristGuideConnection();
                String sql = "call backpacker.add_itinerary(?, ?, ?, ?, ?);\r\n";
                try (PreparedStatement stm = con.prepareStatement(sql)) {
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                    stm.setString(1, name);
                    stm.setString(2, location);
                    stm.setString(3, guideId);
                    stm.setDate(4, sqlDate);
                    stm.setString(5, steps);
                    stm.executeUpdate();
                    return true;
                }
            }
        }) != null);
    }
}
