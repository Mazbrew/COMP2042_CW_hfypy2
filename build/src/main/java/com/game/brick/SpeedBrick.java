package com.game.brick;
import java.awt.*;
import java.awt.Point;

import com.game.ball.Ball;
import com.game.views.GameFrame;

/**
 * Child class of the super class Brick.
 * Upon impact, the speed of the ball will be increased.
 */
public class SpeedBrick extends Brick{

    private static final String NAME = "Speed Brick";
    private static final Color DEF_INNER = new Color(255, 000, 000).darker();
    private static final Color DEF_BORDER = new Color(255, 000, 000);
    private static final int SPEED_STRENGTH = 1;

    public SpeedBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,SPEED_STRENGTH);
        
    }

    
    /** 
     * Override of the findBrickImpact method within the super class.
     * Whenever a impact is detected, the ball's move speed will be increased.
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
            b.increaseSpeed();
            out = LEFT_IMPACT;  
        } 
        else if(brickFace.contains(b.left)){
            b.increaseSpeed();
            out = RIGHT_IMPACT;
        }
        else if(brickFace.contains(b.up)){
            b.increaseSpeed();
            out = DOWN_IMPACT;
        }
        else if(brickFace.contains(b.down)){
            b.increaseSpeed();
            out = UP_IMPACT;
        }
        return out;
    }
    


}
