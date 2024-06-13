package com.simplyflashcards;

public class FileEmptyException extends Exception {
    public FileEmptyException() {}

    public FileEmptyException(String msg){
        super(msg);
    }
}