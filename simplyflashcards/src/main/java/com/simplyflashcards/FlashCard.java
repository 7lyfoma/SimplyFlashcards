package com.simplyflashcards;

public class FlashCard {
    private String FrontText;
    private String FrontImagePath;
    private String BackText;
    private String BackImagePath;
    public FlashCard(String frontText, String frontImagePath, String backText, String backImagePath) {
        FrontText = frontText;
        FrontImagePath = frontImagePath;
        BackText = backText;
        BackImagePath = backImagePath;
    }
    public String getFrontText() {
        return FrontText;
    }
    public void setFrontText(String frontText) {
        FrontText = frontText;
    }
    public String getFrontImagePath() {
        return FrontImagePath;
    }
    public void setFrontImagePath(String frontImagePath) {
        FrontImagePath = frontImagePath;
    }
    public String getBackText() {
        return BackText;
    }
    public void setBackText(String backText) {
        BackText = backText;
    }
    public String getBackImagePath() {
        return BackImagePath;
    }
    public void setBackImagePath(String backImagePath) {
        BackImagePath = backImagePath;
    }

    
}
