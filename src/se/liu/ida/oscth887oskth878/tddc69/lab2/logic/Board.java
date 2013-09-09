package se.liu.ida.oscth887oskth878.tddc69.lab2.logic;

/**
 * Created with IntelliJ IDEA.
 * User: ostenip
 * Date: 9/9/13
 * Time: 4:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class Board {
    public final int WIDTH, HEIGHT;
    private SquareType[][] grid;

    public Board() {
        this(10, 20);
    }

    public Board(int WIDTH, int HEIGHT) {
        int frameSize = 1;
        this.WIDTH = WIDTH + frameSize * 2; // add the extra blocks to each side
        this.HEIGHT = HEIGHT + frameSize * 2;
        this.grid = new SquareType[WIDTH][HEIGHT];
    }
}
