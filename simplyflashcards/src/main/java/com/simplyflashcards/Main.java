package com.simplyflashcards;

public class Main {
    public static void main(String[] args) {
        FlashCardSet fcs = FileHandler.loadFlashCardSet("C:\\Users\\Max\\Documents\\CS\\NonUni\\SimplyFlashcards\\SimplyFlashcards\\simplyflashcards\\src\\test\\testfiles\\cardset_size1.txt");
        
        final String dir = System.getProperty("user.dir");
        System.out.println("current dir = " + dir);
        System.out.println(fcs);
    }
}