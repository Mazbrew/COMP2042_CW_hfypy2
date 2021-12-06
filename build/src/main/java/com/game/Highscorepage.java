package com.game;

import javax.swing.*;
import javax.swing.event.MouseInputListener;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Highscorepage extends JLayeredPane{
    JLabel background = new JLabel();
    ImageIcon highscorepage = new ImageIcon(getClass().getResource("/highscorepage.png"));

    JLabel backbutton = new JLabel();
    ImageIcon backbuttonsprite = new ImageIcon(getClass().getResource("/BACK.png"));
    ImageIcon backbuttonalt = new ImageIcon(getClass().getResource("/BACKalt.png"));

    public Highscorepage(GameFrame owner,Highscore highscore){
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setLayout(null);
        this.setSize(600,450);

        background.setBounds(0,0,600,450);
        background.setIcon(highscorepage);

        backbutton.setBounds(this.getWidth()/2-backbuttonsprite.getIconWidth()/2,(this.getHeight()/3)*2-backbuttonsprite.getIconHeight()/2,200,60);
        backbutton.setIcon(backbuttonsprite);
        backbutton.addMouseListener(new MouseInputListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                Point p = mouseEvent.getPoint();

                if(backbutton.contains(p)){
                    owner.revertHighscorepage();
                }                  
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                Point p = mouseEvent.getPoint();

                if(backbutton.contains(p)){
                    backbutton.setIcon(backbuttonalt);
                } 
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                Point p = mouseEvent.getPoint();

                backbutton.setIcon(backbuttonsprite);
                if(backbutton.contains(p)){
                    owner.revertHighscorepage();
                } 
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {


            }


            @Override
            public void mouseDragged(MouseEvent mouseEvent) {
                Point p = mouseEvent.getPoint();

                if(backbutton.contains(p)){
                    backbutton.setIcon(backbuttonalt);
                }else{
                    backbutton.setIcon(backbuttonsprite);
                }
            }

            @Override
            public void mouseMoved(MouseEvent mouseEvent) {

            }
    });

        this.add(backbutton, JLayeredPane.MODAL_LAYER);
        this.add(background, JLayeredPane.DEFAULT_LAYER);
        this.validate();

        highscore.readScores();
    }
}
