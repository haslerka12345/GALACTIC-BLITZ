package org.example.logic;

import org.example.ImageLoader;

public class Restore extends Entity {
    public int timer;
    public Restore(String file, int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        loader = new ImageLoader(file);
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }
}
