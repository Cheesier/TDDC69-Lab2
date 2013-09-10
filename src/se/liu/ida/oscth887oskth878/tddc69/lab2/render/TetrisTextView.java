package se.liu.ida.oscth887oskth878.tddc69.lab2.render;

import se.liu.ida.oscth887oskth878.tddc69.lab2.logic.*;

/**
 * Created with IntelliJ IDEA.
 * User: ostenip
 * Date: 9/9/13
 * Time: 5:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class TetrisTextView {
    public static String convertToText(Board board) {
        StringBuilder result = new StringBuilder();

        for (int y = 0; y < board.HEIGHT; y++) {
            for (int x = 0; x < board.WIDTH; x++) {
                result.append(getRepresentation(board.getSquareType(x, y).getShape()));
            }
            result.append("\n");
        }

        return result.toString();
    }

    private static String getRepresentation(SquareType.Shape shape) {
        switch (shape) {
            case EMPTY:
                return " ";
            case FRAME:
                return "#";
            default:
                return shape.toString();
        }
    }
}
