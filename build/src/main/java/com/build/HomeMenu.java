package com.build;

import javax.swing.*;
import javax.swing.event.MouseInputListener;

import java.awt.*;
import java.awt.event.MouseEvent;


public class HomeMenu extends JLayeredPane{
    JLabel background = new JLabel();
    ImageIcon wall = new ImageIcon("build/src/sprites/brickpattern.png");

    JLabel playbutton = new JLabel();
    ImageIcon playbuttonsprite = new ImageIcon("build/src/sprites/PLAY.png");
    ImageIcon playbuttonalt = new ImageIcon("build/src/sprites/PLAYalt.png");

    JLabel infobutton = new JLabel();
    ImageIcon infobuttonsprite = new ImageIcon("build/src/sprites/INFO.png");
    ImageIcon infobuttonalt = new ImageIcon("build/src/sprites/INFOalt.png");

    JLabel exitbutton = new JLabel();
    ImageIcon exitbuttonsprite = new ImageIcon("build/src/sprites/EXIT.png");
    ImageIcon exitbuttonalt = new ImageIcon("build/src/sprites/EXITalt.png");


    public HomeMenu(GameFrame owner){
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setLayout(null);
        this.setSize(600,450);

        background.setBounds(0,0,600,450);
        background.setIcon(wall);

        
        playbutton.setBounds(this.getWidth()/2-playbuttonsprite.getIconWidth()/2,this.getHeight()/3-playbuttonsprite.getIconHeight()/2,200,60);
        playbutton.setIcon(playbuttonsprite);
        playbutton.addMouseListener(new MouseInputListener() {
                @Override
                public void mouseClicked(MouseEvent mouseEvent) {
                    Point p = mouseEvent.getPoint();

                    if(playbutton.contains(p)){
                        owner.enableGameBoard();  
                    }                  
                }

                @Override
                public void mousePressed(MouseEvent mouseEvent) {
                    Point p = mouseEvent.getPoint();

                    if(playbutton.contains(p)){
                        playbutton.setIcon(playbuttonalt);
                    } 
                }

                @Override
                public void mouseReleased(MouseEvent mouseEvent) {
                    Point p = mouseEvent.getPoint();

                    playbutton.setIcon(playbuttonsprite);
                    if(playbutton.contains(p)){
                        owner.enableGameBoard();  
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

                    if(playbutton.contains(p)){
                        playbutton.setIcon(playbuttonalt);
                    }else{
                        playbutton.setIcon(playbuttonsprite);
                    }
                }

                @Override
                public void mouseMoved(MouseEvent mouseEvent) {

                }
        });
        
        infobutton.setBounds(this.getWidth()/2-infobuttonsprite.getIconWidth()/2,this.getHeight()/2-infobuttonsprite.getIconHeight()/2,200,60);
        infobutton.setIcon(infobuttonsprite);
        infobutton.addMouseListener(new MouseInputListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                Point p = mouseEvent.getPoint();

                if(infobutton.contains(p)){
                    owner.enableInfo();  
                }                  
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                Point p = mouseEvent.getPoint();

                if(infobutton.contains(p)){
                    infobutton.setIcon(infobuttonalt);
                } 
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                Point p = mouseEvent.getPoint();

                infobutton.setIcon(infobuttonsprite);
                if(infobutton.contains(p)){
                    owner.enableInfo();  
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

                if(infobutton.contains(p)){
                    infobutton.setIcon(infobuttonalt);
                }else{
                    infobutton.setIcon(infobuttonsprite);
                }
            }

            @Override
            public void mouseMoved(MouseEvent mouseEvent) {

            }
    });

        exitbutton.setBounds(this.getWidth()/2-exitbuttonsprite.getIconWidth()/2,(this.getHeight()/3)*2-playbuttonsprite.getIconHeight()/2,200,60);
        exitbutton.setIcon(exitbuttonsprite);
        exitbutton.addMouseListener(new MouseInputListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                Point p = mouseEvent.getPoint();

                if(exitbutton.contains(p)){
                    System.exit(0);
                }                  
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                Point p = mouseEvent.getPoint();

                if(exitbutton.contains(p)){
                    exitbutton.setIcon(exitbuttonalt);
                } 
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                Point p = mouseEvent.getPoint();

                exitbutton.setIcon(exitbuttonsprite);
                if(exitbutton.contains(p)){
                    System.exit(0);
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

                if(exitbutton.contains(p)){
                    exitbutton.setIcon(exitbuttonalt);
                }else{
                    exitbutton.setIcon(exitbuttonsprite);
                }
            }

            @Override
            public void mouseMoved(MouseEvent mouseEvent) {

            }
    });
        

        this.add(playbutton, JLayeredPane.MODAL_LAYER);
        this.add(infobutton, JLayeredPane.MODAL_LAYER);
        this.add(exitbutton, JLayeredPane.MODAL_LAYER);
        this.add(background, JLayeredPane.DEFAULT_LAYER);
        this.validate();
    }
}