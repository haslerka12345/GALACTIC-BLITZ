package org.example.logic;

import org.example.ImageLoader;

public class Bullet extends Entity {
    double rotationAngle;
    double velocityX;
    double velocityY;
    public Bullet(double rotationAngle, int x, int y, String file){
        width = 10;
        height = 5;
        step = 20;
        this.x = x;
        this.y = y;
        this.rotationAngle = rotationAngle;
        loader = new ImageLoader(file);
    }
    public void move(){
        velocityX = Math.cos(rotationAngle) * step;
        velocityY = Math.sin(rotationAngle) * step;
        x += (int) velocityX;
        y += (int) velocityY;
    }

    public double getRotationAngle() {
        return rotationAngle;
    }
}
