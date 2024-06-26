package com.simplyflashcards;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class FileHandler {

    public HashMap<String, String> loadFlashCardSetMetadata(String filepath){
        FlashCardSet fcs = new FlashCardSet(); //Use flashcard set so we can use its isvalid method

        File file = new File(filepath);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
            
            if (!scanner.hasNextLine()) throw new FileEmptyException();
            String data = scanner.nextLine();
            System.out.println(data);
            
            while (!data.equals(";")){
                // \\| needed for regex
                String[] metaPair = data.split("\\|");
                for (String i :metaPair){
                    System.out.println(i);
                }
                fcs.addMetaData(metaPair[0], metaPair[1]);

                if (!scanner.hasNextLine()) throw new NoSuchElementException();
                data = scanner.nextLine();
                System.out.println(data);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchElementException e) {
            System.err.println("Flashcard file improperly formatted");
            return null;
        } catch (FileEmptyException e) {
            System.err.println("File is empty");
            return null;
        } finally {
            if(null != scanner) scanner.close();
        }

        if (!fcs.isValid()){
            System.err.println("Flashcardset does not have neccessary metadata");
            return null;
        }
        return fcs.getMetaData();
    }


    public FlashCardSet loadFlashCardSet(String filepath){
        FlashCardSet fcs = new FlashCardSet();

        File file = new File(filepath);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);

            Boolean isMeta = true;

            if (!scanner.hasNextLine()) throw new FileEmptyException();

            while(scanner.hasNextLine()){
                String data = scanner.nextLine();
                if (isMeta){
                    while (!data.equals(";")){
                        System.out.println(data);
                        // \\| needed for regex
                        String[] metaPair = data.split("\\|");
                        for (String i :metaPair){
                            System.out.println(i);
                        }
                        fcs.addMetaData(metaPair[0], metaPair[1]);
                        data = scanner.nextLine();
                    }
                    isMeta = false;
                    System.out.println(fcs.getMetaData());
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
            return null;
        } catch (NoSuchElementException e) {
            System.err.println("Flashcard file improperly formatted");
            return null;
        } catch (FileEmptyException e) {
            System.err.println("File is empty");
            return null;
        } finally {
            if(null != scanner) scanner.close();
        }

        if (!fcs.isValid()){
            System.err.println("Flashcardset does not have neccessary metadata");
            return null;
        }
        

        return fcs;
       
    }

    public Boolean saveFlashCardSet(FlashCardSet fcs){

        if (!fcs.isValid()){
            System.err.println("fcs not valid");
            return false;
        }


        String filename = fcs.getMetaData().get("filename");

        try {
            FileWriter fw = new FileWriter(filename, false); 

            for (Iterator<String> iter = fcs.getMetaData().keySet().iterator(); iter.hasNext(); ) {
                String key = iter.next();
                fw.write(key + "|" + fcs.getMetaData().get(key) + "\n");
            }
            fw.write(";\n");
            
            for (FlashCard fc : fcs.getFlashCardSet()) {
                fw.write(fc.getFrontText() + "\n");
                fw.write(fc.getBackText() + "\n");
                fw.write(fc.getFrontImagePath() + "\n");
                fw.write(fc.getBackImagePath() + "\n");
                fw.write(";\n");
            }




            fw.close();

        }
        catch (IOException e){
            System.err.println("File could not be created");
            return false;
        }
              
       
       
        return true;
    }

    public Boolean deleteFlashCardSet(FlashCardSet fcs){

        File file = new File(fcs.getMetaData().get("filename"));

        fcs.setIsActive(false);

        return file.delete();
    }

    public ArrayList<HashMap<String, String>> getMetadatasFromDirectory(String directorypath){
        ArrayList<HashMap<String, String>> metadatas = new ArrayList<HashMap<String, String>>();

        File d = new File(directorypath);

        if (!d.isDirectory()) return null;

        File[] files = d.listFiles();

        for (File f : files){
            metadatas.add(this.loadFlashCardSetMetadata(f.getAbsolutePath()));
        }


        return metadatas;
    }
}
