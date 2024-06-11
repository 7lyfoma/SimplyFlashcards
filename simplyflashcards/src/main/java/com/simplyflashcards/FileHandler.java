package com.simplyflashcards;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileHandler {
    public static FlashCardSet loadFlashCardSet(String filepath){
        FlashCardSet fcs = new FlashCardSet();


        File file = new File("filepath");
        Scanner scanner;
        try {
            scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                String data = scanner.nextLine();
                //Todo here
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return fcs;
       
    }
}
