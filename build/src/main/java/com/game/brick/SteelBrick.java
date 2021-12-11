package com.game.brick;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * Child class of the super class Brick.
 */
public class SteelBrick extends Brick {

    private static final String NAME = "Steel Brick";
    private static final Color DEF_INNER = new Color(203, 203, 201);
    private static final Color DEF_BORDER = Color.BLACK;
    private static final int STEEL_STRENGTH = 1;
    private static final double STEEL_PROBABILITY = 0.4;

    private Random rnd;
    private Shape brickFace;

    public SteelBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,STEEL_STRENGTH);
        rnd = new Random();
        brickFace = super.brickFace;
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
        return brickFace;
    }

    /**
     * Override for the setImpact method in the super class. 
     * Runs it's own impact instead of ther superclass's impact
     */
    @Override
    public  boolean setImpact(Point2D point , int dir){
        if(super.isBroken())
            return false;
        this.impact();
        return  super.isBroken();
    }

    /**
     * Method that calls the super classes's impact by chance.
     */
    public void impact(){
        if(rnd.nextDouble() < STEEL_PROBABILITY){
            super.impact();
        }
    }

}
