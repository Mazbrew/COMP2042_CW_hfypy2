package com.game.player;

import java.awt.*;

import com.game.ball.Ball;

/**
 * Class that contains all the logic and the properties of the player (i.e. paddle).
 */
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

    
    /** 
     * Method that creates the collision area of the player.
     * 
     * @param width Width of the collision area.
     * @param height Height of the collision area.
     * @return Rectangle Shape of the collision area.
     */
    private Rectangle makeRectangle(int width,int height){
        Point p = new Point((int)(playerPoint.getX() - (width / 2)),(int)playerPoint.getY());
        return  new Rectangle(p,new Dimension(width,height));
    }

    
    /** 
     * Method that detects the collision of the ball and the player.
     * 
     * @param b An instance of the ball class
     * @return Returns true if collision is detected else it'll return false.
     */
    public boolean impact(Ball b){
        return playerFace.contains(b.getPosition());
    }

    /**
     * Method that moves the player along the X axis.
     * Logic was added to ensure that the player can be set to any speed yet move from end to end of the window.
     */
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

    /**
     * Method that changes the move ammount to a negative define movement ammount (DEF_MOVE_AMMOUNT) hence
     * moving the player to the left.
     */
    public void moveLeft(){
        moveAmount = -DEF_MOVE_AMOUNT;
    }

    /**
     * Method that changes the move ammount to the define movement ammount (DEF_MOVE_AMMOUNT) hence 
     * moving the player to the left.
     */
    public void moveRight(){
        moveAmount = DEF_MOVE_AMOUNT;
    }

    /**
     * Method that stops the players movement by setting the move ammmount to 0.
     */
    public void stop(){
        moveAmount = 0;
    }

    
    /** 
     * Getter method that returns the shape of the collision box.
     * 
     * @return Returns the shape of the collision box, rectangle.
     */
    public Shape getPlayerFace(){
        return  playerFace;
    }

    
    /** 
     * Moves the player to a specified point.
     * 
     * @param p The desired X and Y position for the player.
     */
    public void moveTo(Point p){
        playerPoint.setLocation(p);
        playerFace.setLocation(playerPoint.x - (int)playerFace.getWidth()/2,playerPoint.y);
    }
}
