package Game;

import Engine.GameResource;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;

public class BoxMoveTest implements GameResource, KeyListener {
    String name;
    boolean up, down, left, right;
    int startLocX, startLocY;
    Rectangle2D rect = new Rectangle2D.Double(100, 100, 50, 50);

    public BoxMoveTest(String name, int startLocX, int startLocY) {
        this.name = name;
        this.startLocX = startLocX;
        this.startLocY = startLocY;
    }

    @Override
    public void update() {
        rect.setRect(startLocX, startLocY, 50, 50);
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.RED);
        g.fill(rect);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP){
            startLocY -= 1;
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN){
            startLocY += 1;
        }
        else if(e.getKeyCode() == KeyEvent.VK_LEFT){
            startLocX -= 1;
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            startLocX += 1;
        }

        System.out.println("x " + startLocX + " ,y " + startLocY);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
