package it.ispw.daniele.backpacker.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUserConnection {

    private static Connection me = null;
    private static final String user = "user";
    private static final String dbUrl = "jdbc:mysql://localhost/backpacker?allowPublicKeyRetrieval=true&useSSL=false";

    private static final String driverClassName = "com.mysql.jdbc.Driver";

    public static Connection getUserConnection() throws ClassNotFoundException, SQLException {

        if(me != null) {
            return me;
        }
        else {
            Class.forName(driverClassName);
            me = DriverManager.getConnection(dbUrl,user,"user");
            return me;
        }

    }

    public static void closeUserConnection(Connection conn) throws SQLException{
        conn.close();
        me = null;
    }

}
