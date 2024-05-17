package org.example;

import javax.swing.*;

public class GameFrame extends javax.swing.JFrame {
    GamePanel panel;
    GameFrame(String name){
        setIconImage(new ImageIcon("src/main/resources/iconImage.png").getImage());
        setTitle(name);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel = new GamePanel();
        add(panel);
        pack();

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
