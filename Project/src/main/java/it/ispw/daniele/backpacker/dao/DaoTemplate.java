package it.ispw.daniele.backpacker.dao;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class DaoTemplate {

    protected static final Logger logger = Logger.getLogger("Dao");

    protected final <T> T execute(DaoAction <T> daoAction){
        try {
            return daoAction.act();
        }catch (SQLException | ClassNotFoundException exception){
            logger.log(Level.WARNING, exception.toString());
        }

        return null;

    }

}
