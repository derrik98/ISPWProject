package it.ispw.daniele.backpacker.dao;

import it.ispw.daniele.backpacker.entity.Itinerary;
import it.ispw.daniele.backpacker.utils.DBTouristGuideConnection;
import it.ispw.daniele.backpacker.utils.DBUserConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ItineraryDao extends DaoTemplate{

    private static final String ID = "itinerary_id";
    private static final String LOCATION = "location";
    private static final String GUIDE_ID = "guideId";
    private static final String DATE= "date";
    private static final String STEPS = "steps";

    private static final String ADD_PART = "add_part";
    private static final String REMOVE_PART = "remove_part";


    public Itinerary getItinerary(String city){//, String role) {
        return this.execute(new DaoAction<Itinerary>() {
            @Override
            public Itinerary act() throws ClassNotFoundException, SQLException {
                Connection conn = null;
                Itinerary itinerary = null;
                String sql = null;

                /*if(role.equals("admin")) {
                    conn =DBTouristGuideConnection.getTouristGuideConnection();
                    sql = "call livethemusic.get_pending_itinerary(?);\r\n";
                } else {*/
                    conn = DBUserConnection.getUserConnection();
                    sql = "call backpacker.get_itinerary(?);\r\n";
                //}

                try(PreparedStatement stm = conn.prepareStatement(sql)) {

                    stm.setString(1, city);
                    try (ResultSet rs = stm.executeQuery()) {
                        rs.next();

                        String name = rs.getString(ID);
                        String location = rs.getString(LOCATION);
                        String guideId = rs.getString(GUIDE_ID);
                        String date = rs.getString(DATE);
                        String steps = rs.getString(STEPS);

                        System.out.println(name + location + guideId + date + steps + "AAAAAAAAAAAAAAAAAAA");

                        //setting up coordinates of the musicevent
                        /*double latitude = rs.getDouble(LATITUDE);
                        double longitude = rs.getDouble(LONGITUDE);
                        List<Double> coordinates = new ArrayList<>();
                        coordinates.add(latitude);
                        coordinates.add(longitude);

                        if(coverPath == null || coverPath.equals("")) {
                            coverPath = DEFAULTPICTURE;
                        }*/

                        itinerary = new Itinerary(name, location, guideId, date, steps);
                        System.out.println("sono quiiiiiiiiiii " + itinerary);
                        //itinerary.setCoordinates(coordinates);
                    }
                }
                return itinerary;
            }
        });

    }

    public void addParticipation(String username, String itineraryId) {
        this.manageParticipation(username, itineraryId, ADD_PART);
    }

    public void removeParticipation(String username, String itineraryId) {
        this.manageParticipation(username, itineraryId, REMOVE_PART);
    }

    public Boolean isParticipating(String username, String itineraryId) {
        Boolean ret = this.execute(new DaoAction<Boolean>() {
            @Override
            public Boolean act() throws ClassNotFoundException, SQLException {
                Connection conn = DBUserConnection.getUserConnection();
                String sql = "call backpacker.is_participating(?, ?);\r\n";
                try (PreparedStatement stm = conn.prepareStatement(sql)) {
                    stm.setString(1, username);
                    stm.setInt(2,  Integer.parseInt(itineraryId));
                    try (ResultSet rs = stm.executeQuery()) {
                        return (rs.first());
                    }
                }
            }
        });
        if (ret != null)
            return ret;
        else
            return false;
    }

    private void manageParticipation(String username, String id, String operation) {
        this.execute(new DaoAction<Void>() {
            @Override
            public Void act() throws ClassNotFoundException, SQLException {
                Connection conn = null;
                PreparedStatement stm = null;
                String sql = null;
                try {
                    conn = DBUserConnection.getUserConnection();
                    if(operation.equals(ADD_PART)) {
                        sql = "call backpacker.add_participation(?, ?);\r\n";
                    } else if(operation.equals(REMOVE_PART)) {
                        sql = "call backpacker.remove_participation(?, ?);\r\n";
                    }
                    stm = conn.prepareStatement(sql);
                    stm.setString(1, username);
                    stm.setInt(2,  Integer.parseInt(id));
                    stm.executeUpdate();
                } finally {
                    if (stm != null)
                        stm.close();
                }
                return null;
            }
        });
    }

    public boolean addItinerary(String id, String guideId, String location, Date date, String time, String participants, String price, String steps) {
        return (this.execute(new DaoAction<Boolean>() {
            @Override
            public Boolean act() throws ClassNotFoundException, SQLException {
                Connection con = DBTouristGuideConnection.getTouristGuideConnection();
                String sql = "call backpacker.add_itinerary(?, ?, ?, ?, ?, ?, ?, ?);\r\n";
                try (PreparedStatement stm = con.prepareStatement(sql)) {
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                    stm.setString(1, id);
                    stm.setString(2, guideId);
                    stm.setString(3, location);
                    stm.setDate(4, sqlDate);
                    stm.setString(5, time);
                    stm.setInt(6, Integer.parseInt(participants));
                    stm.setInt(7, Integer.parseInt(price));
                    stm.setString(8, steps);
                    stm.executeUpdate();
                    return true;
                }
            }
        }) != null);
    }

    public boolean getItineraryId(String id) {
        return (this.execute(new DaoAction<Boolean>() {
            @Override
            public Boolean act() throws ClassNotFoundException, SQLException {
                Connection conn = DBUserConnection.getUserConnection();
                Itinerary itinerary = null;
                String sql = "call backpacker.get_itinerary_id(?);\r\n";

                try (PreparedStatement stm = conn.prepareStatement(sql)) {

                    stm.setString(1, id);

                    try (ResultSet rs = stm.executeQuery()) {
                        rs.next();

                        String id = rs.getString("itinerary_id");

                        System.out.println(id + "AAAAAAAAAAAAAAAAAAA");

                        //itinerary = new Itinerary(name, location, guideId, date, steps);
                        System.out.println("sono quiiiiiiiiiii " + itinerary);
                        //itinerary.setCoordinates(coordinates);
                        return true;
                    }
                }
            }
        }) != null);
    }
}
