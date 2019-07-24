package com.mashibing.nettychat;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientFrame extends Frame {

    private TextArea ta = new TextArea();
    private TextField tf = new TextField();

    private Client c = null;

    public ClientFrame() {
        this.setSize(400,300);
        this.setLocation(400,20);
        this.add(ta, BorderLayout.CENTER);
        this.add(tf,BorderLayout.SOUTH);
        this.setTitle("leiu2520.com");

        tf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //send to server
                ta.setText(ta.getText() + tf.getText() + "\r\n");
                tf.setText("");
            }
        });
    }

    public void connectToServer(){
        c = new Client();
        c.connect();
    }

    public static void main(String[] args){
        ClientFrame f = new ClientFrame();
        f.setVisible(true);
        f.connectToServer();
    }
}
