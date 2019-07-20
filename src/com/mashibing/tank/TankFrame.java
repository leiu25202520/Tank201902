package com.mashibing.tank;


import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;

public class TankFrame extends Frame {
    public static final int GAME_WIDTH = 800, GAME_HEIGHT = 600;
    public static final TankFrame INSTANCE = new TankFrame();
    Image offScreenImage = null;
    private GameModel gm = new GameModel();

    public TankFrame() {
        this.setTitle("tank war");

        this.setLocation(400, 100);
        this.setSize(GAME_WIDTH, GAME_HEIGHT);

        this.addKeyListener(new TankKeyListener());
    }

    public GameModel getGm() {
        return gm;
    }

    @Override
    public void paint(Graphics g) {

        gm.paint(g);

    }

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
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_S) save();
            else if(key == KeyEvent.VK_L) load();
            else gm.getMyTank().keyPressed(e);

        }

        @Override
        public void keyReleased(KeyEvent e) {
            gm.getMyTank().keyReleased(e);
        }
    }


    private void save() {
        ObjectOutputStream oos = null;
        try {

            File f = new File("/Users/liulei435/tank.dat");
            FileOutputStream fos = new FileOutputStream(f);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(gm);
            oos.flush();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(oos != null)
                    oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void load() {
        try {

            File f = new File("/Users/liulei435/tank.dat");
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            this.gm = (GameModel) (ois.readObject());

            ois.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
