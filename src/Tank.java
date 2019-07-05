import java.awt.*;
import java.awt.event.KeyEvent;

public class Tank {
    private int x,y;
    public static final int SPEED = 5;
    private Dir dir;
    private boolean bL = false,bU = false,bR = false,bD = false;



    public Tank(int x, int y,Dir dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }



    public void paint(Graphics g) {
        g.fillRect(x,y,50,50);

        move();
    }

    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        int key = e.getKeyCode();
        switch (key){
            case KeyEvent.VK_LEFT:
                bL = true;
                break;
            case KeyEvent.VK_RIGHT:
                bR = true;
                break;
            case KeyEvent.VK_UP:
                bU = true;
                break;
            case KeyEvent.VK_DOWN:
                bD = true;
                break;
        }

        setMainDir();

    }

    private void setMainDir() {
        if(!bL && !bU && !bR && !bD){
            dir = Dir.STOP;
        }
        if(bL && !bU && !bR && !bD){
            dir = Dir.L;
        }
        if(!bL && bU && !bR && !bD){
            dir = Dir.U;
        }
        if(!bL && !bU && bR && !bD){
            dir = Dir.R;
        }
        if(!bL && !bU && !bR && bD){
            dir = Dir.D;
        }
    }

    private void move() {
        switch (dir){
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
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key){
            case KeyEvent.VK_LEFT:
                bL = false;
                break;
            case KeyEvent.VK_RIGHT:
                bR = false;
                break;
            case KeyEvent.VK_UP:
                bU = false;
                break;
            case KeyEvent.VK_DOWN:
                bD = false;
                break;
        }

        setMainDir();

    }
}
