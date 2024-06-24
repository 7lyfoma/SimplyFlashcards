package com.simplyflashcards;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        File f = File.createTempFile("temp", ".txt");

        
        FlashCardSet fcs = new FlashCardSet();

        fcs.addMetaData("name", "temp");
        fcs.addMetaData("filename", f.getAbsolutePath());

        
        
        Boolean success = FileHandler.saveFlashCardSet(fcs);

        //ssertTrue(success);

        FlashCardSet fcsCheck = FileHandler.loadFlashCardSet(f.getAbsolutePath());


        System.out.println(fcs);
        System.out.println(fcsCheck);
        // assertEquals(fcs.getMetaData().get("name"), fcsCheck.getMetaData().get("name"));
        // assertEquals(fcs.getMetaData().get("filename"), fcsCheck.getMetaData().get("filename"));

        

        f.delete();
    }
    }