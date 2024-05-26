package org.example.logic;

import org.example.ImageLoader;

import java.awt.*;

public class Entity extends Coordinates{
    int step;
    public ImageLoader loader;
    int width;
    int height;
    public Rectangle getRect() {
        return new Rectangle(x,y,width,height);
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public int centerX(){
        return x + width / 2;
    }
    public int centerY(){
        return y + height / 2;
    }
}
