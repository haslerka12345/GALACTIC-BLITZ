package org.example.logic;

import org.example.ImageLoader;

import java.awt.*;
import java.util.Random;

public class Enemy extends Entity{
    Random random;
    int enemySpawnSide, enemyType;

    public Enemy(int windowWidth, int windowHeight){
        super();
        random = new Random();
        enemySpawnSide = random.nextInt(1, 5);
        enemyType = random.nextInt(1, 4);

        switch (enemySpawnSide) {
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
        loader = new ImageLoader("Enemy2.png");
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

    public void draw(Graphics g){
        g.drawImage(loader.getImage(), x,y,width, height,null);
    }
    public int centerX(){
        return x + width / 2;
    }
    public int centerY(){
        return y + height / 2;
    }

}
