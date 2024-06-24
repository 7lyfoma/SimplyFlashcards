package com.simplyflashcards;

public class Main {
    public static void main(String[] args) {
        FlashCardSet fcs = new FlashCardSet();
        fcs.addMetaData("filename", "test.txt");
        fcs.addMetaData("name", "test");


        fcs.addFlashCard(new FlashCard("1", "2", "3", "4"));
        fcs.addFlashCard(new FlashCard("11", "21", "31", "41"));
        fcs.addFlashCard(new FlashCard("12", "22", "32", "42"));

        Boolean success = FileHandler.saveFlashCardSet(fcs);

        System.out.println(success);
    }
}