package com.game;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Highscore{
    public Highscore() throws IOException{
        InputStream highscore = getClass().getResourceAsStream("/highscore.txt");
        Scanner fscanf = new Scanner(highscore);

        while (fscanf.hasNextLine()) {
            String data = fscanf.nextLine();
            System.out.println(data);
        }
    }
}
