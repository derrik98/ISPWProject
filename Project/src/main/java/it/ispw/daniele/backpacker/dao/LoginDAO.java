package it.ispw.daniele.backpacker.dao;

import it.ispw.daniele.backpacker.DB.DBLoginConnection;

import java.sql.*;

public class LoginDAO extends DaoTemplate{

    public LoginDAO findUser(String username, String password) {
        return this.execute(new DaoAction<LoginDAO>() {
            @Override
            public LoginDAO act() throws SQLException, ClassNotFoundException {
                Connection conn = null;
                LoginDAO loginDAO = null;
                conn = DBLoginConnection.getLoginConnection();

                String sql = "call livethemusic.login(?, ?);\r\n";
                try(PreparedStatement stm = conn.prepareStatement(sql)){
                    stm.setString(1, username);
                    stm.setString(2, password);
                    try(ResultSet rs = stm.executeQuery()){
                        if(!rs.first()){
                            return null;
                        }
                        boolean moreThanOne = rs.first() && rs.next();
                        assert !moreThanOne;
                        rs.first();

                        String role = rs.getString("role");
                        String usernameLoaded = rs.getString("username");

                        if(usernameLoaded.equals(username)){
                            loginDAO = new LoginDAO();
                        }
                    }
                }
                return loginDAO;
            }
        });
    }
}
