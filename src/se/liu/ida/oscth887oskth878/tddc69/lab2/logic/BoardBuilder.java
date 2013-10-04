package se.liu.ida.oscth887oskth878.tddc69.lab2.logic;

import java.util.Random;

/**
 * @author Oscar Thunberg <oscth887>
 * @author Oskar Therén <oskth878>
 * @version 1.0
 * @since 10/09/13
 */
public class BoardBuilder {
    public static void randomizeBoard(Board board) {
        Random rand = new Random();
        SquareType.Shape[] availableShapes = SquareType.Shape.values();

        // don't generate at border
        for (int y = 1; y < board.HEIGHT-1; y++) {
            for (int x = 1; x < board.WIDTH-1; x++) {
                board.setSquareType(x, y, availableShapes[rand.nextInt(availableShapes.length - 1)]);
            }
        }
    }
}
