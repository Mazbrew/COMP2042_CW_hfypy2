package com.game;

import java.awt.*;


public class Player {


    public static final Color BORDER_COLOR = Color.lightGray.darker().darker();
    public static final Color INNER_COLOR = Color.lightGray;

    private static final int DEF_MOVE_AMOUNT = 8;

    private Rectangle playerFace;
    private Point playerPoint;
    private int moveAmount;
    private int min;
    private int max;

    private int width;


    public Player(Point playerPoint,int width,int height,Rectangle container) {
        this.width = width;
        this.playerPoint = playerPoint;
        moveAmount = 0;
        playerFace = makeRectangle(width, height);
        min = container.x + (width / 2);
        max = min + container.width - width;

    }

    private Rectangle makeRectangle(int width,int height){
        Point p = new Point((int)(playerPoint.getX() - (width / 2)),(int)playerPoint.getY());
        return  new Rectangle(p,new Dimension(width,height));
    }

    public boolean impact(Ball b){
        return playerFace.contains(b.getPosition()) && playerFace.contains(b.down) ;
    }

    public void move(){
        double x = playerPoint.getX() + moveAmount;
        if(x < min || x > max){
            return;
        }else if(x-min < DEF_MOVE_AMOUNT){
            x = width/2;
        }else if(max-x < DEF_MOVE_AMOUNT){
            x = max ;
        }

        playerPoint.setLocation(x,playerPoint.getY());
        playerFace.setLocation(playerPoint.x - (int)playerFace.getWidth()/2,playerPoint.y);
    }

    public void moveLeft(){
        moveAmount = -DEF_MOVE_AMOUNT;
    }

    public void moveRight(){
        moveAmount = DEF_MOVE_AMOUNT;
    }

    public void stop(){
        moveAmount = 0;
    }

    public Shape getPlayerFace(){
        return  playerFace;
    }

    public void moveTo(Point p){
        playerPoint.setLocation(p);
        playerFace.setLocation(playerPoint.x - (int)playerFace.getWidth()/2,playerPoint.y);
    }
}
