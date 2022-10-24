package it.ispw.daniele.backpacker.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBTouristGuideConnection {

    private static Connection me = null;
    private static String touristGuide = "tourist_guide";
    private static String dbUrl = "jdbc:mysql://localhost/backpacker?useSSL=false";

    private static String driverClassName = "com.mysql.jdbc.Driver";

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