package Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by iamcr on 7/8/2016.
 */
public class BoxKeyListener implements KeyListener {
    Box box;

    public BoxKeyListener(Box box) {
        this.box = box;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP){
            box.up = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            box.left = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            box.right = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            box.down = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP){
            box.up = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            box.left = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            box.right = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            box.down = false;
        }
    }
}
