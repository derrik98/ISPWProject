package it.ispw.daniele.backpacker.utils;

import java.util.Random;

public class RandomNumberGenerator {

    private static  RandomNumberGenerator instance = null;
    private static final int MIN = 0;
    private static final int MAX = 10000;
    private static Random r = new Random();

    private RandomNumberGenerator(){}

    public static RandomNumberGenerator getInstance(){
        if(instance == null){
            instance = new RandomNumberGenerator();
        }
        return instance;
    }

    public int randomInt(){
        return r.nextInt((MAX - MIN) + 1) + MIN;
    }
}
