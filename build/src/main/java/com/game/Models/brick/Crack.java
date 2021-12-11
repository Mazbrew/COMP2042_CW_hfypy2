package com.game.models.brick;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.Random;

    
/**
 * Class that generates the crack across the face of certain bricks.
 */
public class Crack{
    private static final int CRACK_SECTIONS = 3;
    private static final double JUMP_PROBABILITY = 0.7;

    public static final int LEFT = 10;
    public static final int RIGHT = 20;
    public static final int UP = 30;
    public static final int DOWN = 40;
    public static final int VERTICAL = 100;
    public static final int HORIZONTAL = 200;

    private Brick brick;

    private static Random rnd;

    private GeneralPath crack;

    private int crackDepth;
    private int steps;


    public Crack(Brick brick,int crackDepth, int steps){
        this.brick = brick;
        rnd = new Random();
        crack = new GeneralPath();
        this.crackDepth = crackDepth;
        this.steps = steps;

    }


    
    /** 
     * Method that returns a GeneralPath, more specifically a line.
     * 
     * @return Returns a line.
     */
    public GeneralPath draw(){
        return crack;
    }

    
    /** 
     * Method that makes a crack based on the direction of impact and the point of impact.
     * 
     * @param point Point which the ball impacts the brick.
     * @param direction Direction of the impact.
     */
    protected void makeCrack(Point2D point, int direction){
        Rectangle bounds = brick.brickFace.getBounds();

        Point impact = new Point((int)point.getX(),(int)point.getY());
        Point start = new Point();
        Point end = new Point();


        switch(direction){
            case LEFT:
                start.setLocation(bounds.x + bounds.width, bounds.y);
                end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
                Point tmp = makeRandomPoint(start,end,VERTICAL);
                makeCrack(impact,tmp);

                break;
            case RIGHT:
                start.setLocation(bounds.getLocation());
                end.setLocation(bounds.x, bounds.y + bounds.height);
                tmp = makeRandomPoint(start,end,VERTICAL);
                makeCrack(impact,tmp);

                break;
            case UP:
                start.setLocation(bounds.x, bounds.y + bounds.height);
                end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
                tmp = makeRandomPoint(start,end,HORIZONTAL);
                makeCrack(impact,tmp);
                break;
            case DOWN:
                start.setLocation(bounds.getLocation());
                end.setLocation(bounds.x + bounds.width, bounds.y);
                tmp = makeRandomPoint(start,end,HORIZONTAL);
                makeCrack(impact,tmp);

                break;

        }
    }

    
    /** 
     * Method that makes the crack based on the start point and end point.
     * 
     * @param start Start point of the crack.
     * @param end End point of the crack.
     */
    protected void makeCrack(Point start, Point end){

        GeneralPath path = new GeneralPath();


        path.moveTo(start.x,start.y);

        double w = (end.x - start.x) / (double)steps;
        double h = (end.y - start.y) / (double)steps;

        int bound = crackDepth;
        int jump  = bound * 5;

        double x,y;

        for(int i = 1; i < steps;i++){

            x = (i * w) + start.x;
            y = (i * h) + start.y + randomInBounds(bound);

            if(inMiddle(i,CRACK_SECTIONS,steps))
                y += jumps(jump,JUMP_PROBABILITY);

            path.lineTo(x,y);

        }

        path.lineTo(end.x,end.y);
        crack.append(path,true);
    }

    
    /** 
     * Method that generates a random value with the max of the bound and min of 1.
     * 
     * @param bound Bound is defined as the crack depth.
     * @return Returns a random integer with the max of the bound and min of 1.
     */
    private int randomInBounds(int bound){
        int n = (bound * 2) + 1;
        return rnd.nextInt(n) - bound;
    }

    
    /** 
     * Method that checks if the current point of the crack is in the middle.
     * 
     * @param i The current "point" of the crack.
     * @param steps The number of crack sections.
     * @param divisions The number of steps.
     * @return Returns either true or false depending on the condition.
     */
    private boolean inMiddle(int i,int steps,int divisions){
        int low = (steps / divisions);
        int up = low * (divisions - 1);

        return  (i > low) && (i < up);
    }

    
    /** 
     * Method that makes "jumps" in the crack.
     * 
     * @param bound The crack depth multiplied by 5/
     * @param probability The probability of jumping.
     * @return Returns either a 0 or a random integer with a max value of bound.
     */
    private int jumps(int bound,double probability){

        if(rnd.nextDouble() > probability)
            return randomInBounds(bound);
        return  0;

    }

    
    /** 
     * Makes a random line based on the point of impact and the direction of impact.
     * 
     * @param from Starting point of the line.
     * @param to Ending point of the line.
     * @param direction Direction of impact.
     * @return Returns a point.
     */
    private Point makeRandomPoint(Point from,Point to, int direction){

        Point out = new Point();
        int pos;

        switch(direction){
            case HORIZONTAL:
                pos = rnd.nextInt(to.x - from.x) + from.x;
                out.setLocation(pos,to.y);
                break;
            case VERTICAL:
                pos = rnd.nextInt(to.y - from.y) + from.y;
                out.setLocation(to.x,pos);
                break;
        }
        return out;
    }

}
