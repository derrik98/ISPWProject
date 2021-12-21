package it.ispw.daniele.backpacker.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBLoginConnection {

    private static Connection me = null;
    private static String user = "login";
    private static String dbUrl = "jdbc:mysql://localhost/livethemusic";

    private static String driverClassName = "com.mysql.jdbc.Driver";

    private DBLoginConnection(){}

    public static Connection getLoginConnection() throws ClassNotFoundException, SQLException{
        if(me != null){
            return me;
        }
        else{
            Class.forName(driverClassName);
            me = DriverManager.getConnection(dbUrl, user, "login");
            return me;
        }
    }

    public static void closeLoginConnection() throws SQLException{
        me.close();
        me = null;
    }
}