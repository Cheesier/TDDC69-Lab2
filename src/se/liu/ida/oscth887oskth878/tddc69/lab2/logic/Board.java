package se.liu.ida.oscth887oskth878.tddc69.lab2.logic;

import se.liu.ida.oscth887oskth878.tddc69.lab2.math.Vec2;

import java.util.ArrayList;
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
        notifyListners();
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

    private void clearLines() {
        //int[] lines = new int[fallingPoly.getDimension().y];
        int lines = 0;
        for (int y = 0; y < fallingPoly.getDimension().y; y++) {
            for (int x = 0; x < this.WIDTH; x++) {
                if (getSquareTypeShape(x, getFallingPolyPos().y + y) == SquareType.Shape.EMPTY)
                    break;
                if (x == this.WIDTH - 1) {
                    //lines[y] = y + getFallingPolyPos().y;
                    lines++;
                    for (int x2 = 1; x2 < this.WIDTH - 1; x2++) {
                        setSquareType(x2, getFallingPolyPos().y + y, SquareType.Shape.EMPTY);
                    }
                }
            }
        }
        if (lines > 0) {
            for (int y = 0; y < fallingPoly.getDimension().y; y++) {
                for (int x = 1; x < (this.WIDTH - 1); x++) {
                    if (getSquareTypeShape(x, getFallingPolyPos().y + y) != SquareType.Shape.EMPTY) {
                        for (int x2 = 1; x2 < (this.WIDTH -1); x2++) {
                            SquareType.Shape temp = getSquareTypeShape(x2, y + fallingPolyPos.y);
                            setSquareType(x2, y + fallingPolyPos.y, getSquareTypeShape(x2, y + fallingPolyPos.y - lines));
                            setSquareType(x2, y + fallingPolyPos.y - lines, temp);
                            break;
                        }
                    }
                }
            }
        }
    }

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
                        System.out.println(shape.toString());
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
                        System.out.println(shape.toString());
                        return false;
                    }
                }
            }
        }
        return true;

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
                    if (y == this.HEIGHT-1)
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

    private void newFallingPoly() {
        fallingPoly = tetroMaker.getRandomPoly();
        fallingPolyPos.x = (this.WIDTH / 2) - (fallingPoly.getDimension().x / 2);
        fallingPolyPos.y = this.HEIGHT-fallingPoly.getDimension().y;

        if (!canFall()) {
            this.gameOver = true;
            System.out.println("Game over");
        }

        notifyListners();
    }

    public Poly getFallingPoly() {
        return fallingPoly;
    }

    public Vec2 getFallingPolyPos() {
        return fallingPolyPos;
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

    public void addBoardListener(BoardListener bl) {
        boardListeners.add(bl);
    }

    private void notifyListners() {
        for (int i = 0; i < boardListeners.size(); i++)
            boardListeners.get(i).boardChanged();
    }

    public void move(int anmount) {
        fallingPolyPos.x += anmount;
        if (!isValidPlacement())
            fallingPolyPos.x -= anmount;
        notifyListners();
    }

    public void rotate (boolean clocwise) {
        fallingPoly.rotate(true);
        if (!isValidPlacement())
            fallingPoly.rotate(false);
        notifyListners();
    }

}
