package se.liu.ida.oscth887oskth878.tddc69.lab2.render;

import se.liu.ida.oscth887oskth878.tddc69.lab2.logic.Board;
import se.liu.ida.oscth887oskth878.tddc69.lab2.logic.BoardBuilder;
import se.liu.ida.oscth887oskth878.tddc69.lab2.logic.TetrominoBlueprints;
import se.liu.ida.oscth887oskth878.tddc69.lab2.logic.TetrominoMaker;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * @author: Oscar Thunberg <oscth887>
 * @author: Oskar Therén <oskth878>
 * @version 1.0
 * @since: 09/09/13
 */
public class BoardTest {
    static Board board;
    static TetrisGraphicFrame window;
    static Timer clockTimer;

    static final Action doOneStep = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!board.tick()) {
                TetrisGraphicFrame.gameOver();
                clockTimer.stop();
            }
        }
    };

    public static void main(String[] args) {
        board = new Board();
        window = new TetrisGraphicFrame(board);
        board.addBoardListener(window);

        clockTimer = new Timer(200, doOneStep);
        clockTimer.setCoalesce(true);
        clockTimer.start();

    }
}
