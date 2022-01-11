package it.ispw.daniele.backpacker.utils;

import java.io.File;
import java.util.random.RandomGenerator;

public class FileManager {

    private static final String PROJECT = System.getProperty("user.home") + File.separator
            + "Desktop" + File.separator + "Backpacker" + File.separator + "trunk" + File.separator
            + "WebContent" + File.separator + "images" + File.separator;

    public static final String PROFILE = PROJECT + "profilePictures";

    private FileManager(){}

    public static String generateNewFileName(String fileName, boolean needSalt, String username){
        String newFileName = "";
        if(!fileName.equals("")){
            newFileName = username;
            if(needSalt){
                newFileName = newFileName + RandomNumberGenerator.getInstance().randomInt();
            }
            newFileName = newFileName + fileName;
        }
        return newFileName;
    }
}
