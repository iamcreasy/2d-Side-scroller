package Game;

import Engine.GFrame;
import Engine.GameResource;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Random;

public class Box implements GameResource, KeyListener/*, MouseMotionListener */{
    Random rand = new Random(System.nanoTime());
    String name;
    Color color;
    boolean up, down, left, right;
    float locX, locY, width, height, velocity;

    public Box(){
        this.name = "Box";
        this.color = new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
        this.locX = rand.nextInt((int)GFrame.dimension.getWidth());
        this.locY = rand.nextInt((int)GFrame.dimension.getHeight());
        this.width = rand.nextInt(100);
        this.height = rand.nextInt(100);
        this.velocity = rand.nextInt(1000);
    }

    public Box(String name, float locX, float locY, float width, float height, Color color, float velocity) {
        this.name = name;
        this.color = color;
        this.locX = locX;
        this.locY = locY;
        this.width = width;
        this.height = height;
        this.velocity = velocity;
    }

    @Override
    public void update(float tpf, Graphics2D g) {
        g.setColor(color);
        g.fillRect((int)locX, (int)locY, (int) width, (int) height);

//        g.setColor(Color.BLACK);
//        g.drawString((int)locX + "," + (int)locY, 0, 10); // Check, why I have to set it 0,10 instead of 0,0

        if(up){
            locY = locY - velocity*tpf;
        }
        if(down){
            locY = locY + velocity*tpf;
        }
        if(left){
            locX = locX - velocity*tpf;
        }
        if(right){
            locX = locX + velocity*tpf;
        }
    }

//
//    @Override
//    public void mouseDragged(MouseEvent e) {
//        locX = e.getX();
//        locY = e.getY();
//
//    }
//
//    @Override
//    public void mouseMoved(MouseEvent e) {
//        locX = e.getX();
//        locY = e.getY();
//    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP){
            up = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            left = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            right = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            down = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP){
            up = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            left = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            right = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            down = false;
        }
    }
}
