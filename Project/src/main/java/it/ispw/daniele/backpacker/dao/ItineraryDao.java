package it.ispw.daniele.backpacker.dao;

import it.ispw.daniele.backpacker.utils.DBTouristGuideConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class ItineraryDao extends DaoTemplate{

    public boolean addItinerary(String name, String location, String artistId, Date date, String ticketone, List<Double> coordinates) {
        return (this.execute(new DaoAction<Boolean>() {
            @Override
            public Boolean act() throws ClassNotFoundException, SQLException {
                Connection con = DBTouristGuideConnection.getTouristGuideConnection();
                String sql = "call backpacker.add_itinerary(?, ?, ?, ?, ?, ?, ?, ?);\r\n";
                try (PreparedStatement stm = con.prepareStatement(sql)) {
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                    stm.setString(1, name);
                    stm.setString(2, coverPath);
                    stm.setString(3, location);
                    stm.setString(4, artistUsername);
                    stm.setDate(5, sqlDate);
                    stm.setString(6, ticketone);
                    stm.setDouble(7, coordinates.get(0));
                    stm.setDouble(8, coordinates.get(1));
                    stm.executeUpdate();
                    return true;
                }
            }
        }) != null);
    }
}
