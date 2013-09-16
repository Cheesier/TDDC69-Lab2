package se.liu.ida.oscth887oskth878.tddc69.lab2.render;

import se.liu.ida.oscth887oskth878.tddc69.lab2.logic.Board;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: ostenip
 * Date: 9/16/13
 * Time: 7:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class TetrisFrame extends JFrame {
    JTextArea area;

    public TetrisFrame(Board board) {
        super("Tetris game!");

        area = new JTextArea(board.HEIGHT+1, board.WIDTH);
        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 16));
        this.add(area);

        this.setSize(area.getPreferredSize());
        this.setVisible(true);
        this.setResizable(false);
    }

    public void draw(Board board) {
        area.setText(TetrisTextView.convertToText(board));
    }
}
