package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GamePanel extends JPanel implements Runnable, MouseListener {
    int windowWidth = 800;
    int windowHeight = 600;
    GameLogic logic;
    GameGraphics gameGraphics;

    GamePanel(){
        setPreferredSize(new Dimension(windowWidth, windowHeight));
        logic = new GameLogic(windowWidth, windowHeight);
        gameGraphics = new GameGraphics(logic);

        addMouseListener(this);
        addMouseMotionListener(logic.player);
        addKeyListener(logic);
        addKeyListener(logic.player);
        setFocusable(true);
        requestFocus();

        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {

        while (true) {
            if (logic.isGameActive()) {
                logic.update();
            }
            repaint();

            try {
                Thread.sleep(1000 / 120);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        gameGraphics.draw(g);
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
