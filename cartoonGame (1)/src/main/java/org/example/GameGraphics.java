package org.example;

import org.example.logic.Bullet;
import org.example.logic.Enemy;
import org.example.logic.Player;
import org.example.logic.Restore;
import java.awt.*;

public class GameGraphics{
    ImageLoader killsLastRound, mostKills, labelGame1, shipShieldGame, background, HelpTab, pressShift,  enemySpawnSign, pause;
    GameLogic logic;
    Player player;
    GameGraphics(GameLogic logic){
        this.logic = logic;
        this.player = logic.getPlayer();

        enemySpawnSign = new ImageLoader("moreEnemies.png");
        pressShift = new ImageLoader("pressShift.png");
        labelGame1 = new ImageLoader("titleScreen.png");
        background = new ImageLoader("bg.jpg");
        HelpTab = new ImageLoader("HELPTAB.png");
        shipShieldGame = new ImageLoader("shipShield.png");
        killsLastRound = new ImageLoader("killsLastRound.png");
        mostKills = new ImageLoader("MaxKills.png");
        pause = new ImageLoader("PAUSED.png");
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
            g.fillRect(10, 590 - logic.getPlayer().getBulletStack(), 10, logic.getPlayer().getBulletStack());

            g.drawRect(10, 10, 90, 10);
            g.fillRect(10, 10, logic.getPlayer().getHealth(), 10);

            g.drawString(String.valueOf(logic.getKillCount()), 400,100);
            g.drawString("Cash: " + logic.getPlayer().getCash() + "$", 650,30);
            if(logic.getPlayer().getCash() >= 30){
                g.drawImage(pressShift.getImage(),0,-20,logic.getWindowWidth(), logic.getWindowHeight(), null);
            }
            if(logic.gamePause){
                g.drawImage(pause.getImage(), 0,0,logic.getWindowWidth(), logic.getWindowHeight(), null);
            }

        }

    }
    private void drawEntity(Graphics g){
        for(Restore ammoBunch : logic.getAmmoBunches()){
            g.drawImage(ammoBunch.loader.getImage(), ammoBunch.getX(),ammoBunch.getY(),ammoBunch.getWidth(),ammoBunch.getHeight(),null);
        }
        for(Restore fuel : logic.getFuels()){
            g.drawImage(fuel.loader.getImage(), fuel.getX(),fuel.getY(),fuel.getWidth(),fuel.getHeight(),null);
        }
        for(Restore heal : logic.getHeals()){
            g.drawImage(heal.loader.getImage(), heal.getX(),heal.getY(),heal.getWidth(),heal.getHeight(),null);
        }
        drawBullets(g);
        drawPlayer(g);

        if(logic.isShipShieldActive()){
            g.drawImage(shipShieldGame.getImage(), logic.getPlayer().getX() - 6, logic.getPlayer().getY() - 20, 90,90,null);
        }

        for(Enemy enemy : logic.getEnemies()){
            g.drawImage(enemy.loader.getImage(), enemy.getX(),enemy.getY(),enemy.getWidth(), enemy.getHeight(),null);
        }

    }
    public void drawPlayer(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.rotate(player.getRotationAngle(), player.centerX(), player.centerY());
        g2d.drawImage(player.loader.getImage(), player.getX(), player.getY(), player.getWidth(), player.getHeight(), null);
        g2d.dispose();
    }
    public void drawBullets(Graphics g){
        for (Bullet bullet : logic.getBullets()){
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.rotate(bullet.getRotationAngle(), bullet.centerX(), bullet.centerY());
            g2d.drawImage(bullet.loader.getImage(), bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight(), null);
            g2d.dispose();
        }
    }
}
