package com.game.views;

import javax.swing.*;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

/**
 * Class that opens the GameFrame JFrame. 
 * Said GameFrame will accomodate other view such as the GameBoard, Highscore Page, HomeMenu and InfoPage.
 * By default the first view is the HomeMenu.
 */
public class GameFrame extends JFrame implements WindowFocusListener {

    private static final String DEF_TITLE = "Brick Destroyer";

    private static GameBoard gameBoard;
    private static HomeMenu homeMenu;
    private static InfoPage infopage;
    private static HighscorePage highscorepage;

    private static boolean gaming;

    public GameFrame(){
        super();
        
        homeMenu = new HomeMenu(this);
        this.add(homeMenu);
        
        gaming = true;
    }

    /**
     * Method that initializes the GameFrame
     */
    public void initialize(){
        this.setTitle(DEF_TITLE);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setSize(615,490);
        this.setResizable(false);
        this.setVisible(true);
        this.addWindowFocusListener(this);

        ImageIcon icon = new ImageIcon(getClass().getResource("/brick.png"));
        this.setIconImage(icon.getImage());
    }

    /**
     * Method that enables the GameBoard and removes the HomeMenu.
     */
    public void enableGameBoard(){
        gameBoard = new GameBoard(this);
        this.add(gameBoard,BorderLayout.CENTER);
        this.remove(homeMenu);
        
        this.initialize();
    }

    /**
     * Method that enables the Infopage and removes the HomeMenu.
     */
    public void enableInfo(){
        infopage = new InfoPage(this);
        this.add(infopage,BorderLayout.CENTER);
        this.remove(homeMenu);
        
        this.initialize();
    }

    /**
     * Method that enables the HomeMenu and removes the InfoPage.
     */
    public void revertInfopage(){
        this.add(homeMenu,BorderLayout.CENTER);
        this.remove(infopage);
        
        this.initialize();
    }

    /**
     * Method that enables the HomeMenu and removes the GameBoard.
     */
    public void revertGameboard(){
        this.add(homeMenu,BorderLayout.CENTER);
        this.remove(gameBoard);
        
        this.initialize();

    }

    /**
     * Method that enables the HighscorePage and remvoes the HomeMenu.
     */
    public void enableHighscore(){
        highscorepage = new HighscorePage(this);
        this.add(highscorepage,BorderLayout.CENTER);
        this.remove(homeMenu);
        
        this.initialize();
    }

    /**
     * Method that enables the Homemenu and removes the HighscorePage.
     */
    public void revertHighscorepage(){
        this.add(homeMenu,BorderLayout.CENTER);
        this.remove(highscorepage);
        
        this.initialize();
    }

    /**
     * Locates the center of the screen and moves the GameFrame to said position.
     */
    public void autoLocate(){
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (size.width - this.getWidth()) / 2;
        int y = (size.height - this.getHeight()) / 2;
        this.setLocation(x,y);
    }


    
    /** 
     * Unused interface.
     */
    @Override
    public void windowGainedFocus(WindowEvent windowEvent) {
        gaming = true;
    }

    
    /** 
     * Interface that detects when the window, GameFrame, has lost focus.
     * When the window has lost focus, the gameboard's onLostFocus() method will be called.
     * 
     * @param windowEvent Window information.
     */
    @Override
    public void windowLostFocus(WindowEvent windowEvent) {
        gaming = false;
    }

    /**
     * Getter method to check if the game is in focus.
     * @return Returns the boolean gaming to check if the game is in focus.
     */
    public boolean getGaming(){
        return gaming;
    }

}
