package se.liu.ida.oscth887oskth878.tddc69.lab2.render;

import se.liu.ida.oscth887oskth878.tddc69.lab2.logic.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author: Oscar Thunberg <oscth887>
 * @author: Oskar Therén <oskth878>
 * @version 1.0
 * @since: 16/09/13
 */
public class TetrisFrame extends JFrame {
    JTextArea area;
    JMenuBar menuBar = new JMenuBar();
    JMenu gameMenu = new JMenu("Spel");

    public TetrisFrame(Board board) {
        super("Tetris game!");

        JMenuItem exit = new JMenuItem("Avsluta", 'A');
        gameMenu.add(exit);
        this.add(gameMenu);
        gameMenu.setVisible(true);
        exit.addActionListener(killProgramAction);

        menuBar.add(gameMenu);
        this.setJMenuBar(menuBar);

        area = new JTextArea(board.HEIGHT+1, board.WIDTH);
        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 20));
        this.add(area);

        this.pack();
        this.setVisible(true);
        this.setResizable(false);
    }

    public void draw(Board board) {
        area.setText(TetrisTextView.convertToText(board));
    }

    Action killProgramAction = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Closing program.");
            System.exit(0);
        }
    };
}
