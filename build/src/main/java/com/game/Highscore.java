package com.game;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Highscore{
    private int scores[];
    private int totalScores = 5;
    
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

    public void readScores(){
        try {
            File highscores = new File("highscore.txt");
            Scanner fscanf = new Scanner(highscores);

            for(int i=0; i<totalScores; i++) {
              scores[i] = fscanf.nextInt();
              System.out.println(scores[i]);
            }
            fscanf.close();
            
        } 
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    

    public int updateScores(int curscore){
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
        return totalScores;
    }

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
}
