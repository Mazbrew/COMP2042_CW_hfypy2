package com.game.views;

import javax.swing.*;

import com.game.highscore.Highscore;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;


public class GameFrame extends JFrame implements WindowFocusListener {

    private static final String DEF_TITLE = "Brick Destroyer";

    private static GameBoard gameBoard;
    private static HomeMenu homeMenu;
    private static infopage infopage;
    private static Highscorepage highscorepage;
    private static Highscore highscore;

    private boolean gaming;

    public GameFrame(){
        super();
        highscore = new Highscore();

        gaming = false;

        
        
        homeMenu = new HomeMenu(this);
        this.add(homeMenu);
        
    }

    public void initialize(){
        this.setTitle(DEF_TITLE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(615,490);
        this.setResizable(false);
        this.setVisible(true);
        this.addWindowFocusListener(this);

        ImageIcon icon = new ImageIcon(getClass().getResource("/brick.png"));
        this.setIconImage(icon.getImage());
    }

    public void enableGameBoard(){
        gameBoard = new GameBoard(this,highscore);
        this.add(gameBoard,BorderLayout.CENTER);
        this.remove(homeMenu);
        
        this.initialize();
    }

    public void enableInfo(){
        infopage = new infopage(this);
        this.add(infopage,BorderLayout.CENTER);
        this.remove(homeMenu);
        
        this.initialize();
    }

    public void revertInfopage(){
        this.add(homeMenu,BorderLayout.CENTER);
        this.remove(infopage);
        
        this.initialize();
    }

    public void revertGameboard(){
        this.add(homeMenu,BorderLayout.CENTER);
        this.remove(gameBoard);
        
        this.initialize();

    }

    public void enableHighscore(){
        highscorepage = new Highscorepage(this,highscore);
        this.add(highscorepage,BorderLayout.CENTER);
        this.remove(homeMenu);
        
        this.initialize();
    }

    public void revertHighscorepage(){
        this.add(homeMenu,BorderLayout.CENTER);
        this.remove(highscorepage);
        
        this.initialize();
    }


    public void autoLocate(){
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (size.width - this.getWidth()) / 2;
        int y = (size.height - this.getHeight()) / 2;
        this.setLocation(x,y);
    }


    @Override
    public void windowGainedFocus(WindowEvent windowEvent) {
        gaming = true;
    }

    @Override
    public void windowLostFocus(WindowEvent windowEvent) {
        if(gaming)
            gameBoard.onLostFocus();

    }
}
