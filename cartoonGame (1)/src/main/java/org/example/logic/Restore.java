package org.example.logic;

import org.example.ImageLoader;

import java.awt.*;

public class Restore extends Entity {
    public Restore(String file, int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        loader = new ImageLoader(file);
    }
}
