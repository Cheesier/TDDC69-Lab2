package se.liu.ida.oscth887oskth878.tddc69.lab2.logic;

/**
 * Created with IntelliJ IDEA.
 * User: ostenip
 * Date: 9/9/13
 * Time: 4:31 PM
 * To change this template use File | Settings | File Templates.
 */

enum Shape {
    EMPTY, I, O, T, S, Z, J, L, OUTSIDE
}

public class SquareType {
    private Shape shape;

    public SquareType(Shape shape) {
        this.shape = shape;
    }

    public Shape getShape() {
        return shape;
    }
}
