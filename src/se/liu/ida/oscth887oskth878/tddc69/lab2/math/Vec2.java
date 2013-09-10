package se.liu.ida.oscth887oskth878.tddc69.lab2.math;

/**
 * Created with IntelliJ IDEA.
 * User: ostenip
 * Date: 9/10/13
 * Time: 5:53 PM
 * To change this template use File | Settings | File Templates.
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
