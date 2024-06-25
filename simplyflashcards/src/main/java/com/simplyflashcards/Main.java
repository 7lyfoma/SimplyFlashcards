package com.simplyflashcards;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // TODO: Add a load all flashcards method from a directory
        // TODO: Change filehandler to non static?

        FileHandler fh = new FileHandler();
        System.out.println(fh.getMetadatasFromDirectory("C:/Users/Max/Documents/CS/NonUni/SimplyFlashcards/SimplyFlashcards/simplyflashcards/src/test/java/testFiles"));

        MainGUIFrame frame = new MainGUIFrame();

        System.out.println(frame);
    }
    }