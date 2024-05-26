package org.example.logic;

import org.example.ImageLoader;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class Player extends Entity implements KeyListener, MouseMotionListener{
    private double rotationAngle;
    private int health, fuel, bulletStack, cash;
    private final double acceleration = 0.3;
    private double velocityY;
    private double velocityX;
    private boolean up, down, left, right, shoot, canMove, isMoving;
    ImageLoader idleLoader, moveLoader;
    private int mouseX, mouseY;

    public Player(int x, int y, int width, int height, String playerIdleFile, String playerMoveFile) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        step = 5;
        moveLoader = new ImageLoader(playerMoveFile);
        idleLoader = new ImageLoader(playerIdleFile);
        loader = idleLoader;

    }
    public void move(int winWidth, int winHeight){
        if(canMove){
            if (up && velocityY > -step) {
                velocityY -= acceleration;
            }
            if (down && velocityY < step) {
                velocityY += acceleration;
            }
            if (left && velocityX  > -step) {
                velocityX -= acceleration;
            }
            if (right && velocityX  < step) {
                velocityX  += acceleration;
            }
            if(up || down || left || right){
                isMoving = true;
            }else{
                isMoving = false;
            }
        }
        if(x > winWidth - width){
            x = winWidth - width;
        }
        if(x < 0){
            x = 0;
        }
        if(y > winHeight - height){
            y = winHeight - height;
        }
        if(y < 0){
            y = 0;
        }

        x += (int) velocityX;
        y += (int) velocityY;

        if(isMoving){
            loader = moveLoader;
        }else{
            loader = idleLoader;
        }
        rotationAngle = Math.atan2(mouseY - centerY(), mouseX - centerX());
    }

    public void setMouseX(int mouseX) {
        this.mouseX = mouseX;
    }

    public void setMouseY(int mouseY) {
        this.mouseY = mouseY;
    }

    public double getRotationAngle() {
        return rotationAngle;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    public boolean isShoot() {
        return shoot;
    }

    public void setShoot(boolean shoot) {
        this.shoot = shoot;
    }


    public boolean isMoving() {
        return isMoving;
    }
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public int getBulletStack() {
        return bulletStack;
    }

    public void setBulletStack(int bulletStack) {
        this.bulletStack = bulletStack;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keys = e.getKeyCode();
        switch (keys){
            case KeyEvent.VK_W -> setUp(true);
            case KeyEvent.VK_S -> setDown(true);
            case KeyEvent.VK_A -> setLeft(true);
            case KeyEvent.VK_D -> setRight(true);
            case KeyEvent.VK_SPACE -> setShoot(true);

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keys = e.getKeyCode();
        switch (keys){
            case KeyEvent.VK_W -> setUp(false);
            case KeyEvent.VK_S -> setDown(false);
            case KeyEvent.VK_A -> setLeft(false);
            case KeyEvent.VK_D -> setRight(false);
            case KeyEvent.VK_SPACE -> setShoot(false);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        setMouseX(e.getX());
        setMouseY(e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        setMouseX(e.getX());
        setMouseY(e.getY());
    }
}
