package se.liu.ida.oscth887oskth878.tddc69.lab2.logic;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: ostenip
 * Date: 9/10/13
 * Time: 4:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class TetrominoMaker {
    private int numberOfTypes = 0;
    private Poly[] polies;

    public int getNumberOfTypes() {
        return numberOfTypes;
    }

    public Poly getPoly(int n) {
        return polies[n];
    }

    public Poly getRandomPoly() {
        Random rand = new Random();
        return polies[rand.nextInt(numberOfTypes)];
    }
}
