package it.ispw.daniele.backpacker.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBTouristGuideConnection {

    private static Connection me = null;
    private static final String touristGuide = "tourist_guide";
    private static final String dbUrl = "jdbc:mysql://localhost/backpacker?allowPublicKeyRetrieval=true&useSSL=false";

    private static final String driverClassName = "com.mysql.jdbc.Driver";

    private DBTouristGuideConnection() {

    }

    public static Connection getTouristGuideConnection() throws ClassNotFoundException, SQLException {

        if(me != null) return me;
        else {
            Class.forName(driverClassName);
            me = DriverManager.getConnection(dbUrl,touristGuide,"tourist_guide");
            return me;
        }

    }

    public static void closeTouristGuideConnection() throws SQLException{
        me.close();
        me = null;
    }

}
