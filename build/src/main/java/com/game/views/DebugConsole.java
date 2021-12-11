package com.game.views;

import javax.swing.*;

import com.game.Models.ball.Ball;
import com.game.controller.DebugPanel;
import com.game.controller.GameController;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 *  The DebugConsole class defines the properties of the DebugConsole JDialog.
 */
public class DebugConsole extends JDialog implements WindowListener{

    private static final String TITLE = "Debug Console";


    private GameFrame owner;
    private DebugPanel debugPanel;
    private GameBoard gameBoard;
    private GameController gamecontroller;


    public DebugConsole(GameFrame owner,GameController gamecontroller,GameBoard gameBoard){

        this.gamecontroller = gamecontroller;
        this.owner = owner;
        this.gameBoard = gameBoard;
        initialize();

        debugPanel = new DebugPanel(gamecontroller);
        this.add(debugPanel,BorderLayout.CENTER);


        this.pack();
    }

    /**
     * Method that initializes the properties of the debug console.
     */
    private void initialize(){
        this.setModal(true);
        this.setTitle(TITLE);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.addWindowListener(this);
        this.setFocusable(true);
    }

    /**
     * Method that sets the location of the debug console to the center of the frame.
     */
    private void setLocation(){
        int x = ((owner.getWidth() - this.getWidth()) / 2) + owner.getX();
        int y = ((owner.getHeight() - this.getHeight()) / 2) + owner.getY();
        this.setLocation(x,y);
    }


    
    /** 
     * Unsused interface.
     */
    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

    
    /** 
     * Repaints the gameboard upon the closing of the DebugConsole Jdialog.
     * 
     * @param windowEvent The parameter that defines what has occured to the JDialog window.
     */
    @Override
    public void windowClosing(WindowEvent windowEvent) {
        gameBoard.repaint();
    }

    
    /** 
     * Unused interface
     * 
     */
    @Override
    public void windowClosed(WindowEvent windowEvent) {

    }

    
    /** 
     * Unused interface
     * 
     */
    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    
    /** 
     * Unused interface
     * 
     */
    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    
    /** 
     * Interface that changes the X and Y axis speed of the ball upon closure.
     * 
     * @param windowEvent The parameter that defines what has occured to the JDialog window.
     */
    @Override
    public void windowActivated(WindowEvent windowEvent) {
        setLocation();
        Ball b = gamecontroller.ball;
        debugPanel.setValues(b.getSpeedX(),b.getSpeedY());
    }

    /** 
     * Unused interface
     * 
     */
    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }
}
