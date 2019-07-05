package com.mashibing.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TankFrame  extends Frame {

    public static final int GAME_WIDTH = 800,GAME_HEIGHT = 600;
    private Tank myTank;
    private Tank enemy;
    private Bullet bullet;

    public TankFrame(){
        this.setTitle("tank war");
        this.setLocation(400,100);
        this.setSize(800,600);

        this.addKeyListener(new TankKeyListener());

        myTank = new Tank(100,100,Dir.R,Group.GOOD);
        enemy = new Tank(200,200,Dir.D,Group.BAD);
        bullet = new Bullet(100,100,Dir.D,Group.GOOD);
    }

    @Override
    public void paint(Graphics g){
        myTank.paint(g);
        enemy.paint(g);
        bullet.paint(g);

    }

    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    private class TankKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            myTank.keyPressed(e);

        }

        @Override
        public void keyReleased(KeyEvent e) {
            myTank.keyReleased(e);
        }
    }
}
