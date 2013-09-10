package se.liu.ida.oscth887oskth878.tddc69.lab2.render;

import se.liu.ida.oscth887oskth878.tddc69.lab2.logic.Board;
import se.liu.ida.oscth887oskth878.tddc69.lab2.logic.BoardBuilder;

/**
 * Created with IntelliJ IDEA.
 * User: ostenip
 * Date: 9/9/13
 * Time: 6:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class BoardTest {
    public static void main(String[] args) {
        Board board = new Board();
        for (int i = 0; i < 10; i++) {
            BoardBuilder.randomizeBoard(board);
            System.out.println(TetrisTextView.convertToText(board));
        }
    }
}
