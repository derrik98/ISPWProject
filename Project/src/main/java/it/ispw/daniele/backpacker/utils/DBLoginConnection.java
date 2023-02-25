package it.ispw.daniele.backpacker.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBLoginConnection {

    private static Connection me = null;
    private static final String user = System.getProperty("login_password");
    private static final String db_url = "jdbc:mysql://localhost/backpacker?allowPublicKeyRetrieval=true&useSSL=false";
    private static final String driver_class_name = "com.mysql.jdbc.Driver";

    public static Connection getLoginConnection() throws ClassNotFoundException, SQLException {

        if (me == null) {
            //Class.forName(driver_class_name);
            me = DriverManager.getConnection(db_url, user, "login");
        }
        return me;
    }

    public static void closeLoginConnection() throws SQLException{
        me.close();
        me = null;
    }

}
