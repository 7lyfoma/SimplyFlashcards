package com.simplyflashcards;

import java.util.ArrayList;
import java.util.HashMap;

public class FlashCardSet {
    HashMap<String, String> metaData;

    ArrayList<FlashCard> flashCardSet;

    public FlashCardSet() {
        metaData = new HashMap<String, String>();
        flashCardSet = new ArrayList<FlashCard>();
    }

    public HashMap<String, String> getMetaData() {
        return metaData;
    }

    public ArrayList<FlashCard> getFlashCardSet() {
        return flashCardSet;
    }

    public Boolean addFlashCard(FlashCard fc) {
        return flashCardSet.add(fc);
    }

    public Boolean removeFlashcard(FlashCard fc) {
        return flashCardSet.remove(fc);

    }

}
