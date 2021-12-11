package com.game.Models.ball;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;
import java.awt.geom.Ellipse2D;
import java.util.Random;


/**
 * The ball class contains all of the logic and the properties of the ball object.
 * 
 */
public class Ball {

    private Shape ballFace;

    private Point2D center;

    public Point2D up;
    public Point2D down;
    public Point2D left;
    public Point2D right;


    private int speedX;
    private int speedY;

    private Random rnd;
    private boolean chance;

    private static final int DEF_RADIUS = 10;
    private static final Color DEF_INNER_COLOR = new Color(255, 0, 0);
    private static final Color DEF_BORDER_COLOR = Color.black;

    public Ball(Point2D center){
        this.center = center;

        rnd = new Random();

        up = new Point2D.Double();
        down = new Point2D.Double();
        left = new Point2D.Double();
        right = new Point2D.Double();

 


        ballFace = makeBall(center,DEF_RADIUS);
        speedX = 0;
        speedY = 0;
    }

    
    /** 
     * Method that generates a circle at a specified point and with a specified radius.
     * 
     * @param center Position of the circle.
     * @param DEF_RADIUS Radius of the circle.
     * @return Returns the circle generated with the parameters specified.
     */
    protected Shape makeBall(Point2D center,int DEF_RADIUS){

        double x = center.getX() - (DEF_RADIUS / 2);
        double y = center.getY() - (DEF_RADIUS / 2);

        return new Ellipse2D.Double(x,y,DEF_RADIUS,DEF_RADIUS);
    }

    /**
     * Method that updates the position of the ball according to the X and Y speeds.
     */
    public void move(){
        RectangularShape tmp = (RectangularShape) ballFace;
        center.setLocation((center.getX() + speedX),(center.getY() + speedY));
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((center.getX() -(w / 2)),(center.getY() - (h / 2)),w,h);
        setPoints(w,h);


        ballFace = tmp;
    }

    /**
     * Method that sets the movement speed of the ball.
     */
    public void setSpeed(){
        chance= rnd.nextBoolean();

        speedX = rnd.nextInt(5 + 1 - 3) + 1;
        if(chance == true){
            reverseX();
        }
        
        speedY = -(rnd.nextInt(5 + 1 - 3) + 3);
    }

    /**
     * Method that sets a random X axis speed for the ball.
     */
    public void setRandomX(){
        chance = rnd.nextBoolean();
        speedX = rnd.nextInt(5 + 1 - 3) + 1;
        if(chance == true){
            reverseX();
        }
    }

    /**
     * Method that sets a random Y axis speed for the ball.
     */
    public void setRandomY(){
        chance = rnd.nextBoolean();
        speedY = rnd.nextInt(5 + 1 - 3) + 3;
        if(chance == true){
            reverseY();
        }
    }

    
    /** 
     * This setter method is used to by the debug panel to directly change the X axis speed of the ball.
     * 
     * @param s Specific X axis speed of the ball.
     */
    public void setXSpeed(int s){
        speedX = s;
    }

    
    /** 
     * This setter method is used to by the debug panel to directly change the Y axis speed of the ball.
     * 
     * @param s Specific Y axis speed of the ball.
     */
    public void setYSpeed(int s){
        speedY = s;
    }

    /**
     * Method that reverses the X axis speed of the ball. 
     * In other words, alternates between the left and right movement of the ball when called. 
     */
    public void reverseX(){
        speedX *= -1;
    }

    /**
     * Method that reverses the Y axis speed of the ball. 
     * In other words, alternates between the up and down movement of the ball when called. 
     */
    public void reverseY(){
        speedY *= -1;
    }

    
    /** 
     * Getter method to get the border color of the ball.
     * 
     * @return Returns the border color of the ball.
     */
    public Color getBorderColor(){
        return DEF_BORDER_COLOR;
    }

    
    /** 
     * Getter method to get the inner color of the ball.
     * 
     * @return Returns the inner color of the ball.
     */
    public Color getInnerColor(){
        return DEF_INNER_COLOR;
    }

    
    /** 
     * Getter method to get the current position of the ball.
     * 
     * @return Returns the current position of the ball.
     */
    public Point2D getPosition(){
        return center;
    }

    
    /** 
     * Getter method to get the shape of the ball.
     * 
     * @return Returns the shape of the ball, circle.
     */
    public Shape getBallFace(){
        return ballFace;
    }

    
    /** 
     * Method that moves the ball to the X and Y position parsed in by the parameter, p.
     * 
     * @param p The desired X and Y position of the ball.
     */
    public void moveTo(Point p){
        center.setLocation(p);

        RectangularShape tmp = (RectangularShape) ballFace;
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((center.getX() -(w / 2)),(center.getY() - (h / 2)),w,h);
        ballFace = tmp;
    }

    
    /** 
     * Method that sets the position of the collision points of the ball.
     * 
     * @param width Width of the ball.
     * @param height Height of the ball.
     */
    private void setPoints(double width,double height){
        up.setLocation(center.getX(),center.getY()-(height / 2));
        down.setLocation(center.getX(),center.getY()+(height / 2));

        left.setLocation(center.getX()-(width / 2),center.getY());
        right.setLocation(center.getX()+(width / 2),center.getY());
    }

    
    /** 
     * Getter method to return the X axis speed of the ball.
     * 
     * @return The X axis speed of the ball.
     */
    public int getSpeedX(){
        return speedX;
    }

    
    /** 
     * Getter method to return the Y axis speed of the ball.
     * 
     * @return The Y axis speed of the ball.
     */
    public int getSpeedY(){
        return speedY;
    }

    /**
     * Setter method to increase the speeed of the ball.
     */
    public void increaseSpeed(){
        if(speedX<0)
            speedX-=1;
        else
            speedX+=1;

        if(speedY<0)
            speedY-=1;
        else
            speedY+=1;
    }

}
