package org.example.logic;

import org.example.ImageLoader;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class Player extends Entity implements KeyListener, MouseMotionListener {
    private double rotationAngle;
    private int health, fuel, ammo, cash;
    private final double acceleration = 0.3;
    private double velocityY;
    private double velocityX;
    private boolean up, down, left, right, shoot, canMove, isMoving;
    private int mouseX, mouseY;

    public Player(int x, int y, int width, int height) {
        super();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        step = 5;
        loader = new ImageLoader("player2.png");

    }
    public void move(){
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

        x += (int) velocityX;
        y += (int) velocityY;

        if(isMoving){
            loader.setImage("player.png");
        }else{
            loader.setImage("player2.png");
        }
        rotationAngle = Math.atan2(mouseY - centerY(), mouseX - centerX());
    }



    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.rotate(rotationAngle, centerX(), centerY());
        g2d.drawImage(loader.getImage(), x, y, width, height, null);
        g2d.dispose();
    }

    public int centerX(){
        return x + width / 2;
    }
    public int centerY(){
        return y + height / 2;
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keys = e.getKeyCode();
        if(keys == KeyEvent.VK_W){
            up = true;

        }
        if(keys == KeyEvent.VK_S){
            down = true;

        }
        if(keys == KeyEvent.VK_A){
            left = true;

        }
        if(keys == KeyEvent.VK_D){
            right = true;
        }
        if(keys == KeyEvent.VK_SPACE){
            shoot = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keys = e.getKeyCode();
        if(keys == KeyEvent.VK_W){
            up = false;

        }
        if(keys == KeyEvent.VK_S){
            down = false;

        }
        if(keys == KeyEvent.VK_A){
            left = false;

        }
        if(keys == KeyEvent.VK_D){
            right = false;
        }

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
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

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }
}
