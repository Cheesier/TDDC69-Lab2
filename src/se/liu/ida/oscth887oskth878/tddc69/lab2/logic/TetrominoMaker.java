package se.liu.ida.oscth887oskth878.tddc69.lab2.logic;

import se.liu.ida.oscth887oskth878.tddc69.lab2.math.Vec2;

import java.util.Iterator;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: ostenip
 * Date: 9/10/13
 * Time: 4:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class TetrominoMaker {
    private int numberOfTypes = TetrominoBlueprints.blueprints.length;
    private Poly[] polies = new Poly[TetrominoBlueprints.blueprints.length];

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

    public void loadBlueprints() {
        for (int i = 0; i < numberOfTypes; i++) {
            parsePoly(TetrominoBlueprints.blueprints[i][0], TetrominoBlueprints.blueprints[i][1]);
        }
    }

    private Poly parsePoly(String name, String pattern) {
        Poly poly = new Poly(patternToSquare(name, pattern));
        return null;
    }

    private SquareType[][] patternToSquare(String name, String pattern) {
        Vec2 dim = getDimensions(pattern);

        SquareType[][] shape = new SquareType[dim.x][dim.y];

        return null;
    }

    private Vec2 getDimensions(String pattern) {
        Vec2 dim = new Vec2();
        boolean foundWidth = false;

        for (int i = 0; i < pattern.length(); i++) {
            if (pattern.charAt(i) == '\n') {
                if (!foundWidth) {
                    dim.x = i;
                    foundWidth = true;
                }
                dim.y++;
            }
        }

        dim.y++; // we don't use a newline char at the last row, compensate for that

        System.out.println(dim);
        return dim;
    }
}
