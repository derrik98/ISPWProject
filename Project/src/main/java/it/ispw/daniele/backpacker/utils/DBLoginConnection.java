package it.ispw.daniele.backpacker.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBLoginConnection {

    private static Connection me = null;
    private static final String user = "login";
    private static final String dbUrl = "jdbc:mysql://localhost/backpacker?allowPublicKeyRetrieval=true&useSSL=false";

    private static final String driverClassName = "com.mysql.jdbc.Driver";

    private DBLoginConnection() {

    }

    public static Connection getLoginConnection() throws ClassNotFoundException, SQLException {

        if(me != null) {return me;}
        else {
            Class.forName(driverClassName);
            me = DriverManager.getConnection(dbUrl,user,"login");
            return me;
        }

    }

    public static void closeLoginConnection() throws SQLException{
        me.close();
        me = null;
    }

}
