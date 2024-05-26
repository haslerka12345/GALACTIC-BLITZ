package org.example;

import org.example.logic.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class GameLogic implements KeyListener {
    ArrayList<Restore> fuels, fuelsToRemove, ammoBunches, ammoBunchesToRemove, heals, healsToRemove;
    ArrayList<Bullet> bullets, bulletsToRemove;
    ArrayList<Enemy> enemies, enemiesToRemove;
    Player player;
    Random random;
    int windowWidth, windowHeight;
    boolean gameActive, gamePause;
    int randomRestoreNumber;
    int enemySpawnInterval, enemySpawnTimer, ammoTimer, ammoInterval = 10, fuelDrainTimer, fuelDrainInterval = 100;
    int killCount, maxKills, nowKills;
    boolean helpTabActive, shipShieldActive;

    GameLogic(int windowWidth, int windowHeight){
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        initialize();
    }

    private void initialize(){
        player = new Player(windowWidth/2,windowHeight/2, 80, 50, "player2.png", "player.png");
        random = new Random();
        bullets = new ArrayList<>();
        bulletsToRemove = new ArrayList<>();
        enemies = new ArrayList<>();
        enemiesToRemove = new ArrayList<>();
        fuels = new ArrayList<>();
        fuelsToRemove = new ArrayList<>();
        ammoBunches = new ArrayList<>();
        ammoBunchesToRemove = new ArrayList<>();
        heals = new ArrayList<>();
        healsToRemove = new ArrayList<>();
    }


    public void update(){
        collision();
        updatePlayer();
        updateBullet();
        updateEnemy();
        updateFuel();
        remove();
    }
    private void collision(){
            for (Enemy enemy : enemies) {
                if (player.getRect().intersects(enemy.getRect())) {
                    handlePlayerEnemyCollision(enemy);
                }
                for(Bullet bullet : bullets){
                    if(bullet.getRect().intersects(enemy.getRect())){
                        handleEnemyBulletCollision(enemy, bullet);
                }
            }
            for (Restore fuelTank : fuels) {
                if(fuelTank.getRect().intersects(player.getRect())){
                    handleCollectFuel(fuelTank);
                }
            }
            for (Restore ammoBunch : ammoBunches) {
                if (ammoBunch.getRect().intersects(player.getRect())) {
                    handleCollectAmmo(ammoBunch);
                }
            }
            for (Restore heal : heals) {
                if (heal.getRect().intersects(player.getRect())) {
                    handleCollectHeal(heal);
                }
            }
        }
    }
    private void addRandomRestore(int x, int y){
        randomRestoreNumber = random.nextInt(0,20);
        if(randomRestoreNumber != 3 && randomRestoreNumber != 5 && randomRestoreNumber != 19){
            ammoBunches.add(new Restore("ammoBunch.png", x,y, 20,30));
        }
        if(randomRestoreNumber == 3 || randomRestoreNumber == 5){
            fuels.add(new Restore("fuel.png", x, y, 20,30));
        }
        if(randomRestoreNumber == 19){
             heals.add(new Restore("TOOLKIT.png", x,y,20,20));
        }
    }
    private void handlePlayerEnemyCollision(Enemy enemy){
        player.setCash(player.getCash() + 8);
        killCount += 1;
        addRandomRestore(enemy.centerX(), enemy.centerY());
        enemiesToRemove.add(enemy);
        if(shipShieldActive){
            shipShieldActive = false;
        }else{
            player.setHealth(player.getHealth() - 30);
        }
        if(player.getHealth() <= 0){
            gameActive = false;
            nowKills = killCount;
            if(killCount > maxKills){
                maxKills = killCount;
            }
        }
    }
    private void handleEnemyBulletCollision(Enemy enemy, Bullet bullet){
        player.setCash(player.getCash()+4);
        killCount += 1;
        enemiesToRemove.add(enemy);
        bulletsToRemove.add(bullet);

        addRandomRestore(enemy.centerX(), enemy.centerY());
    }
    private void handleCollectFuel(Restore fuelTank){
        if(player.getFuel() < 100){
            fuelsToRemove.add(fuelTank);
            int fuelToAdd = Math.min(10, 100 - player.getFuel());
            player.setFuel(player.getFuel() + fuelToAdd);
        }
    }
    private void handleCollectAmmo(Restore ammoBunch){
        if(player.getBulletStack() < 100) {
            ammoBunchesToRemove.add(ammoBunch);
            int ammoToAdd = Math.min(5, 100 - player.getBulletStack());
            player.setBulletStack(player.getBulletStack() + ammoToAdd);
        }
    }
    private void handleCollectHeal(Restore heal){
        if(player.getHealth() < 90){
            player.setHealth(player.getHealth() + 30);
            healsToRemove.add(heal);
        }
    }
    private void updateEnemy() {
        if(killCount >= 250){
            enemySpawnInterval = 20;
        }else if (killCount >= 190) {
            enemySpawnInterval = 30;
        } else if (killCount >= 160) {
            enemySpawnInterval = 60;
        } else if (killCount >= 130) {
            enemySpawnInterval = 80;
        } else if (killCount >= 100) {
            enemySpawnInterval = 90;
        } else if (killCount >= 70) {
            enemySpawnInterval = 120;
        } else if (killCount >= 30) {
            enemySpawnInterval = 150;
        } else {
            enemySpawnInterval = 200;
        }
        enemySpawnTimer++;
        if(enemySpawnTimer >= enemySpawnInterval){
            enemies.add(new Enemy(windowWidth, windowHeight, "Enemy2.png"));
            enemySpawnTimer = 0;
        }
        for(Enemy enemy : enemies){
            enemy.move(player.getX(), player.getY());
        }
    }
    private void updateFuel(){
        if(player.isMoving()){
            fuelDrainTimer++;
            if(fuelDrainTimer >= fuelDrainInterval){
                player.setFuel(player.getFuel() - 1);
                fuelDrainTimer = 0;
            }
        }
        if(player.getFuel() <= 0){
            player.setCanMove(false);
        }else{
            player.setCanMove(true);
        }
    }

    private void updatePlayer() {
        player.move(windowWidth, windowHeight);
    }
    private void updateBullet(){
        ammoTimer++;
        if(player.isShoot() && ammoInterval <= ammoTimer){
            if(player.getBulletStack() > 0){
                bullets.add(new Bullet(player.getRotationAngle(), player.centerX() , player.centerY(), "bullet.png"));
                player.setBulletStack(player.getBulletStack() - 3);
            }
            player.setShoot(false);
            ammoTimer = 0;
        }else{
            player.setShoot(false);
        }
        for(Bullet bullet : bullets){
            bullet.move();
        }
    }
    private void remove(){
        heals.removeAll(healsToRemove);
        ammoBunches.removeAll(ammoBunchesToRemove);
        fuels.removeAll(fuelsToRemove);
        bullets.removeAll(bulletsToRemove);
        enemies.removeAll(enemiesToRemove);

        for (Restore heal : heals){
            heal.setTimer(heal.getTimer() + 1);
            if(heal.getTimer() >= 300){
                healsToRemove.add(heal);
            }
        }
        for (Restore ammoBunch : ammoBunches){
            ammoBunch.setTimer(ammoBunch.getTimer() + 1);
            if(ammoBunch.getTimer() >= 300){
                ammoBunchesToRemove.add(ammoBunch);
            }
        }
        for (Restore fuel : fuels){
            fuel.setTimer(fuel.getTimer() + 1);
            if(fuel.getTimer() >= 300){
                fuelsToRemove.add(fuel);
            }
        }
    }
    public void reset(){
        player.setY((windowWidth/2));
        player.setX((windowHeight/2));
        bullets.clear();
        enemies.clear();
        ammoBunches.clear();
        fuels.clear();
        heals.clear();
        player.setFuel(100);
        player.setCash(0);
        killCount = 0;
        player.setBulletStack(100);
        player.setHealth(90);
        shipShieldActive = false;
        gamePause = false;
    }
    public ArrayList<Restore> getFuels() {
        return fuels;
    }
    public ArrayList<Restore> getAmmoBunches() {
        return ammoBunches;
    }

    public ArrayList<Restore> getHeals() {
        return heals;
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public Player getPlayer() {
        return player;
    }

    public int getWindowWidth() {
        return windowWidth;
    }

    public int getWindowHeight() {
        return windowHeight;
    }

    public boolean isGameActive() {
        return gameActive;
    }

    public int getKillCount() {
        return killCount;
    }

    public int getMaxKills() {
        return maxKills;
    }

    public int getNowKills() {
        return nowKills;
    }

    public boolean isHelpTabActive() {
        return helpTabActive;
    }

    public boolean isShipShieldActive() {
        return shipShieldActive;
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keys = e.getKeyCode();
        if(!gameActive){
            if(keys == KeyEvent.VK_SPACE){
                gameActive = true;
                reset();
            }
            if(keys == KeyEvent.VK_CONTROL && !helpTabActive){
                helpTabActive = true;
            }else if(keys == KeyEvent.VK_CONTROL){
                helpTabActive = false;
            }
        }
        if(gameActive){
            if(keys == KeyEvent.VK_ESCAPE){
                gameActive = false;
            }
            if(keys == KeyEvent.VK_P && !gamePause) {
                gamePause = true;
            }else if(keys == KeyEvent.VK_P){
                gamePause = false;
            }

            if(keys == KeyEvent.VK_SHIFT && player.getCash() >= 30 && !shipShieldActive){
                shipShieldActive = true;
                player.setCash(player.getCash() - 30);
            }

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}