import com.mashibing.nettychat.Client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClentFrame2 extends  Frame{
    private TextArea ta = new TextArea();
    private TextField tf = new TextField();

    private Client c = null;

    public ClentFrame2() {
        this.setSize(400,300);
        this.setLocation(400,20);
        this.add(ta, BorderLayout.CENTER);
        this.add(tf,BorderLayout.SOUTH);
        this.setTitle("leiu2520.com");

        tf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                /*//send to server
                c.send(tf.getText());
                //ta.setText(ta.getText() + tf.getText() + "\r\n");
                tf.setText("");*/
            }
        });

    }



    public static void main(String[] args){
        ClentFrame2 f = new  ClentFrame2();
        f.setVisible(true);
        //f.connectToServer();
    }
}
