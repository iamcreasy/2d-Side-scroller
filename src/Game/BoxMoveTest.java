package Game;

import Engine.GameResource;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;

public class BoxMoveTest implements GameResource, KeyListener, MouseMotionListener {
    String name;
    Color c;
    boolean up, down, left, right;
    int locX, locY, sizeX, sizeY;
    Rectangle2D rect;

    public BoxMoveTest(String name, int locX, int locY, int sizeX, int sizeY, Color c) {
        this.name = name;
        this.c = c;
        this.locX = locX;
        this.locY = locY;
        this.sizeX = sizeX;
        this.sizeY = sizeY;

        rect = new Rectangle2D.Double(this.locX, this.locY, this.sizeX, this.sizeY);
    }

    @Override
    public void update() {
        rect.setRect(locX, locY, sizeX, sizeY);
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(c);
        g.fill(rect);
        g.setColor(Color.BLACK);
        g.drawString(locX + "," + locY, 0, 10); // Check, why I have to set it 0,10 instead of 0,0
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
            locY -= 1;
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN){
            locY += 1;
        }
        else if(e.getKeyCode() == KeyEvent.VK_LEFT){
            locX -= 1;
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            locX += 1;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
