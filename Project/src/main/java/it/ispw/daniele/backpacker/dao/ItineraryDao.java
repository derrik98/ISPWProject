package it.ispw.daniele.backpacker.dao;

import it.ispw.daniele.backpacker.entity.Itinerary;
import it.ispw.daniele.backpacker.utils.DBTouristGuideConnection;
import it.ispw.daniele.backpacker.utils.DBUserConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class ItineraryDao extends DaoTemplate{

    private static final String ID = "id";
    private static final String LOCATION = "location";
    private static final String GUIDE_ID = "guideId";
    private static final String DATE = "date";
    private static final String TIME = "time";
    private static final String PARTICIPANTS = "participants";
    private static final String PRICE = "price";
    private static final String STEPS = "steps";
    private static final String ADD_PART = "add_part";
    private static final String REMOVE_PART = "remove_part";


    public List<Itinerary> getItinerary(String city){
        List<Itinerary> ret = this.execute(new DaoAction<List<Itinerary>>() {
            @Override
            public List<Itinerary> act() throws ClassNotFoundException, SQLException {
                Connection conn;
                List<Itinerary> itinerary = new ArrayList<>();
                String sql = null;


                /*if(role.equals("admin")) {
                    conn =DBTouristGuideConnection.getTouristGuideConnection();
                    sql = "call livethemusic.get_pending_itinerary(?);\r\n";
                } else {*/
                    conn = DBUserConnection.getUserConnection();
                    sql = "call backpacker.get_itinerary(?);\r\n";
                //}
                try (PreparedStatement stm = conn.prepareStatement(sql)) {

                    stm.setString(1, city);

                    if (stm != null) {
                        try (ResultSet rs = stm.executeQuery()) {
                            itinerary = unpackResultSet(rs);
                        }
                    }

                }
                DBUserConnection.closeUserConnection(conn);
                return itinerary;
            }
        });
        if (ret != null) {
            return ret;
        }
        else {
            return Collections.emptyList();
        }
    }

    public void addParticipation(String username, int itineraryId) {
        this.manageParticipation(username, itineraryId, ADD_PART);
    }

    public void removeParticipation(String username, int itineraryId) {
        this.manageParticipation(username, itineraryId, REMOVE_PART);
    }

    public Boolean isParticipating(String username, int itineraryId) {
        Boolean ret = this.execute(() -> {
            Connection conn = DBUserConnection.getUserConnection();
            String sql = "call backpacker.is_participating(?, ?);\r\n";
            try (PreparedStatement stm = conn.prepareStatement(sql)) {
                stm.setString(1, username);
                stm.setInt(2,  itineraryId);
                try (ResultSet rs = stm.executeQuery()) {
                    DBUserConnection.closeUserConnection(conn);
                    return (rs.first());
                }
            }
        });
        if (ret != null)
            return ret;
        else
            return false;
    }

    private void manageParticipation(String username, int id, String operation) {
        this.execute((DaoAction<Void>) () -> {
            Connection conn;
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
                stm.setInt(2,  id);
                stm.executeUpdate();
            } finally {
                if (stm != null)
                    stm.close();
            }
            DBUserConnection.closeUserConnection(conn);
            return null;
        });
    }

    public boolean addItinerary(String guideId, String location, Date date, String time, int participants, int price, String steps) {
        return (this.execute(() -> {
            Connection con = DBTouristGuideConnection.getTouristGuideConnection();
            String sql = "call backpacker.add_itinerary(?, ?, ?, ?, ?, ?, ?);\r\n";
            try (PreparedStatement stm = con.prepareStatement(sql)) {
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                stm.setString(1, guideId);
                stm.setString(2, location);
                stm.setDate(3, sqlDate);
                stm.setString(4, time);
                stm.setInt(5, participants);
                stm.setInt(6, price);
                stm.setString(7, steps);
                stm.executeUpdate();
                return true;
            }
        }) != null);
    }

    public int getItineraryId(String guideId, String location, String date, String time, int participants, int price, String steps) throws SQLException, ClassNotFoundException {

            Connection conn;
            String sql;
            PreparedStatement stm;

                conn = DBUserConnection.getUserConnection();
                sql = "call backpacker.get_itinerary_id(?, ?, ?, ?, ?, ?; ?);\r\n";
                stm = conn.prepareStatement(sql);

                stm.setString(1, guideId);
                stm.setString(2, location);
                stm.setString(3, date);
                stm.setString(4, time);
                stm.setInt(5, participants);
                stm.setInt(6, price);
                stm.setString(7, steps);

                try (ResultSet rs = stm.executeQuery()) {
                    if (!rs.first()) { // rs not empty
                        return rs.getInt("id");
                    }
                    return 0;
                } finally {
                    if (stm != null)
                        stm.close();
                    DBUserConnection.closeUserConnection(conn);
                }
    }

    public List<Itinerary> getBookedItineraries(String input) {
        List<Itinerary> ret = this.execute(new DaoAction<List<Itinerary>>() {
            @Override
            public List<Itinerary> act() throws ClassNotFoundException, SQLException {
                Connection conn = null;
                List<Itinerary> itinerary = new ArrayList<>();
                String sql = null;

                /*if(role.equals("admin")) {
                    conn =DBTouristGuideConnection.getTouristGuideConnection();
                    sql = "call livethemusic.get_pending_itinerary(?);\r\n";
                } else {*/
                conn = DBUserConnection.getUserConnection();
                sql = "call backpacker.get_booked_itineraries(?);\r\n";
                //}

                try(PreparedStatement stm = conn.prepareStatement(sql)) {

                    stm.setString(1, input);

                    if (stm != null) {
                        try (ResultSet rs = stm.executeQuery()) {
                            itinerary = unpackResultSet(rs);
                        }
                    }
                    /*try (ResultSet rs = stm.executeQuery()) {
                        rs.next();

                        String name = rs.getString(ID);
                        String location = rs.getString(LOCATION);
                        String guideId = rs.getString(GUIDE_ID);
                        String date = rs.getString(DATE);
                        String steps = rs.getString(STEPS);

                        System.out.println(name + location + guideId + date + steps + "AAAAAAAAAAAAAAAAAAA");

                        //itinerary = new Itinerary(name, location, guideId, date, steps);
                        System.out.println("sono quiiiiiiiiiii " + itinerary);
                    }*/
                }
                return itinerary;
            }
        });
        if (ret != null)
            return ret;
        else
            return Collections.emptyList();
    }

    private List<Itinerary> unpackResultSet(ResultSet rs) throws SQLException{
        List<Itinerary> l = new ArrayList<>();

        if (!rs.first()) // rs not empty
            return Collections.emptyList();

        do{
            int id = rs.getInt(ID);
            String guideId = rs.getString(GUIDE_ID);
            String location = rs.getString(LOCATION);
            String date = rs.getString(DATE);
            String time = rs.getString(TIME);
            int participants = rs.getInt(PARTICIPANTS);
            int price = rs.getInt(PRICE);
            String steps = rs.getString(STEPS);

            System.out.println("LOCATION " + location);

            Itinerary itinerary = new Itinerary(id, guideId, location, date, time, participants, price, steps);

            l.add(itinerary);
        } while (rs.next());
        return l;
    }

    public void saveTour(String username, String itinerary) {
        this.execute(new DaoAction<Boolean>() {
            @Override
            public Boolean act() throws ClassNotFoundException, SQLException {
                Connection con = DBUserConnection.getUserConnection();
                String sql = "call backpacker.save_itinerary(?, ?);\r\n";

                try (PreparedStatement stm = con.prepareStatement(sql)) {
                    stm.setString(1, username);
                    stm.setString(2, itinerary);
                    stm.executeUpdate();

                }
                return true;
            }
        });
    }

    public List<Itinerary> getSavedItinerary(String input) {
        List<Itinerary> ret = this.execute(new DaoAction<List<Itinerary>>() {
            @Override
            public List<Itinerary> act() throws ClassNotFoundException, SQLException {
                Connection conn = null;
                List<Itinerary> itinerary = new ArrayList<>();
                String sql = null;

                /*if(role.equals("admin")) {
                    conn =DBTouristGuideConnection.getTouristGuideConnection();
                    sql = "call livethemusic.get_pending_itinerary(?);\r\n";
                } else {*/
                conn = DBUserConnection.getUserConnection();
                sql = "call backpacker.get_saved_itinerary(?);\r\n";
                //}

                try(PreparedStatement stm = conn.prepareStatement(sql)) {

                    stm.setString(1, input);

                    if (stm != null) {
                        try (ResultSet rs = stm.executeQuery()) {
                            itinerary = unpackResult(rs);
                        }
                    }
                    /*try (ResultSet rs = stm.executeQuery()) {
                        rs.next();

                        String name = rs.getString(ID);
                        String location = rs.getString(LOCATION);
                        String guideId = rs.getString(GUIDE_ID);
                        String date = rs.getString(DATE);
                        String steps = rs.getString(STEPS);

                        System.out.println(name + location + guideId + date + steps + "AAAAAAAAAAAAAAAAAAA");

                        //itinerary = new Itinerary(name, location, guideId, date, steps);
                        System.out.println("sono quiiiiiiiiiii " + itinerary);
                    }*/
                }
                return itinerary;
            }
        });
        if (ret != null)
            return ret;
        else
            return Collections.emptyList();
    }

    private List<Itinerary> unpackResult(ResultSet rs) throws SQLException {
        List<Itinerary> l = new ArrayList<>();

        if (!rs.first()) // rs not empty
            return Collections.emptyList();

        do{
            int id = rs.getInt(ID);
            String steps = rs.getString(STEPS);

            Itinerary itinerary = new Itinerary(id, steps);

            l.add(itinerary);
        } while (rs.next());
        return l;
    }

    public void removeTour(String username, String steps) {

        this.execute(() -> {
            Connection con = DBUserConnection.getUserConnection();
            String sql = "call backpacker.remove_itinerary(?, ?);\r\n";

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setString(1, username);
                stm.setString(2, steps);
                stm.executeUpdate();
            }
            return true;
        });
    }

}
