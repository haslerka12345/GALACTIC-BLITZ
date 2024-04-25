package org.example;

import org.example.logic.Bullet;
import org.example.logic.Enemy;
import org.example.logic.Restore;

import javax.swing.*;
import java.awt.*;

public class GameGraphics {
    ImageLoader killsLastRound, mostKills, labelGame1, shipShieldGame, background, HelpTab, pressShift,  enemySpawnSign;
    GameLogic logic;

    GameGraphics(GameLogic logic){
        this.logic = logic;

        enemySpawnSign = new ImageLoader("moreEnemies.png");
        pressShift = new ImageLoader("pressShift.png");
        labelGame1 = new ImageLoader("titleScreen.png");
        background = new ImageLoader("bg.jpg");
        HelpTab = new ImageLoader("HELPTAB.png");
        shipShieldGame = new ImageLoader("shipShield.png");
        killsLastRound = new ImageLoader("killsLastRound.png");
        mostKills = new ImageLoader("MaxKills.png");
    }


    public void draw(Graphics g){
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.setColor(Color.white);

        g.drawImage(background.getImage(), 0,0, logic.getWindowWidth(), logic.getWindowHeight(), null);
        if(!logic.isGameActive()){
            g.drawImage(labelGame1.getImage(), 0,0, logic.getWindowWidth(), logic.getWindowHeight(), null);
            g.drawString(String.valueOf(logic.getNowKills()), 60,550);
            g.drawImage(killsLastRound.getImage(), 10,525,40,40,null);
            g.drawString(String.valueOf(logic.getMaxKills()), 60,500);
            g.drawImage(mostKills.getImage(),5,460, 50,50,null);
            if(logic.isHelpTabActive()){
                g.drawImage(HelpTab.getImage(), 0,0,logic.getWindowWidth(), logic.getWindowHeight(), null);
            }
        }
        if (logic.isGameActive()){
            drawEntity(g);
            g.setColor(Color.white);

            g.drawRect(780, 490, 10, 100);
            g.fillRect(780, 590 - logic.getPlayer().getFuel(), 10, logic.getPlayer().getFuel());

            g.drawRect(10, 490, 10, 100);
            g.fillRect(10, 590 - logic.getPlayer().getAmmo(), 10, logic.getPlayer().getAmmo());

            g.drawRect(10, 10, 90, 10);
            g.fillRect(10, 10, logic.getPlayer().getHealth(), 10);

            g.drawString(String.valueOf(logic.getKillCount()), 400,100);
            g.drawString("Cash: " + logic.getPlayer().getCash() + "$", 650,30);
            if(logic.getPlayer().getCash() >= 30){
                g.drawImage(pressShift.getImage(),0,-20,logic.getWindowWidth(), logic.getWindowHeight(), null);
            }

        }

    }
    private void drawEntity(Graphics g){

        for(Restore ammoBunch : logic.getAmmoBunches()){
            ammoBunch.draw(g);
        }
        for(Restore fuel : logic.getFuels()){
            fuel.draw(g);
        }
        for(Restore heal : logic.getHeals()){
            heal.draw(g);
        }
        for(Bullet bullet : logic.getBullets()){
            bullet.draw(g);
        }
        logic.getPlayer().draw(g);

        if(logic.isShipShieldActive()){
            g.drawImage(shipShieldGame.getImage(), logic.getPlayer().getX() - 6, logic.getPlayer().getY() - 20, 90,90,null);
        }

        for(Enemy enemy : logic.getEnemies()){
            enemy.draw(g);
        }

    }
}
