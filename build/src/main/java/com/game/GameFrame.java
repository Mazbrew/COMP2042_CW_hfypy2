package com.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;


public class GameFrame extends JFrame implements WindowFocusListener {

    private static final String DEF_TITLE = "Brick Destroyer";

    private static GameBoard gameBoard;
    private static HomeMenu homeMenu;
    private static infopage infopage;

    private boolean gaming;

    public GameFrame(){
        super();

        gaming = false;

        gameBoard = new GameBoard(this);
        
        homeMenu = new HomeMenu(this);

        infopage = new infopage(this);

        this.add(homeMenu);
        
    }

    public void initialize(){
        this.setTitle(DEF_TITLE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(615,490);
        this.autoLocate();
        this.setResizable(false);
        this.setVisible(true);
        this.addWindowFocusListener(this);

        ImageIcon icon = new ImageIcon(getClass().getResource("/brick.png"));
        this.setIconImage(icon.getImage());
    }

    public void enableGameBoard(){
        this.dispose();
        this.remove(homeMenu);
        this.add(gameBoard,BorderLayout.CENTER);
        this.initialize();
    }

    public void enableInfo(){
        this.remove(homeMenu);
        this.add(infopage,BorderLayout.CENTER);
        this.initialize();
    }

    public void enableHomeMenu(){
        this.remove(infopage);
        this.add(homeMenu,BorderLayout.CENTER);
        this.initialize();
    }

    private void autoLocate(){
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
