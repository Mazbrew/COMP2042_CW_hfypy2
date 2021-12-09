package com.game.wall;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.Point2D;

import com.game.ball.Ball;
import com.game.views.GameFrame;

/**
 * Class that acts as the super class for all of the brick types.
 * Contains all of the logic of the brick.
 */
abstract public class Brick {
    public static final int UP_IMPACT = 100;    
    public static final int DOWN_IMPACT = 200;
    public static final int LEFT_IMPACT = 300;
    public static final int RIGHT_IMPACT = 400;

    public Shape brickFace;

    private Color border;
    private Color inner;

    private int fullStrength;
    private int strength;

    private boolean broken;


    public Brick(String name, Point pos,Dimension size,Color border,Color inner,int strength){
        broken = false;
        brickFace = makeBrickFace(pos,size);
        this.border = border;
        this.inner = inner;
        this.fullStrength = this.strength = strength;

    }

    
    /** 
     * Method that is defined in the brick super class but is overrided by all the brick types.
     * 
     * @param pos Position of the brick.
     * @param size Size of the brick.
     * @return Returns the shape of the brick,rectangle.
     */
    protected abstract Shape makeBrickFace(Point pos,Dimension size);

    
    /** 
     * Setter method to set whether or not the brick has been broken.
     * Is overrided by some of the brick types to get the point of impact on the brick and the part of the ball that
     * impacts with said brick.
     * 
     * @param point impact of the ball.
     * @param dir Direction of the impact.
     * @return Returns false if the brick has already been broken, else true.
     */
    public  boolean setImpact(Point2D point , int dir){
        if(broken)
            return false;
        impact();
        return  broken;
    }

    /**
     * Method that is defined in the brick super class but is overrided by all the brick types.
     * 
     * @return Returns the shape of the brick, rectangle.
     */
    public abstract Shape getBrick();



    
    /** 
     * Getter method that returns the border color of the brick to be drawn.
     * 
     * @return Returns the border color of the brick.
     */
    public Color getBorderColor(){
        return  border;
    }

    
    /** 
     * Getter method that returns the inner color of the brick to be drawn.
     * 
     * @return Returns the inner color of the brick.
     */
    public Color getInnerColor(){
        return inner;
    }


    
    /** 
     * Method that finds the when the ball has impacted the bricks.
     * It is overrided by some of the brick types.
     * 
     * @param b Instance of the ball class.
     * @param owner Instance of the GameFrame.
     * @return The point of impact on the brick.
     */
    public int findBrickImpact(Ball b, GameFrame owner){
        if(broken)
            return 0;
        int out  = 0;
        if(brickFace.contains(b.right))
            out = LEFT_IMPACT;
        else if(brickFace.contains(b.left))
            out = RIGHT_IMPACT;
        else if(brickFace.contains(b.up))
            out = DOWN_IMPACT;
        else if(brickFace.contains(b.down))
            out = UP_IMPACT;
        return out;
    }

    
    /** 
     * Method that returns a boolean of whether or not the brick has been broken.
     * 
     * @return Returns whether the brick has been broken or not.
     */
    public final boolean isBroken(){
        return broken;
    }

    /**
     * Method that repairs the brick.
     */
    public void repair() {
        broken = false;
        strength = fullStrength;
    }

    /**
     * Method that is called when the ball imapcts the brick.
     */
    public void impact(){
        strength--;
        broken = (strength == 0);
    }

    
    /** 
     * Getter method that return a boolean of whether or not the brick is broken.
     * 
     * @return Returns whether or not the brick is broken.
     */
    public boolean getBroken(){
        return broken;
    }

}





