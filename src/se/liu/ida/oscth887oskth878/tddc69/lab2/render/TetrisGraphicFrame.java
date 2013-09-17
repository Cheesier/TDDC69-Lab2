package se.liu.ida.oscth887oskth878.tddc69.lab2.render;

import se.liu.ida.oscth887oskth878.tddc69.lab2.logic.Board;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Oskar
 * Date: 2013-09-17
 * Time: 20:38
 * To change this template use File | Settings | File Templates.
 */
public class TetrisGraphicFrame extends JFrame {
    private TetrisComponent tetrisComponent;

    public TetrisGraphicFrame(Board board) throws HeadlessException {
        super("TETRIS");
        tetrisComponent = new TetrisComponent(board);

        this.add(tetrisComponent);

        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
