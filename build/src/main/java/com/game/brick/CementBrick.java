package com.game.brick;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;


/**
 * Child class of the super class Brick.
 * The cement brick is the only brick with a strength of 2.
 * Upon impact, a crack (line) is drawn across the face of the brick.
 * 
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


    public CementBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,CEMENT_STRENGTH);
        crack = new Crack(this,DEF_CRACK_DEPTH,DEF_STEPS);
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
