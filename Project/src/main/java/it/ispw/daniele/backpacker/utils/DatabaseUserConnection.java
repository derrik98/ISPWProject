package it.ispw.daniele.backpacker.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUserConnection {

    private static Connection connection = null;
    private static final String USER_PSW = System.getProperty("user_password");
    private static final String DB_URL = "jdbc:mysql://localhost/backpacker?allowPublicKeyRetrieval=true&useSSL=false";

    //private static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";

    public static Connection getUserConnection() throws SQLException {

        if (connection == null) {
            //Class.forName(driver_class_name);
            connection = DriverManager.getConnection(DB_URL, "user", USER_PSW);
        }
        return connection;

    }

    public static void closeUserConnection(Connection conn) throws SQLException{
        conn.close();
        connection = null;
    }

}
