package com.simplyflashcards;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        File f = File.createTempFile("temp", ".txt");

        
        FlashCardSet fcs = new FlashCardSet();

        fcs.addMetaData("name", "temp");
        fcs.addMetaData("filename", f.getAbsolutePath());
        

        f.delete();
    }
    }