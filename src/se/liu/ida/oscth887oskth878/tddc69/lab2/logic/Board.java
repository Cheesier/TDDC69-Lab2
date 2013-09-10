package se.liu.ida.oscth887oskth878.tddc69.lab2.logic;

import java.util.Arrays;

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
        this.grid = new SquareType[this.WIDTH][this.HEIGHT];

        initBoard();
    }

    private void initBoard() {
        /*
        for (SquareType[] squareTypes : grid) {
            Arrays.fill(squareTypes, new SquareType(SquareType.Shape.EMPTY));
        }
        */

        for (int x = 0; x < this.WIDTH; x++) {
            for (int y = 0; y < this.HEIGHT; y++) {
                // check if outer most block
                if ((x == 0 || y == 0) || (x == this.WIDTH-1 || y == this.HEIGHT-1)) {
                    grid[x][y] = new SquareType(SquareType.Shape.FRAME);
                }
                else {
                    grid[x][y] = new SquareType(SquareType.Shape.EMPTY);
                }
            }
        }
    }

    public SquareType getSquareType(int x, int y) {
        return grid[x][y];
    }
}
