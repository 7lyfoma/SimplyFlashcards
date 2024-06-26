package com.simplyflashcards;

import java.util.ArrayList;
import java.util.HashMap;

public class FlashCardSet {
    private HashMap<String, String> metaData;
    private ArrayList<FlashCard> flashCardSet;
    private Boolean isActive = true;

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

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

    public String addMetaData(String key, String value){
        return metaData.put(key, value);
    }

    public String removeMetaData(String key){
        return metaData.remove(key);
    }

    @Override
    public String toString() {
        return "FlashCardSet [metaData=" + metaData + ", flashCardSet=" + flashCardSet + "]";
    }
    /**
     * Checks if flashcardset contains necessary metadata values
     * @return Boolean
     */
    public Boolean isValid() {
        if (!metaData.containsKey("name")) return false;
        if (!metaData.containsKey("filename")) return false;
        return true;
    }

}
