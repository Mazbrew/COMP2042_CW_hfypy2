package com.build;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.awt.Point;
import java.awt.Rectangle;

import com.game.player.Player;

import org.junit.Test;

/**
 * Test class to test Player.java
 */
public class PlayerTest {
    @Test
    public void playermaxTest(){
        Player player = new Player(new Point(100,100),150,10,new Rectangle(0,0,600,450));

        player.setmoveAmount(1000000);
        player.move();
        assertEquals((int)player.getplayerPoint().getX(), player.getMax());
    }

    @Test
    public void playerminTest(){
        Player player = new Player(new Point(100,100),150,10,new Rectangle(0,0,600,450));

        player.setmoveAmount(-100000000);
        player.move();
        assertEquals((int)player.getplayerPoint().getX(), player.getMin());
    }

    @Test
    public void playerStopTest(){
        Player player = new Player(new Point(100,100),150,10,new Rectangle(0,0,600,450));
        player.stop();
        assertEquals(player.getmoveAmount(), 0);
    }
}
