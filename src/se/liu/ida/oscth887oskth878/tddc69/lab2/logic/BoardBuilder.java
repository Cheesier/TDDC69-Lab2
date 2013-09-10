package se.liu.ida.oscth887oskth878.tddc69.lab2.logic;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: ostenip
 * Date: 9/10/13
 * Time: 3:39 PM
 * To change this template use File | Settings | File Templates.
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
