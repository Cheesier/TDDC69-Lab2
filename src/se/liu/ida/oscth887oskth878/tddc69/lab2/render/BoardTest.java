package se.liu.ida.oscth887oskth878.tddc69.lab2.render;

import se.liu.ida.oscth887oskth878.tddc69.lab2.logic.Board;
import se.liu.ida.oscth887oskth878.tddc69.lab2.logic.BoardBuilder;
import se.liu.ida.oscth887oskth878.tddc69.lab2.logic.TetrominoBlueprints;
import se.liu.ida.oscth887oskth878.tddc69.lab2.logic.TetrominoMaker;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created with IntelliJ IDEA.
 * User: ostenip
 * Date: 9/9/13
 * Time: 6:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class BoardTest {
    static Board board;
    static TetrisGraphicFrame window;

    static final Action doOneStep = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            board.tick();
            //window.draw(board);
        }
    };

    public static void main(String[] args) {
        board = new Board();
        window = new TetrisGraphicFrame(board);
        board.addBoardListener(window);

        final Timer clockTimer = new Timer(300, doOneStep);
        clockTimer.setCoalesce(true);
        clockTimer.start();

    }
}
