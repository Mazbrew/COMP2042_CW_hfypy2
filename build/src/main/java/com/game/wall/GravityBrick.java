package com.game.wall;

import java.awt.*;
import java.awt.Point;
import java.util.Random;

import javax.swing.JFrame;

import com.game.ball.Ball;

/**
 * Child class of the super class Brick.
 */
public class GravityBrick extends Brick {

    private static final String NAME = "Gravity Brick";
    private static final Color DEF_INNER =  Color.BLACK;
    private static final Color DEF_BORDER = Color.BLACK;
    private static final int CLAY_STRENGTH = 1;

    public GravityBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,CLAY_STRENGTH);
    }

    
    /** 
     * Override of the findBrickImpact method within the super class.
     * Whenever a impact is detected, the owner will be moved to a new random location on the screen.
     * Logic to prevent the gameboard from being covered was added.
     * 
     * @param b Instance of the ball class.
     * @param owner Instance of the Gameframe.
     * @return Returns point of impact.
     */
    @Override
    public int findBrickImpact(Ball b,JFrame owner){
        Random rnd = new Random();
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int screenwidth = (int) size.getWidth();
        int screenheight = (int) size.getHeight();
        if(super.getBroken())
            return 0;
        int out  = 0;
        if(brickFace.contains(b.right)){
            owner.setLocation(rnd.nextInt(screenwidth-owner.getWidth()+1),rnd.nextInt(screenheight-owner.getHeight()+1));
            out = LEFT_IMPACT;  
        } 
        else if(brickFace.contains(b.left)){
            owner.setLocation(rnd.nextInt(screenwidth-owner.getWidth()+1),rnd.nextInt(screenheight-owner.getHeight()+1));
            out = RIGHT_IMPACT;
        }
        else if(brickFace.contains(b.up)){
            owner.setLocation(rnd.nextInt(screenwidth-owner.getWidth()+1),rnd.nextInt(screenheight-owner.getHeight()+1));
            out = DOWN_IMPACT;
        }
        else if(brickFace.contains(b.down)){
            owner.setLocation(rnd.nextInt(screenwidth-owner.getWidth()+1),rnd.nextInt(screenheight-owner.getHeight()+1));
            out = UP_IMPACT;
        }
        return out;
    }

    
    /** 
     * Method that makes the hit box for the brick.
     * 
     * @param pos Position of the brick.
     * @param size Size of the brick.
     * @return Returns the shape of the brick, rectangle.
     */
    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    
    /** 
     * Getter method to return the hit box of the brick.
     * 
     * @return Returns the shape of the brick, rectangle.
     */
    @Override
    public Shape getBrick() {
        return super.brickFace;
    }


}