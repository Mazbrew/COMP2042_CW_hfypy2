package com.game.wall;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.Point2D;
import javax.swing.JFrame;

import com.game.ball.Ball;

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

    protected abstract Shape makeBrickFace(Point pos,Dimension size);

    public  boolean setImpact(Point2D point , int dir){
        if(broken)
            return false;
        impact();
        return  broken;
    }

    public abstract Shape getBrick();



    public Color getBorderColor(){
        return  border;
    }

    public Color getInnerColor(){
        return inner;
    }


    public int findBrickImpact(Ball b, JFrame owner){
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

    public final boolean isBroken(){
        return broken;
    }

    public void repair() {
        broken = false;
        strength = fullStrength;
    }

    public void impact(){
        strength--;
        broken = (strength == 0);
    }

    public boolean getBroken(){
        return broken;
    }

}




