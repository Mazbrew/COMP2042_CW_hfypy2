package com.game.main;

import com.game.views.GameFrame;

public class GraphicsMain {

    
    /** 
     * Main method that creates a new instance of the gameframe , initializes it and positions it at the center of the screen.
     * 
     * @param args The command line arguments.
     */
    public static void main(String[] args){
        GameFrame gameFrame = new GameFrame();
        gameFrame.initialize();
        gameFrame.autoLocate();
    }

}
