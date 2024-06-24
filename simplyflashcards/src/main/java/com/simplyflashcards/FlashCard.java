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
    
    public FlashCard() {
        FrontText = "";
        FrontImagePath = "";
        BackText = "";
        BackImagePath = "";
        
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

    @Override
    public String toString() {
        return "FlashCard [FrontText=" + FrontText + ", FrontImagePath=" + FrontImagePath + ", BackText=" + BackText
                + ", BackImagePath=" + BackImagePath + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FlashCard other = (FlashCard) obj;
        if (FrontText == null) {
            if (other.FrontText != null)
                return false;
        } else if (!FrontText.equals(other.FrontText))
            return false;
        if (FrontImagePath == null) {
            if (other.FrontImagePath != null)
                return false;
        } else if (!FrontImagePath.equals(other.FrontImagePath))
            return false;
        if (BackText == null) {
            if (other.BackText != null)
                return false;
        } else if (!BackText.equals(other.BackText))
            return false;
        if (BackImagePath == null) {
            if (other.BackImagePath != null)
                return false;
        } else if (!BackImagePath.equals(other.BackImagePath))
            return false;
        return true;
    }

    

    

    
}
