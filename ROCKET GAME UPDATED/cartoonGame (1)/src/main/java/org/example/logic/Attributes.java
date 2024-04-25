package org.example.logic;

import java.awt.*;

public class Attributes extends Coordinates{

    public int width;
    public int height;
    Attributes(){
    }
    public Rectangle getRect() {
        return new Rectangle(x,y,width,height);
    }
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


}
