package com.simplyflashcards;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GuiFileChoosePanel extends JPanel{
    public GuiFileChoosePanel (ChooseDirectoryMethod ChooseDirectoryMethod){
        JLabel infoLabel = new JLabel("Please Choose Flash Card Folder");
        infoLabel.setName("FileChooseInfo");

        JButton fileChooseButton = new JButton("Open");
        fileChooseButton.setName("FileChooseOpenButton");

        JButton backButton = new JButton("Back");
        fileChooseButton.setName("FileChooseBackButton");

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setName("FileChooser");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        fileChooseButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if (e.getSource() == fileChooseButton){
                    int returnVal = fileChooser.showOpenDialog(GuiFileChoosePanel.this);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                            String filepath = fileChooser.getSelectedFile().getAbsolutePath();
                            infoLabel.setText("You have chosen: " + filepath);
                            ChooseDirectoryMethod.chooseDirectory(filepath);
                            
                        } else {
                            System.out.println("clso");
                    }
                }
                
                

                // 
            }
        });
        System.out.println(fileChooser.getComponents());




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
        add(fileChooseButton, c);

        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 2;
        add(backButton, c);

    }

    public interface ChooseDirectoryMethod {
        void chooseDirectory(String dir);
    }
    
    
}
