package com.simplyflashcards;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GuiMainFrame extends JFrame {

    JLabel errorJLabel;
    
    JFileChooser fileChooser;

    GridBagConstraints c;

    public GuiMainFrame(){
       
        //Error label is always there, invisble if not in use
        errorJLabel = new JLabel("Hello World");
        errorJLabel.setForeground(Color.RED);
        errorJLabel.setFont(new Font(errorJLabel.getFont().getName(), errorJLabel.getFont().getStyle(), 18));

        fileChooser = new JFileChooser();

        c = new GridBagConstraints();

        setLayout(new GridBagLayout());
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        //Add error label
        c.fill = GridBagConstraints.VERTICAL;
        c.weightx = 1;
        c.weighty = 0.1;
        c.gridx = 0;
        c.gridy = 1;
        add(fileChooser,c);

        // Set constraints back to default so panels that replace the login panel go in the same place
        c.anchor = GridBagConstraints.PAGE_START;
        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 0;

        setVisible(true);

    }
}
