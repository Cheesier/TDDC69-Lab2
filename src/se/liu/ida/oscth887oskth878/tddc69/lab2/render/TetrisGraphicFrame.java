package se.liu.ida.oscth887oskth878.tddc69.lab2.render;

import se.liu.ida.oscth887oskth878.tddc69.lab2.logic.Board;
import se.liu.ida.oscth887oskth878.tddc69.lab2.logic.BoardListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created with IntelliJ IDEA.
 * User: Oskar
 * Date: 2013-09-17
 * Time: 20:38
 * To change this template use File | Settings | File Templates.
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
                "rotate");
        tetrisComponent.getActionMap().put("rotate",
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        tetrisComponent.rotate();
                    }
                });

        tetrisComponent.getInputMap().put(KeyStroke.getKeyStroke("LEFT"),
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
        tetrisComponent.getActionMap().put("moveRight",
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        tetrisComponent.moveRight();
                    }
                });

        tetrisComponent.getInputMap().put(KeyStroke.getKeyStroke("DOWN"),
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
}
