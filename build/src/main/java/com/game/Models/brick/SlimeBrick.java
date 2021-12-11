package com.game.models.brick;
import java.awt.*;
import java.awt.Point;

import com.game.models.ball.Ball;
import com.game.views.GameFrame;

/**
 * Child class of the super class Brick.
 * Upon impact, based on the direction of impact, either the X or Y axis speed of the ball will be randomized.
 * 
 */
public class SlimeBrick extends Brick{

    private static final String NAME = "Slime Brick";
    private static final Color DEF_INNER = new Color(0, 255, 000).darker();
    private static final Color DEF_BORDER = new Color(0, 255, 000);
    private static final int SLIME_STRENGTH = 1;

    public SlimeBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,SLIME_STRENGTH);
        
    }

    
    /** 
     * Override of the findBrickImpact method within the super class.
     * Whenever a impact is detected, the ball's move speed will be randomized depending on the point
     * of impact on the brick.
     * 
     * @param b Instance of the ball class.
     * @param owner Instance of the Gameframe.
     * @return Returns point of impact.
     */
    @Override
    public int findBrickImpact(Ball b,GameFrame owner){
        if(super.getBroken())
            return 0;
        int out  = 0;
        if(brickFace.contains(b.right)){
            b.setRandomY();
            out = LEFT_IMPACT;  
        } 
        else if(brickFace.contains(b.left)){
            b.setRandomY();
            out = RIGHT_IMPACT;
        }
        else if(brickFace.contains(b.up)){
            b.setRandomX();
            out = DOWN_IMPACT;
        }
        else if(brickFace.contains(b.down)){
            b.setRandomX();
            out = UP_IMPACT;
        }
        return out;
    }
}
