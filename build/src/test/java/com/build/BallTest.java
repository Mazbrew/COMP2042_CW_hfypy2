package com.build;

import static org.junit.Assert.assertEquals;

import java.awt.*;
import java.awt.geom.Point2D;
import com.game.ball.Ball;
import org.junit.Test;

/**
 * Test class to test Ball.java
 */
public class BallTest
{
    /**
     * Test to check if the positive X speed can be converted to a negative integer.
     */
    @Test
    public void ballReverseTest1(){
        Point2D center = new Point(0,0);
        Ball ball = new Ball(center);
        ball.setXSpeed(10);
        ball.reverseX();
        assertEquals(ball.getSpeedX(),-10);
    }

    /**
     * Test to check if the negative X speed will be converted to a positive integer.
     */
    @Test
    public void ballReverseTest2(){
        Point2D center = new Point(0,0);
        Ball ball = new Ball(center);
        ball.setXSpeed(-10);
        ball.reverseX();
        assertEquals(ball.getSpeedX(),10);

    }

    /**
     * Test to check if the positive Y speed can be converted to a negative integer.
     */
    @Test
    public void ballReverseTest3(){
        Point2D center = new Point(0,0);
        Ball ball = new Ball(center);
        ball.setYSpeed(10);
        ball.reverseY();
        assertEquals(ball.getSpeedY(),-10);

    }

    /**
     * Test to check if the negative Y speed will be converted to a positive integer.
     */
    @Test
    public void ballReverseTest4(){
        Point2D center = new Point(0,0);
        Ball ball = new Ball(center);
        ball.setYSpeed(-10);
        ball.reverseY();
        assertEquals(ball.getSpeedY(),10);

    }
}
