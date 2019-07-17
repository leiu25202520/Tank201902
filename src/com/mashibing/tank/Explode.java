package com.mashibing.tank;

import java.awt.*;

public class Explode  extends AbstractGameObject {
    private int x, y;
    private int width, height;
    private int step = 0;


    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    private boolean live = true;

    public Explode(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = ResourceMgr.explodes[0].getWidth();
        this.height = ResourceMgr.explodes[0].getHeight();
    }


    public void paint(Graphics g) {
        if (!live) {
            return;
        }

        g.drawImage(ResourceMgr.explodes[step], x, y, null);

        step++;

        if (step >= ResourceMgr.explodes.length) {
            this.over();
        }


    }

    private void over() {
        this.live = false;
    }


}
