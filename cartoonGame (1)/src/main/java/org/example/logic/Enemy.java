package org.example.logic;

import org.example.ImageLoader;

import java.awt.*;
import java.util.Random;

public class Enemy extends Entity{
    Random random;

    public Enemy(int windowWidth, int windowHeight, String file){
        random = new Random();

        switch (random.nextInt(1, 5)) {
            case 1:
                x = random.nextInt(0, windowWidth + 1);
                y = -100;
                break;
            case 2:
                x = random.nextInt(0, windowWidth + 1);
                y = windowHeight + 100;
                break;
            case 3:
                x = -100;
                y = random.nextInt(0, windowHeight + 1);
                break;
            case 4:
                x = windowWidth + 100;
                y = random.nextInt(0, windowHeight + 1);
                break;
        }
        loader = new ImageLoader(file);
        step = random.nextInt(1,3);
        width = random.nextInt(40,50);
        height = width;

    }
    public void move(int playerX, int playerY){
        if (playerX > x) {
            x += step;
        } else if (playerX < x) {
            x -= step;
        }

        if (playerY > y) {
            y += step;
        } else if (playerY < y) {
            y -= step;
        }
    }
}
