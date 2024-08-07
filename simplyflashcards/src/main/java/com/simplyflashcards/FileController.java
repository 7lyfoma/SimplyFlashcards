package com.simplyflashcards;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class FileController {
    private String directoryPath = "";
    private FileHandler fh;
    private ArrayList<HashMap<String, String>> metadatas;

    private final String storePath = "simply_flashcards_directory_store.txt";

    public FileController() {
        fh = new FileHandler();    
        File store = new File(storePath);

        Scanner scanner = null;
        try {
            if (store.exists()){
                scanner = new Scanner(store);
                if (scanner.hasNextLine()){
                    directoryPath = scanner.nextLine();
                }
                

                System.out.println(directoryPath);
            }
            else{
                store.createNewFile();

                System.out.println(store.getAbsolutePath());
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Problem loading directory storage file");
            System.exit(-1);
        } finally {
            if (scanner != null){
                scanner.close();
            }
            
        }
        if (!directoryPath.equals("")){
            metadatas = fh.getMetadatasFromDirectory(directoryPath);
        }
    }

    public String getDirectoryPath() {
        return directoryPath;
    }

    public void setDirectoryPath(String directoryPath) {
        this.directoryPath = directoryPath;
        if (!directoryPath.equals("")){
            metadatas = fh.getMetadatasFromDirectory(directoryPath);
        }
        System.out.println(directoryPath);
        System.out.println(metadatas.size());
    }

    public ArrayList<HashMap<String, String>> getMetadatas() {
        return metadatas;
    }

    
        

}
