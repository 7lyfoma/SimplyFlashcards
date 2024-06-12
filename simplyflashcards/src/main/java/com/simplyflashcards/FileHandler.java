package com.simplyflashcards;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class FileHandler {
    public static FlashCardSet loadFlashCardSet(String filepath){
        FlashCardSet fcs = new FlashCardSet();

        System.out.println(filepath);
        File file = new File(filepath);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);

            Boolean isMeta = true;

            while(scanner.hasNextLine()){
                String data = scanner.nextLine();
                if (isMeta){
                    while (!data.equals(";")){
                        String[] metaPair = data.split(":");
                        fcs.addMetaData(metaPair[0], metaPair[1]);
                        data = scanner.nextLine();
                    }
                    isMeta = false;
                }
                else {
                    int count = 0;
                    FlashCard fc = new FlashCard();
                    while (!data.equals(";")){
                        if (count == 0) fc.setFrontText(data);
                        else if (count == 1) fc.setBackText(data);
                        else if (count == 2) fc.setFrontImagePath(data);
                        else if (count == 3) fc.setBackImagePath(data);
                        else throw new NoSuchElementException();
                        count++;
                        data = scanner.nextLine();
                    }          
                    fcs.addFlashCard(fc);     
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchElementException e) {
            System.err.println("Flashcard file improperly formatted");
        } finally {
            if(null != scanner) scanner.close();
        }
        

        return fcs;
       
    }
}
