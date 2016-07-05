package Game;

import Engine.GameResource;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class BoxMoveTest implements GameResource, KeyListener, MouseMotionListener {
    String name;
    Color c;
    boolean up, down, left, right;
    float locX, locY, sizeX, sizeY;

    public BoxMoveTest(String name, float locX, float locY, float sizeX, float sizeY, Color c) {
        this.name = name;
        this.c = c;
        this.locX = locX;
        this.locY = locY;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    @Override
    public void update(float tpf, Graphics2D g) {
        g.setColor(c);
        g.fillRect((int)locX, (int)locY, (int)sizeX, (int)sizeY);

        g.setColor(Color.BLACK);
        g.drawString((int)locX + "," + (int)locY, 0, 10); // Check, why I have to set it 0,10 instead of 0,0

        if(up){
            locY = locY - 100*tpf;
        }
        else if(down){
            locY = locY + 100*tpf;
        }
        else if(left){
            locX = locX - 100*tpf;
        }
        else if(right){
            locX = locX + 100*tpf;
        }
    }


    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        locX = e.getX();
        locY = e.getY();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP){
            up = true;
        }
        else if(e.getKeyCode() == KeyEvent.VK_LEFT){
            left = true;
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            right = true;
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN){
            down = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP){
            up = false;
        }
        else if(e.getKeyCode() == KeyEvent.VK_LEFT){
            left = false;
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            right = false;
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN){
            down = false;
        }
    }
}
