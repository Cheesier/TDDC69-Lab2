package se.liu.ida.oscth887oskth878.tddc69.lab2.logic;

import se.liu.ida.oscth887oskth878.tddc69.lab2.math.Vec2;

import java.util.ArrayList;

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
    private Poly fallingPoly = null;
    private int fallingX, fallingY;
    private Vec2 fallingPolyPos = new Vec2();
    private TetrominoMaker tetroMaker = new TetrominoMaker(TetrominoBlueprints.blueprints);
    private ArrayList<BoardListener> boardListeners = new ArrayList<BoardListener>();
    private boolean gameOver = false;

    public Board() {
        this(10, 20);
    }

    public Board(int WIDTH, int HEIGHT) {
        int boarderWidth = 1;
        this.WIDTH = WIDTH + boarderWidth * 2; // add the extra blocks to each side
        this.HEIGHT = HEIGHT + boarderWidth * 2;
        this.grid = new SquareType[this.WIDTH][this.HEIGHT];

        initBoard();
        System.out.println("Created a new board: " + this.WIDTH + ", " + this.HEIGHT);
    }

    public void tick() {
        if (gameOver)
            return;

        updatePolyPos();
        notifyListeners();
    }

    private void updatePolyPos() {
        if (canFall())
            this.fallingPolyPos.y--;
        else {
            placePoly();
            clearLines();
            newFallingPoly();
        }
    }

    // Removes, if needed, the lines that are full
    private void clearLines() {
        for (int y = fallingPolyPos.y; y < fallingPolyPos.y + fallingPoly.getDimension().y; y++) {
            if (isLineFull(y)) {
                for (int y2 = y; y2 < this.HEIGHT-2; y2++) {
                    dropLine(y2+1, 1);
                }
                y--;
            }
        }
    }

    private boolean isLineEmpty(int y) {
        for (int x = 1; x < WIDTH-1; x++)
            if (getSquareTypeShape(x, y) != SquareType.Shape.EMPTY)
                return true;
        return false;
    }

    private boolean isLineFull(int y) {
        for (int x = 1; x < WIDTH-1; x++)
            switch (getSquareTypeShape(x, y)) {
                case EMPTY:
                case FRAME_NO_COLLIDE:
                    return false;
            }
            return true;
    }

    private void emptyLine(int y) {
        for (int x = 1; x < WIDTH-1; x++) {
            setSquareType(x, y, SquareType.Shape.EMPTY);
        }
    }

    private void dropLine(int y, int amount) {
        for (int x = 1; x < WIDTH-1; x++) {
            setSquareType(x, y-amount, getSquareTypeShape(x, y));
        }
        emptyLine(y);
    }

    // Break down a poly to SquareTypes and put on board
    private void placePoly() {
        for (int x = 0; x < fallingPoly.getDimension().x; x++) {
            for (int y = 0; y < fallingPoly.getDimension().y; y++) {
                if (fallingPoly.getSquare(x, y) != null) {
                    setSquareType(getFallingPolyPos().x + x,
                                  getFallingPolyPos().y + y,
                                  fallingPoly.getShape());
                }
            }
        }
    }

    private boolean canFall() {
        for (int x = 0; x < fallingPoly.getDimension().x; x++) {
            for (int y = 0; y < fallingPoly.getDimension().y; y++) {
                if (fallingPoly.getSquare(x, y) != null) {
                    SquareType.Shape shape = getSquareTypeShape(getFallingPolyPos().x + x,
                                                                getFallingPolyPos().y + y - 1);
                    if (shape != SquareType.Shape.EMPTY && shape != SquareType.Shape.FRAME_NO_COLLIDE) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean isValidPlacement() {
        for (int x = 0; x < fallingPoly.getDimension().x; x++) {
            for (int y = 0; y < fallingPoly.getDimension().y; y++) {
                if (fallingPoly.getSquare(x, y) != null) {
                    SquareType.Shape shape = getSquareTypeShape(getFallingPolyPos().x + x,
                                                                getFallingPolyPos().y + y);
                    if (shape != SquareType.Shape.EMPTY && shape != SquareType.Shape.FRAME_NO_COLLIDE) {
                        return false;
                    }
                }
            }
        }
        return true;

    }

    // Initialize the board with a FRAME around the entire board and fill the other parts with EMPTY
    private void initBoard() {
        for (int x = 0; x < this.WIDTH; x++) {
            for (int y = 0; y < this.HEIGHT; y++) {
                // check if outer most block
                if ((x == 0 || y == 0) || (x == this.WIDTH-1 || y == this.HEIGHT-1)) {
                    if (y == this.HEIGHT-1 && (x > 0 && x < this.WIDTH-1))
                        grid[x][y] = new SquareType(SquareType.Shape.FRAME_NO_COLLIDE);
                    else
                        grid[x][y] = new SquareType(SquareType.Shape.FRAME);
                }
                else {
                    grid[x][y] = new SquareType(SquareType.Shape.EMPTY);
                }
            }
        }

        newFallingPoly();
    }

    // generate a new Poly
    private void newFallingPoly() {
        fallingPoly = tetroMaker.getRandomPoly();
        fallingPolyPos.x = (this.WIDTH / 2) - (fallingPoly.getDimension().x / 2);
        fallingPolyPos.y = this.HEIGHT-1;

        if (!canFall()) {
            this.gameOver = true;
            System.out.println("Game over");
        }

        notifyListeners();
    }

    public Poly getFallingPoly() {
        return fallingPoly;
    }

    public Vec2 getFallingPolyPos() {
        return fallingPolyPos;
    }

    public SquareType.Shape getSquareTypeShape(int x, int y) {
        if (x < 0 || x > this.WIDTH-1 || y < 0 || y > this.HEIGHT-1)
            return SquareType.Shape.EMPTY;
        return grid[x][y].getShape();
    }

    void setSquareType(int x, int y, SquareType.Shape shape) {
        if (shape == SquareType.Shape.FRAME)
            throw new IllegalArgumentException("You are not allowed to place walls."); // lack of custom exception

        if (getSquareTypeShape(x, y) == SquareType.Shape.FRAME)
            throw new IllegalArgumentException("You are not allowed to replace walls.");

        if (x >= 0 && x <= this.WIDTH-1 && y >= 0 && y <= this.HEIGHT-1)
            grid[x][y].setShape(shape);
    }

    public void addBoardListener(BoardListener bl) {
        boardListeners.add(bl);
    }

    private void notifyListeners() {
        for (int i = 0; i < boardListeners.size(); i++)
            boardListeners.get(i).boardChanged();
    }

    public void move(int amount) {
        fallingPolyPos.x += amount;
        if (!isValidPlacement())
            fallingPolyPos.x -= amount;
        notifyListeners();
    }

    public void rotate (boolean clockwise) {
        fallingPoly.rotate(clockwise);
        if (!isValidPlacement())
            fallingPoly.rotate(false);
        notifyListeners();
    }

}
