package se.liu.ida.oscth887oskth878.tddc69.lab2.render;

import se.liu.ida.oscth887oskth878.tddc69.lab2.logic.Board;
import se.liu.ida.oscth887oskth878.tddc69.lab2.logic.SquareType;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created with IntelliJ IDEA.
 * User: Oskar
 * Date: 2013-09-17
 * Time: 19:33
 * To change this template use File | Settings | File Templates.
 */


public class TetrisComponent extends JComponent {
    private Board board;
    private static final int SQUARE_SIZE = 26;
    private static final int MARGIN = 2;

    public TetrisComponent(Board board) {
        this.board = board;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(board.WIDTH * (SQUARE_SIZE + MARGIN*2),
                             board.HEIGHT * (SQUARE_SIZE + MARGIN*2));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        for (int x = 0; x < board.WIDTH; x++) {
            for (int y = 0; y < board.HEIGHT; y++) {
                renderSquareAt(x, y, board.getSquareTypeShape(x, y), g2);
            }
        }
        for (int x = 0; x < board.getFallingPoly().getDimension().x; x++) {
            for (int y = 0; y < board.getFallingPoly().getDimension().y; y++) {
                if (board.getFallingPoly().getSquare(x,y) != null)
                    renderSquareAt(x + board.getFallingPolyPos().x, y + board.getFallingPolyPos().y, board.getFallingPoly().getShape(), g2);
            }
        }
    }

    private void renderSquareAt(int x, int y, SquareType.Shape shape, Graphics2D g2) {
        if (x > board.WIDTH || x < 0 || y > board.HEIGHT || y < 0)
            return;

        int inY = (board.HEIGHT - y-1);
        Shape box = new Rectangle(SQUARE_SIZE * x + MARGIN*(2*x+1),
                                  SQUARE_SIZE * inY + MARGIN*(2*inY+1),
                                  SQUARE_SIZE,
                                  SQUARE_SIZE);
        g2.setColor(colorMapper(shape));
        g2.fill(box);
        g2.draw(box);

    }

    private Color colorMapper (SquareType.Shape shape) {
        switch (shape){
            case I:
                return Color.CYAN;
            case O:
                return Color.RED;
            case T:
                return Color.YELLOW;
            case S:
                return Color.BLUE;
            case Z:
                return Color.MAGENTA;
            case J:
                return Color.GREEN;
            case L:
                return Color.ORANGE;
            case EMPTY:
                return Color.WHITE;
            case FRAME:
            case FRAME_NO_COLLIDE:
                return Color.DARK_GRAY;
            default:
                return Color.PINK;
        }
    }
}
