package com.game.main;

import com.game.views.GameFrame;

public class GraphicsMain {

    public static void main(String[] args){
        GameFrame gameFrame = new GameFrame();
        gameFrame.initialize();
        gameFrame.autoLocate();
    }

}
