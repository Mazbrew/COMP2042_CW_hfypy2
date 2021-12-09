package com.game.highscore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Class that handles the logic for the highscores.
 * 
 */
public class Highscore{
    private static int scores[];
    private int totalScores = 5;
    
    /**
     * Contructor that generates the highscores.txt file if it isn't found within the directory. 
     * Else the game will just run like normal.
     */
    public Highscore(){
        scores = new int[totalScores];

        try {
            File highscores = new File("highscore.txt");
            if (highscores.createNewFile()) {
                try{
                    FileWriter fprintf = new FileWriter(highscores);
                    for(int i = 0;i<totalScores;i++){
                        fprintf.write(String.format("0\n"));
                    }
                    fprintf.close();
                    System.out.println("File made succesfully.");
                } catch (IOException e){
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
            } else {
              System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }  
    }

    
    /** 
     * Method that reads the highscore.txt file for the highscores.
     * 
     * @return Returns an array of integers for the highscores where the index of 0 is the highest and it descends from there.
     */
    public int[] readScores(){
        try {
            File highscores = new File("highscore.txt");
            Scanner fscanf = new Scanner(highscores);

            for(int i=0; i<totalScores; i++) {
              scores[i] = fscanf.nextInt();
            }
            fscanf.close();
            
            
        } 
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return scores;
    }

    
    /** 
     * Method that updates the array of highscores
     * 
     * @param curscore The users's current highscore.
     */
    public void updateScores(int curscore){
        sortScores(curscore);
        try {
            File highscores = new File("highscore.txt");
            FileWriter fprintf= new FileWriter(highscores);

            for(int i= 0; i<totalScores;i++){
                fprintf.write(String.format("%d\n",scores[i]));
            }
            
            fprintf.close(); 
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    
    /** 
     * Method that sorts the elemments of the highscore array.
     * 
     * @param curscore - The user's current score.
     */
    public void sortScores(int curscore){
        int temp;

        for(int i= 0; i<totalScores;i++){
            if(curscore >= scores[i]){
                temp = scores[i];
                scores[i] = curscore;
                curscore =temp;
            }
        }
    }

    /**
     * Getter method to get the scores array.
     * @return Returns the scores array.
     */
    public int[] getScores(){
        return scores;
    }

    /**
     * Setter method to set the scores aray.
     * @param scoreslist An array of scores.
     */
    public void setScores(int[] scoreslist){
        scores= scoreslist;
    }
}
