package it.ispw.daniele.backpacker.dao;

import it.ispw.daniele.backpacker.entity.User;
import it.ispw.daniele.backpacker.utils.DBLoginConnection;
import it.ispw.daniele.backpacker.utils.DBUserConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserDAO extends DaoTemplate{

//    private static final String FOLLOW = "follow";
//    private static final String UNFOLLOW = "unfollow";
//    private static final String REMOVEFRIEND = "removefriend";
//    private static final String ACCEPTFRIENDREQUEST = "acceptfriendrequest";
//    private static final String REMOVEFRIENDREQUEST = "removefrinedrequest";
//    private static final String REQUESTFRIEND = "requestfriend";
//    private static final String SEARCHUSER = "searchuser";
//    private static final String VIEWFRIENDS = "viewfriends";
//    private static final String VIEWFRIENDSREQUESTS = "viewfriendsrequests";
//    private static final String SEARCHFRIENDREQUEST = "searchfriendrequest";
//    private static final String ISFRIEND = "isfriend";
//    private static final String ISFOLLOWING = "isfollowing";

    public Boolean createUser(String username, String name, String surname,
                              String email, String password, String profilePicture) {
        return (this.execute(new DaoAction<Boolean>() {
            @Override
            public Boolean act() throws ClassNotFoundException, SQLException {

                System.out.println("sono qui");
                Connection con = DBUserConnection.getUserConnection();
                if(con == null){
                    System.out.println("connessoine non stabilita");
                }
                else{
                    System.out.println("connessoine stabilita");
                }

                String sql = "call backpacker.add_user(?, ?, ?, ?, ?, ?);\r\n";
                try (PreparedStatement stm = con.prepareStatement(sql)) {
                    stm.setString(1, username);
                    stm.setString(2, name);
                    stm.setString(3, surname);
                    stm.setString(4, email);
                    stm.setString(5, password);
                    stm.setString(6, profilePicture);
                    stm.executeUpdate();
                }
                return true;
            }
        }) != null);
    }

//    public boolean isFollowing(String username, String artistId) {
//        return this.isQueryDataBase(username, artistId, ISFOLLOWING);
//    }
//
//    public void follow(String username, String artist) {
//        this.manageFollow(username, artist, FOLLOW);
//    }
//
//    public void unfollow(String username, String artist) {
//        this.manageFollow(username, artist, UNFOLLOW);
//    }
//
//    public List<User> getSearchUser(String searchString, String caller){
//        return this.queryDatabase(searchString, caller, SEARCHUSER);
//    }
//
//    public List<User> getFriends(String username){
//        return this.queryDatabase(username, "", VIEWFRIENDS);
//    }
//
//    public List<User> getFriendRequests(String username){
//        return this.queryDatabase(username, "", VIEWFRIENDSREQUESTS);
//    }
//
//    public boolean isFriend(String user, String target) {
//        return this.isQueryDataBase(user, target, ISFRIEND);
//    }
//
//    public boolean isRequestSent(String user, String target) {
//        return this.isQueryDataBase(user, target, SEARCHFRIENDREQUEST);
//    }
//
//    public void requestFriend(String user, String target) {
//        this.manageFriends(user, target, REQUESTFRIEND);
//    }
//
//    public void removeFriendRequest(String user, String target) {
//        this.manageFriends(user, target, REMOVEFRIENDREQUEST);
//    }
//
//    public void acceptFriendRequest(String user, String target) {
//        this.manageFriends(user, target, ACCEPTFRIENDREQUEST);
//    }
//
//    public void removeFriend(String user, String target) {
//        this.manageFriends(user, target, REMOVEFRIEND);
//    }

//    private void manageFollow(String username, String artist, String operation) {
//        this.execute(new DaoAction<Void>() {
//            @Override
//            public Void act() throws ClassNotFoundException, SQLException {
//                String sql = null;
//                Connection conn = DBUserConnection.getUserConnection();
//                if(operation.equals(FOLLOW)) {
//                    sql = "call livethemusic.follow_artist(?, ?);\r\n";
//                } else if(operation.equals(UNFOLLOW)) {
//                    sql = "call livethemusic.unfollow_artist(?, ?);\r\n";
//                }
//                try (PreparedStatement stm = conn.prepareStatement(sql)) {
//                    stm.setString(1, username);
//                    stm.setString(2, artist);
//                    stm.executeUpdate();
//                    return null;
//                }
//            }
//        });
//
//    }

//    private void manageFriends(String user, String target, String operation) {
//        this.execute(new DaoAction<Void>() {
//            @Override
//            public Void act() throws ClassNotFoundException, SQLException {
//                String sql = null;
//                Connection conn = DBUserConnection.getUserConnection();
//                if(operation.equals(REMOVEFRIEND)) {
//                    sql = "call livethemusic.remove_friend(?, ?);\r\n";
//                } else if(operation.equals(ACCEPTFRIENDREQUEST)) {
//                    sql = "call livethemusic.accept_friend_request(?, ?);\r\n";
//                } else if(operation.equals(REMOVEFRIENDREQUEST)) {
//                    sql = "call livethemusic.remove_friend_request(?, ?);\r\n";
//                } else if(operation.equals(REQUESTFRIEND)){
//                    sql = "call livethemusic.request_friend(?, ?);\r\n";
//                }
//                try (PreparedStatement stm = conn.prepareStatement(sql)) {
//                    stm.setString(1, user);
//                    stm.setString(2, target);
//                    stm.executeUpdate();
//                    return null;
//                }
//            }
//        });
//    }

//    private List<User> queryDatabase(String string, String caller, String operation){
//        List <User> ret = this.execute(new DaoAction<List<User>>() {
//            @Override
//            public List<User> act() throws ClassNotFoundException, SQLException {
//                List<User> l = new ArrayList<>();
//                Connection conn = DBUserConnection.getUserConnection();
//                PreparedStatement stm = null;
//                try {
//                    String sql;
//                    switch (operation) {
//                        case SEARCHUSER:
//                            sql = "call livethemusic.search_user(?, ?);\r\n";
//                            stm = conn.prepareStatement(sql);
//                            stm.setString(1, string);
//                            stm.setString(2, caller);
//                            break;
//                        case VIEWFRIENDS:
//                            sql = "call livethemusic.view_friends(?);\r\n";
//                            stm = conn.prepareStatement(sql);
//                            stm.setString(1, string);
//                            break;
//                        case VIEWFRIENDSREQUESTS:
//                            sql = "call livethemusic.view_friend_requests(?);\r\n";
//                            stm = conn.prepareStatement(sql);
//                            stm.setString(1, string);
//                            break;
//                        default:
//                            return Collections.emptyList();
//                    }
//                    try (ResultSet rs = stm.executeQuery()) {
//                        if (!rs.first()) // rs not empty
//                            return Collections.emptyList();
//
//                        do{
//                            String username = rs.getString("username");
//                            String name = rs.getString("name");
//                            String surname = rs.getString("surname");
//                            String profilePicture = rs.getString("profile_picture_path");
//
//                            if(profilePicture == null || profilePicture.equals("")) {
//                                profilePicture = "concert.jpg";
//                            }
//
//                            l.add(new User(username, name, surname, profilePicture));
//                        } while (rs.next());
//                        return l;
//                    }
//                } finally {
//                    if (stm != null)
//                        stm.close();
//                }
//            }
//        });
//        if (ret != null) {
//            return ret;
//        } else {
//            return Collections.emptyList();
//        }
//    }


//    private Boolean isQueryDataBase(String user, String target, String operation) {
//        Boolean ret = this.execute(new DaoAction<Boolean>() {
//            @Override
//            public Boolean act() throws ClassNotFoundException, SQLException {
//                String sql = null;
//                Connection conn = DBUserConnection.getUserConnection();
//                if(operation.equals(SEARCHFRIENDREQUEST)) {
//                    sql = "call livethemusic.search_friend_request(?, ?);\r\n";
//                } else if(operation.equals(ISFRIEND)) {
//                    sql = "call livethemusic.is_friend(?, ?);\r\n";
//                } else if(operation.equals(ISFOLLOWING)) {
//                    sql = "call livethemusic.is_following(?, ?);\r\n";
//                }
//                try (PreparedStatement stm = conn.prepareStatement(sql)) {
//                    stm.setString(1, user);
//                    stm.setString(2, target);
//                    try (ResultSet rs = stm.executeQuery()) {
//                        return rs.first();
//                    }
//                }
//            }
//        });
//        if (ret != null) {
//            return ret;
//        } else {
//            return false;
//        }
//    }

}
