package com.game.Models.player;

import java.awt.*;

import com.game.Models.ball.Ball;

/**
 * Class that contains all the logic and the properties of the player (i.e. paddle).
 */
public class Player {


    public static final Color BORDER_COLOR = Color.lightGray.darker().darker();
    public static final Color INNER_COLOR = Color.lightGray;

    private static final int DEF_MOVE_AMOUNT = 10;

    private Rectangle playerFace;
    private Point playerPoint;
    private int moveAmount;
    private int min;
    private int max;

    public Player(Point playerPoint,int width,int height,Rectangle container) {
        this.playerPoint = playerPoint;
        moveAmount = 0;
        playerFace = makeRectangle(width, height);
        min = width/2;
        max = min + container.width - width;

    }

    
    /** 
     * Method that creates the player.
     * 
     * @param width Width of the player.
     * @param height Height of the player.
     * @return Rectangle Shape of the player.
     */
    private Rectangle makeRectangle(int width,int height){
        Point p = new Point((int)(playerPoint.getX() - (width / 2)),(int)playerPoint.getY());
        return  new Rectangle(p,new Dimension(width,height));
    }

    
    /** 
     * Method that detects the impact of the ball and the player.
     * 
     * @param b An instance of the ball class
     * @return Returns true if impact is detected else it'll return false.
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
        if(x-min < DEF_MOVE_AMOUNT){
            x = min;
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
     * Getter method that returns the shape of the player.
     * 
     * @return Returns the shape of the player, rectangle.
     */
    public Shape getPlayerFace(){
        return  playerFace;
    }

    
    /** 
     * Moves the player and it's player to a specified point.
     * 
     * @param p The desired X and Y position for the player.
     */
    public void moveTo(Point p){
        playerPoint.setLocation(p);
        playerFace.setLocation(playerPoint.x - (int)playerFace.getWidth()/2,playerPoint.y);
    }

    /**
     * Getter method for the moveAmount.
     * 
     * @return Returns the moveAmount.
     */
    public int getmoveAmount(){
        return moveAmount;
    }

    /**
     * Setter method for the moveAmount.
     * 
     */
    public void setmoveAmount(int setter){
        moveAmount= setter;
    }

    /**
     * Getter method for the max.
     * @return Returns the value for max.
     */
    public int getMax(){
        return max;
    }

    /**
     * Getter method for the min.
     * @return Returns the value for min.
     */
    public int getMin(){
        return min;
    }

    /**
     * Getter method for the PlayerPoint.
     * @return Returns the PlayerPoint
     */
    public Point getplayerPoint(){
        return playerPoint;
    }
}
