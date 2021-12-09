package com.game.wall;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;


/**
 * Child class of the super class Brick.
 */
public class CementBrick extends Brick {
    public static final int MIN_CRACK = 1;
    public static final int DEF_CRACK_DEPTH = 1;
    public static final int DEF_STEPS = 35;


    private static final String NAME = "Cement Brick";
    private static final Color DEF_INNER = new Color(147, 147, 147);
    private static final Color DEF_BORDER = new Color(217, 199, 175);
    private static final int CEMENT_STRENGTH = 2;

    private Crack crack;
    private Shape brickFace;


    public CementBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,CEMENT_STRENGTH);
        crack = new Crack(this,DEF_CRACK_DEPTH,DEF_STEPS);
        brickFace = super.brickFace;
    }

    
    /** 
     * Makes the hti box for the brick.
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
     * Overrides the setImpact method to use the ball's point of impact and as well as 
     * the direction of the impact to draw the crack.
     * 
     * @param point hit box of the ball.
     * @param dir Direction of the impact.
     * @return Returns false if the brick has already been broken, else true.
     */
    @Override
    public boolean setImpact(Point2D point, int dir) {
        if(super.isBroken())
            return false;
        super.impact();
        if(!super.isBroken()){
            crack.makeCrack(point,dir);
            updateBrick();
            return false;
        }
        return true;
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
     * Method that updates the brick with a crack if it has impacted with a ball.
     * 
     */
    private void updateBrick(){
        if(!super.isBroken()){
            GeneralPath gp = crack.draw();
            gp.append(super.brickFace,false);
            brickFace = gp;
        }
    }

    /**
     * Method that repairs the brick.
     */
    public void repair(){
        super.repair();
        crack.reset();
        brickFace = super.brickFace;
    }
}
