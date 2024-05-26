package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GamePanel extends JPanel implements MouseListener, Runnable {
    GameLogic logic;
    GameGraphics gameGraphics;
    int windowWidth = 800, windowHeight = 600;
    Thread thread;
    GamePanel(){
        setPreferredSize(new Dimension(windowWidth, windowHeight));
        logic = new GameLogic(windowWidth, windowHeight);
        gameGraphics = new GameGraphics(logic);


        addKeyListener(logic.player);
        addMouseListener(this);
        addMouseMotionListener(logic.player);
        addKeyListener(logic);
        setFocusable(true);
        requestFocus();

        thread = new Thread(this);
        thread.start();

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        gameGraphics.draw(g);
    }


    @Override
    public void run() {
        while (true){
            if(logic.gameActive && !logic.gamePause){
                logic.update();
            }
            repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }
    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        ImageLoader loader = new ImageLoader("cursor.png");
        Cursor customCursor = Toolkit.getDefaultToolkit().createCustomCursor(loader.getImage(), new Point(18,18), "CustomCursor");
        setCursor(customCursor);
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
