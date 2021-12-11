package com.build;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import com.game.Models.highscore.Highscore;

import org.junit.Test;

/**
 * Test class for the highscores list.
 */
public class HighscoreTest 
{
    /**
     * Test to check that when the file is made, all the values are equals to 0.
     */
    @Test
    public void sortTest1()
    {
        int[] scores;
        int testvalue =0;
        Highscore highscore = new Highscore();
        highscore.sortScores(testvalue);
        scores= highscore.getScores();

        for(int i=0;i < 5;i++){
            assertEquals(scores[i],testvalue);
        }
    }

    /**
     * Test to check that when a test value of 100 is inputted to the sortScores method,
     * the first index should be the same value as the test value.
     */
    public void sortTest2(){
        int[] scores;
        int testvalue =100;
        Highscore highscore = new Highscore();
        highscore.sortScores(testvalue);
        scores= highscore.getScores();

        assertEquals(scores[0],testvalue);
    }

    /**
     * Test to check if the sortScores method can sort properly.
     */
    public void sortTest3(){
        int[] scores ={6000,4000,300,200,100};
        int testvalue = 5000;

        Highscore highscore = new Highscore();
        highscore.setScores(scores);
        highscore.sortScores(testvalue);
        scores= highscore.getScores();

        assertEquals(scores[1], testvalue);
        for(int i=0;i<5;i++){
            assertFalse(scores[i] != 100);
        }
    }

}
