package com.simplyflashcards;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GuiFileChoosePanel extends JPanel{
    public GuiFileChoosePanel (){
        JLabel infoLabel = new JLabel("Please Choose Flash Card Folder");
        infoLabel.setName("FileChooseInfo");

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setName("FileChooser");


        GridBagConstraints c = new GridBagConstraints();

        setLayout(new GridBagLayout());

        c.insets = new Insets(10,0,0,10);

        c.fill = GridBagConstraints.HORIZONTAL;
        
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        add(infoLabel, c);

        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 1;
        add(fileChooser, c);

    }
    
    
}
