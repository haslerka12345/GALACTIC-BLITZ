package org.example.logic;

import org.example.ImageLoader;

import java.awt.*;

public class Restore extends Attributes {
    int timer, interval = 400;
    ImageLoader loader;

    public Restore(String file, int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        loader = new ImageLoader(file);

    }

    public int getTimer() {
        timer++;
        return timer;
    }

    public int getInterval() {
        return interval;
    }

    public void draw(Graphics g){
        g.drawImage(loader.getImage(), x,y,width,height,null);
    }
}
