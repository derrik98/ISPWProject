package it.ispw.daniele.backpacker.dao;

import it.ispw.daniele.backpacker.entity.GeneralUser;
import it.ispw.daniele.backpacker.utils.DBLoginConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GeneralUserDao extends DaoTemplate{

    public GeneralUser findUser(String username, String password) {
        return this.execute(new DaoAction<GeneralUser>() {
            @Override
            public GeneralUser act() throws ClassNotFoundException, SQLException {
                Connection conn = null;
                GeneralUser u = null;
                conn = DBLoginConnection.getLoginConnection();
                /*if(conn != null){
                    System.out.println("connessoine stabilita");
                }
                else{
                    System.out.println("connessoine non stabilita");
                }
                System.out.println(username + password);*/

                String sql = "call backpacker.login(?, ?);\r\n";
                try(PreparedStatement stm = conn.prepareStatement(sql)){
                    stm.setString(1, username);
                    stm.setString(2, password);
                    try (ResultSet rs = stm.executeQuery()) {
                        //System.out.println(username + password);
                        if (!rs.first()) // rs not empty
                            return null;

                        boolean moreThanOne = rs.first() && rs.next();
                        assert !moreThanOne;
                        rs.first();

                        String role = rs.getString("role");
                        //System.out.println(role);
                        String usernameLoaded = rs.getString("username");
                        //System.out.println(username);

                        if(usernameLoaded.equals(username)) {
                            u = new GeneralUser(usernameLoaded, "", role);

                        }
                    }
                }
                System.out.println(username + password);
                return u;
            }
        });
    }
}
