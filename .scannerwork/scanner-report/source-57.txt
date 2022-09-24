package it.ispw.daniele.backpacker.dao;

import java.sql.SQLException;

public interface DaoAction<G> {
    G act() throws SQLException, ClassNotFoundException;
}
