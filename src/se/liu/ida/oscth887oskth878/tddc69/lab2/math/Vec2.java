package se.liu.ida.oscth887oskth878.tddc69.lab2.math;

/**
 * @author Oscar Thunberg <oscth887>
 * @author Oskar Therén <oskth878>
 * @version 1.0
 * @since 10/09/13
 */
public class Vec2 {
    public int x, y;

    public Vec2() {
    }

    public Vec2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "{" + x +
                ", " +  y +
                '}';
    }
}
