package com.game.ball;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;
import java.awt.geom.Ellipse2D;
import java.util.Random;

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

        up.setLocation(center.getX(),center.getY()-(DEF_RADIUS / 2));
        down.setLocation(center.getX(),center.getY()+(DEF_RADIUS / 2));

        left.setLocation(center.getX()-(DEF_RADIUS /2),center.getY());
        right.setLocation(center.getX()+(DEF_RADIUS /2),center.getY());


        ballFace = makeBall(center,DEF_RADIUS);
        speedX = 0;
        speedY = 0;
    }

    protected Shape makeBall(Point2D center,int DEF_RADIUS){

        double x = center.getX() - (DEF_RADIUS / 2);
        double y = center.getY() - (DEF_RADIUS / 2);

        return new Ellipse2D.Double(x,y,DEF_RADIUS,DEF_RADIUS);
    }

    public void move(){
        RectangularShape tmp = (RectangularShape) ballFace;
        center.setLocation((center.getX() + speedX),(center.getY() + speedY));
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((center.getX() -(w / 2)),(center.getY() - (h / 2)),w,h);
        setPoints(w,h);


        ballFace = tmp;
    }

    public void setSpeed(){
        chance= rnd.nextBoolean();

        speedX = rnd.nextInt(5 + 1 - 2) + 2;
        if(chance == true){
            reverseX();
        }
        
        speedY = -(rnd.nextInt(5 + 1 - 2) + 2);
    }

    public void setRandomX(){
        chance = rnd.nextBoolean();
        speedX = rnd.nextInt(5 + 1 - 2) + 2;
        if(chance == true){
            reverseX();
        }
    }

    public void setRandomY(){
        chance = rnd.nextBoolean();
        speedY = rnd.nextInt(5 + 1 - 2) + 2;
        if(chance == true){
            reverseY();
        }
    }

    public void setXSpeed(int s){
        speedX = s;
    }

    public void setYSpeed(int s){
        speedY = s;
    }

    public void reverseX(){
        speedX *= -1;
    }

    public void reverseY(){
        speedY *= -1;
    }

    public Color getBorderColor(){
        return DEF_BORDER_COLOR;
    }

    public Color getInnerColor(){
        return DEF_INNER_COLOR;
    }

    public Point2D getPosition(){
        return center;
    }

    public Shape getBallFace(){
        return ballFace;
    }

    public void moveTo(Point p){
        center.setLocation(p);

        RectangularShape tmp = (RectangularShape) ballFace;
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((center.getX() -(w / 2)),(center.getY() - (h / 2)),w,h);
        ballFace = tmp;
    }

    private void setPoints(double width,double height){
        up.setLocation(center.getX(),center.getY()-(height / 2));
        down.setLocation(center.getX(),center.getY()+(height / 2));

        left.setLocation(center.getX()-(width / 2),center.getY());
        right.setLocation(center.getX()+(width / 2),center.getY());
    }

    public int getSpeedX(){
        return speedX;
    }

    public int getSpeedY(){
        return speedY;
    }


}
