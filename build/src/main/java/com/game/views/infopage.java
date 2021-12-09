package com.game.views;

import javax.swing.*;
import javax.swing.event.MouseInputListener;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Class that acts as the view for the InfoPage. This class displays an image of the rules of the game.
 * It also contains the BACK button that returns to the HomeMenu.
 */
public class InfoPage extends JLayeredPane{
    private JLabel background = new JLabel();
    private ImageIcon infopage = new ImageIcon(getClass().getResource("/infopage.png"));

    private JLabel backbutton = new JLabel();
    private ImageIcon backbuttonsprite = new ImageIcon(getClass().getResource("/BACK.png"));
    private ImageIcon backbuttonalt = new ImageIcon(getClass().getResource("/BACKalt.png"));

    public InfoPage(GameFrame owner){
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setLayout(null);
        this.setSize(600,450);

        background.setBounds(0,0,600,450);
        background.setIcon(infopage);

        backbutton.setBounds(this.getWidth()/2-backbuttonsprite.getIconWidth()/2,(this.getHeight()/3)*2-backbuttonsprite.getIconHeight()/2,200,60);
        backbutton.setIcon(backbuttonsprite);
        backbutton.addMouseListener(new MouseInputListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                Point p = mouseEvent.getPoint();

                if(backbutton.contains(p)){
                    owner.revertInfopage();
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
                    owner.revertInfopage();
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
    }
}
