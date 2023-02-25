package it.ispw.daniele.backpacker.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbLoginConnection {

    private static Connection connection = null;
    private static final String USER = System.getProperty("login_password");
    private static final String DB_URL = "jdbc:mysql://localhost/backpacker?allowPublicKeyRetrieval=true&useSSL=false";
    private static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";

    private dbLoginConnection() {
    }

    public static Connection getLoginConnection() throws ClassNotFoundException, SQLException {

        if (connection == null) {
            //Class.forName(driver_class_name);
            connection = DriverManager.getConnection(DB_URL, USER, "login");
        }
        return connection;
    }

    public static void closeLoginConnection(Connection conn) throws SQLException{
        conn.close();
        connection = null;
    }

}
