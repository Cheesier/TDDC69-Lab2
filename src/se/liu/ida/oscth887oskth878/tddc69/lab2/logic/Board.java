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
        int boarderWidth = 1;
        this.WIDTH = WIDTH + boarderWidth * 2; // add the extra blocks to each side
        this.HEIGHT = HEIGHT + boarderWidth * 2;
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

    public SquareType.Shape getSquareTypeShape(int x, int y) {
        return grid[x][y].getShape();
    }

    void setSquareType(int x, int y, SquareType.Shape shape) {
        if (shape == SquareType.Shape.FRAME)
            throw new IllegalArgumentException("You are not allowed to place walls."); // lack of custom exception

        if (getSquareTypeShape(x, y) == SquareType.Shape.FRAME)
            throw new IllegalArgumentException("You are not allowed to replace walls.");


        grid[x][y].setShape(shape);
    }
}
