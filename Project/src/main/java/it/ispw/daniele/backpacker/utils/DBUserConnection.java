package it.ispw.daniele.backpacker.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUserConnection {

    private static Connection me = null;
    private static String user = "root";
    private static String dbUrl = "jdbc:mysql://localhost/backpacker?useSSL=false";

    private static String driverClassName = "com.mysql.jdbc.Driver";

    private DBUserConnection() {

    }

    public static Connection getUserConnection() throws ClassNotFoundException, SQLException {

        if(me != null) return me;
        else {
            Class.forName(driverClassName);
            me = DriverManager.getConnection(dbUrl,user,"");
            return me;
        }

    }

    public static void closeUserConnection() throws SQLException{
        me.close();
        me = null;
    }

}
