package com.mashibing.tank;

import java.awt.*;
import java.util.Random;

public class Tank  extends AbstractGameObject {
    public static final int SPEED = 5;
    private int x, y;
    private Dir dir;
    private boolean bL = false, bU = false, bR = false, bD = false;
    private boolean moving = true;
    private int oldX , oldY ;
    private int width,height;
    private Group group;
    private boolean live = true;
    private Random r = new Random();
    private Rectangle rect;

    public Tank(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.oldX = x;
        this.oldY = y;
        this.width = ResourceMgr.goodTankU.getWidth();
        this.height = ResourceMgr.goodTankU.getHeight();

        this.rect = new Rectangle(x, y, width, height);
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void paint(Graphics g) {

        if (!this.isLive()) {
            return;
        }

        if (this.group == Group.GOOD) {
            switch (dir) {
                case L:
                    g.drawImage(ResourceMgr.goodTankL, x, y, null);
                    break;
                case U:
                    g.drawImage(ResourceMgr.goodTankU, x, y, null);
                    break;
                case R:
                    g.drawImage(ResourceMgr.goodTankR, x, y, null);
                    break;
                case D:
                    g.drawImage(ResourceMgr.goodTankD, x, y, null);
                    break;
            }
        } else {

            switch (dir) {
                case L:
                    g.drawImage(ResourceMgr.badTankL, x, y, null);
                    break;
                case U:
                    g.drawImage(ResourceMgr.badTankU, x, y, null);
                    break;
                case R:
                    g.drawImage(ResourceMgr.badTankR, x, y, null);
                    break;
                case D:
                    g.drawImage(ResourceMgr.badTankD, x, y, null);
                    break;
            }
        }

        move();

        //update rect
        rect.x = x;
        rect.y = y;
    }

    private void move() {
        if (!moving) return;

        oldX = x;
        oldY = y;


        switch (dir) {
            case L:
                x -= SPEED;
                break;
            case U:
                y -= SPEED;
                break;
            case R:
                x += SPEED;
                break;
            case D:
                y += SPEED;
                break;
        }


        boundsCheck();

        randomDir();

        int curRandom = r.nextInt(100);
        if (curRandom < 10) {

            fire();
        }
    }

    private void boundsCheck() {
        if (x < 0 || y < 30 || x + width > TankFrame.GAME_WIDTH || y + height > TankFrame.GAME_HEIGHT) {
            this.back();
        }
    }

    public void back() {
        this.x = oldX;
        this.y = oldY;
    }

    private void randomDir() {
        if (r.nextInt(100) > 95) {
            this.dir = Dir.randomDir();
        }

    }


    private void fire() {
        int bX = x + ResourceMgr.goodTankU.getWidth() / 2 - ResourceMgr.bulletU.getWidth() / 2;
        int bY = y + ResourceMgr.goodTankU.getHeight() / 2 - ResourceMgr.bulletU.getHeight() / 2;
        TankFrame.INSTANCE.add(new Bullet(bX, bY, dir, group));
    }

    public Rectangle getRect() {
        return rect;
    }

    public void die() {
        this.setLive(false);
        TankFrame.INSTANCE.add(new Explode(x,y));
    }
}
