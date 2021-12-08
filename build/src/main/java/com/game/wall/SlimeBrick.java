package com.game.wall;
import java.awt.*;
import java.awt.Point;
import javax.swing.JFrame;

import com.game.ball.Ball;

public class SlimeBrick extends Brick{

    private static final String NAME = "Slime Brick";
    private static final Color DEF_INNER = new Color(0, 255, 000).darker();
    private static final Color DEF_BORDER = new Color(0, 255, 000);
    private static final int SLIME_STRENGTH = 1;

    public SlimeBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,SLIME_STRENGTH);
        
    }

    @Override
    public int findBrickImpact(Ball b,JFrame owner){
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
    

    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    @Override
    public Shape getBrick() {
        return super.brickFace;
    }

}
