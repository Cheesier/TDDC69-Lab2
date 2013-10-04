package se.liu.ida.oscth887oskth878.tddc69.lab2.logic;

import java.util.Random;

/**
 * @author Oscar Thunberg <oscth887>
 * @author Oskar Therén <oskth878>
 * @version 1.0
 * @since 10/09/13
 */
public class TetrominoMaker {
    private Poly[] polies;
    private Random rand = new Random();

    public TetrominoMaker(String[][] blueprints) {
        polies = new Poly[blueprints.length];

        loadBlueprints(blueprints);
    }

    public int getNumberOfTypes() {
        return this.polies.length;
    }

    public Poly getPoly(int n) {
        return polies[n];
    }

    public Poly getRandomPoly() {
        return polies[rand.nextInt(polies.length)];
    }

    private void loadBlueprints(String[][] blueprints) {
        for (int i = 0; i < blueprints.length; i++) {
            polies[i] = new Poly(blueprints[i][0], blueprints[i][1]);
        }
    }
}
