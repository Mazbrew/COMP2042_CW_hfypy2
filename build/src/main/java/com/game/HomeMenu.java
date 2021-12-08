package com.game;

import javax.swing.*;
import javax.swing.event.MouseInputListener;

import java.awt.*;
import java.awt.event.MouseEvent;


public class HomeMenu extends JLayeredPane{
    private JLabel background = new JLabel();
    private ImageIcon wall = new ImageIcon(getClass().getResource("/brickpattern.png"));

    private JLabel playbutton = new JLabel();
    private ImageIcon playbuttonsprite = new ImageIcon(getClass().getResource("/PLAY.png"));
    private ImageIcon playbuttonalt = new ImageIcon(getClass().getResource("/PLAYalt.png"));

    private JLabel infobutton = new JLabel();
    private ImageIcon infobuttonsprite = new ImageIcon(getClass().getResource("/INFO.png"));
    private ImageIcon infobuttonalt = new ImageIcon(getClass().getResource("/INFOalt.png"));

    private JLabel exitbutton = new JLabel();
    private ImageIcon exitbuttonsprite = new ImageIcon(getClass().getResource("/EXIT.png"));
    private ImageIcon exitbuttonalt = new ImageIcon(getClass().getResource("/EXITalt.png"));

    private JLabel scoresbutton = new JLabel();
    private ImageIcon scoresbuttonsprite = new ImageIcon(getClass().getResource("/HIGHSCORES.png"));
    private ImageIcon scoresbuttonalt = new ImageIcon(getClass().getResource("/HIGHSCORESalt.png"));


    public HomeMenu(GameFrame owner){
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setLayout(null);
        this.setSize(600,450);

        background.setBounds(0,0,600,450);
        background.setIcon(wall);

        
        playbutton.setBounds(this.getWidth()/4-playbuttonsprite.getIconWidth()/2,this.getHeight()/4-playbuttonsprite.getIconHeight()/2,200,60);
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
        
        infobutton.setBounds((this.getWidth()/4)*3-infobuttonsprite.getIconWidth()/2,(this.getHeight()/4)-infobuttonsprite.getIconHeight()/2,200,60);
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

        exitbutton.setBounds((this.getWidth()/4)*3-exitbuttonsprite.getIconWidth()/2,(this.getHeight()/4)*3-exitbuttonsprite.getIconHeight()/2,200,60);
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

    scoresbutton.setBounds(this.getWidth()/4-scoresbuttonsprite.getIconWidth()/2,(this.getHeight()/4)*3-scoresbuttonsprite.getIconHeight()/2,200,60);
    scoresbutton.setIcon(scoresbuttonsprite);
    scoresbutton.addMouseListener(new MouseInputListener() {
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            Point p = mouseEvent.getPoint();

            if(scoresbutton.contains(p)){
                owner.enableHighscore();
            }                  
        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {
            Point p = mouseEvent.getPoint();

            if(scoresbutton.contains(p)){
                scoresbutton.setIcon(scoresbuttonalt);
            } 
        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {
            Point p = mouseEvent.getPoint();

            scoresbutton.setIcon(scoresbuttonsprite);
            if(scoresbutton.contains(p)){
                owner.enableHighscore();
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

            if(scoresbutton.contains(p)){
                scoresbutton.setIcon(scoresbuttonalt);
            }else{
                scoresbutton.setIcon(scoresbuttonsprite);
            }
        }

        @Override
        public void mouseMoved(MouseEvent mouseEvent) {

        }
});
        

        this.add(playbutton, JLayeredPane.MODAL_LAYER);
        this.add(infobutton, JLayeredPane.MODAL_LAYER);
        this.add(exitbutton, JLayeredPane.MODAL_LAYER);
        this.add(scoresbutton, JLayeredPane.MODAL_LAYER);
        this.add(background, JLayeredPane.DEFAULT_LAYER);
        this.validate();
    }
}