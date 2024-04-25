package org.example.logic;

import org.example.ImageLoader;

import java.awt.*;

public class Bullet extends Entity {
    double rotationAngle;
    double velocityX;
    double velocityY;
    public Bullet(double rotationAngle, int x, int y){
        super();
        width = 10;
        height = 5;
        step = 20;
        this.x = x;
        this.y = y;
        this.rotationAngle = rotationAngle;
        loader = new ImageLoader("bullet.png");
    }
    public void move(){
        velocityX = Math.cos(rotationAngle);
        velocityY = Math.sin(rotationAngle);
        velocityX *= step;
        velocityY *= step;
        x += (int) velocityX;
        y += (int) velocityY;

    }
    public void draw(Graphics g){
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.rotate(rotationAngle, centerX(), centerY());
        g2d.drawImage(loader.getImage(), x, y, width, height, null);
        g2d.dispose();
    }
    public int centerX(){
        return x + width / 2;
    }
    public int centerY(){
        return y + width / 2;
    }

}
