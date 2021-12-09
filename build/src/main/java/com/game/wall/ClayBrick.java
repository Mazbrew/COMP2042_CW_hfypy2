package com.game.wall;

import java.awt.*;
import java.awt.Point;

/**
 * Child class of the super class Brick.
 */
public class ClayBrick extends Brick {

    private static final String NAME = "Clay Brick";
    private static final Color DEF_INNER = new Color(200, 105, 84).darker();
    private static final Color DEF_BORDER = Color.GRAY;
    private static final int CLAY_STRENGTH = 1;

    public ClayBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,CLAY_STRENGTH);
    }

    
    /** 
     * Makes the hit box for the brick.
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
