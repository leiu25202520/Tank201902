package com.mashibing.tank;

public class Main {
    public static void main(String[] args){


        TankFrame.INSTANCE.setVisible(true);

        for(;;){
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            TankFrame.INSTANCE.repaint();
        }
    }


}
