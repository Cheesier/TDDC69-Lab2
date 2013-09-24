package se.liu.ida.oscth887oskth878.tddc69.lab2.render;

import se.liu.ida.oscth887oskth878.tddc69.lab2.logic.*;
import se.liu.ida.oscth887oskth878.tddc69.lab2.math.Vec2;

/**
 * @author: Oscar Thunberg <oscth887>
 * @author: Oskar Therén <oskth878>
 * @version 1.0
 * @since: 09/09/13
 */
public class TetrisTextView {
    public static String convertToText(Board board) {
        StringBuilder result = new StringBuilder();

        Poly fallingPoly = board.getFallingPoly();
        Vec2 fallingPolyPos = board.getFallingPolyPos();

        for (int y = 0; y < board.HEIGHT; y++) {
            for (int x = 0; x < board.WIDTH; x++) {
                result.append(getRepresentation(board.getSquareTypeShape(x, y)));
            }
            result.append("\n");
        }

        for (int y = 0; y < fallingPoly.getDimension().y; y++) {
            for (int x = 0; x < fallingPoly.getDimension().x; x++) {
                int index = getIndexFor(board, fallingPolyPos.x + x, fallingPolyPos.y +fallingPoly.getDimension().y - y);
                if (fallingPoly.getSquare(x, y) == null)
                    continue;

                if (index > 0 && index < getIndexFor(board, board.WIDTH, 0))
                    if (result.charAt(index) != getRepresentation(SquareType.Shape.FRAME))
                        result.setCharAt(index, getRepresentation(fallingPoly.getShape()));
            }
        }


        return result.toString();
    }

    private static int getIndexFor(Board board, int x, int y) {
        int origin = board.WIDTH * board.HEIGHT + board.HEIGHT - board.WIDTH-1;
        return origin - board.WIDTH*y - y+x;
    }

    private static char getRepresentation(SquareType.Shape shape) {
        switch (shape) {
            case EMPTY:
                return ' ';
            case FRAME:
                return '#';
            default:
                return shape.toString().charAt(0);
        }
    }
}
