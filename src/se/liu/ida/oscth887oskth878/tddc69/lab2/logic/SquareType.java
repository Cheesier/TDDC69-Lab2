package se.liu.ida.oscth887oskth878.tddc69.lab2.logic;

/**
 * @author Oscar Thunberg <oscth887>
 * @author Oskar Therén <oskth878>
 * @version 1.0
 * @since 09/09/13
 */
public class SquareType {
    public enum Shape {
        I, O, T, S, Z, J, L, EMPTY, FRAME, FRAME_NO_COLLIDE
    }

    private Shape shape;

    public SquareType(Shape shape) {
        this.shape = shape;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }
}
