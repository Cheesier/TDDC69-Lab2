package se.liu.ida.oscth887oskth878.tddc69.lab2.render;

import se.liu.ida.oscth887oskth878.tddc69.lab2.logic.Board;
import se.liu.ida.oscth887oskth878.tddc69.lab2.logic.BoardListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author: Oskar Therén <oskth878>
 * @author: Oscar Thunberg <oscth887>
 * @version 1.0
 * @since: 09/17/13
 */
public class TetrisGraphicFrame extends JFrame implements BoardListener{
    private TetrisComponent tetrisComponent;


    public TetrisGraphicFrame(Board board) throws HeadlessException {
        super("TETRIS");
        tetrisComponent = new TetrisComponent(board);

        this.add(tetrisComponent);

        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        tetrisComponent.getInputMap().put(KeyStroke.getKeyStroke("UP"),
                "rotateCC");
        tetrisComponent.getInputMap().put(KeyStroke.getKeyStroke("W"),
                "rotateCC");
        tetrisComponent.getInputMap().put(KeyStroke.getKeyStroke("X"),
                "rotateCC");
        tetrisComponent.getActionMap().put("rotateCC",
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        tetrisComponent.rotate(true);
                    }
                });


        tetrisComponent.getInputMap().put(KeyStroke.getKeyStroke("Z"),
                "rotateCCW");
        tetrisComponent.getActionMap().put("rotateCCW",
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        tetrisComponent.rotate(false);
                    }
                });


        tetrisComponent.getInputMap().put(KeyStroke.getKeyStroke("LEFT"),
                "moveLeft");
        tetrisComponent.getInputMap().put(KeyStroke.getKeyStroke("A"),
                "moveLeft");
        tetrisComponent.getActionMap().put("moveLeft",
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        tetrisComponent.moveLeft();
                    }
                });

        tetrisComponent.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"),
                "moveRight");
        tetrisComponent.getInputMap().put(KeyStroke.getKeyStroke("D"),
                "moveRight");
        tetrisComponent.getActionMap().put("moveRight",
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        tetrisComponent.moveRight();
                    }
                });

        tetrisComponent.getInputMap().put(KeyStroke.getKeyStroke("DOWN"),
                "moveDown");
        tetrisComponent.getInputMap().put(KeyStroke.getKeyStroke("S"),
                "moveDown");
        tetrisComponent.getActionMap().put("moveDown",
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        tetrisComponent.moveDown();
                    }
                });
    }

    @Override
    public void boardChanged() {
        repaint();
    }

    public static void gameOver() {
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog (null, "Would you like to exit the game?","Game Over",dialogButton);
        if(dialogResult == JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }
}
