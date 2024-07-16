package com.simplyflashcards;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FileController fc = new FileController();
        GuiMainFrame frame = new GuiMainFrame();

        System.out.println(frame);
    }
    }