package se.liu.ida.oscth887oskth878.tddc69.lab2.logic;

import se.liu.ida.oscth887oskth878.tddc69.lab2.math.Vec2;

/**
 * Created with IntelliJ IDEA.
 * User: ostenip
 * Date: 9/10/13
 * Time: 4:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class Poly {
    private SquareType[][] squares;
    private SquareType.Shape shape;

    public Poly(String shape, String pattern) {
        this.shape = SquareType.Shape.valueOf(shape);

        parsePoly(pattern);
    }

    public SquareType.Shape getShape() {
        return shape;
    }

    public void rotate(boolean clockwise) {
        if (clockwise) {
            //rotate it clockwise
        }
        else {
            // rotate it ccw
        }
    }

    private void parsePoly(String pattern) {
        Vec2 dim = getDimensions(pattern);
        squares = new SquareType[dim.x][dim.y];

        int x = 0, y = 0;

        for (int i = 0; i < pattern.length(); i++) {
            switch (pattern.charAt(i)) {
                case '#':
                    squares[x][y] = new SquareType(this.shape);
                    x++;
                    break;
                case ' ':
                    squares[x][y] = null;
                    x++;
                    break;
                case '\n':
                    x = 0;
                    y++;
                    break;
                default:
                    System.err.println("Found invalid sign '" + pattern.charAt(i) + "' while parsing Poly.");
                    break;
            }
        }

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
        return dim;
    }


}
