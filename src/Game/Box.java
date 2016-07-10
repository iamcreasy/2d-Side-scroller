package Game;

import Core.GFrame;
import Core.GameObject;
import Physics.PhysicsObject;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.Random;

public class Box implements GameObject, PhysicsObject {
    public String tag = "Player";
    public ArrayList<EventListener> eventListeners = new ArrayList<>();

    private Random rand = new Random(System.nanoTime());
    public String name;
    public Color color;

    public boolean up, down, left, right;
    public float locX, locY, width, height, velocity;
    public boolean alive = true;
    public Rectangle2D shape = new Rectangle2D.Float();

    // Default Constructor
    public Box(){
        this.name = "Box";
        this.color = new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
        this.locX = rand.nextInt((int)GFrame.dimension.getWidth());
        this.locY = rand.nextInt((int)GFrame.dimension.getHeight());
        this.width = rand.nextInt(100);
        this.height = rand.nextInt(100);
        this.velocity = rand.nextInt(1000);
    }

    // Custom Box Constructor
    public Box(String name, float locX, float locY, float width, float height, Color color, float velocity) {
        this.name = name;
        this.color = color;
        this.locX = locX;
        this.locY = locY;
        this.width = width;
        this.height = height;
        this.velocity = velocity;
    }


    // GameObject Interface
    @Override
    public void update(float tpf, Graphics2D g) {
        g.setColor(color);
        shape.setRect((int)locX, (int)locY, (int) width, (int) height);
        g.fill(shape);

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

    public void addEventListener(EventListener eventListener){
        eventListeners.add(eventListener);
    }

    public ArrayList<EventListener> getEventListeners(){
        return eventListeners;
    }

    // PhysicsObject Interface
    @Override
    public String getTag(){
        return tag;
    }

    @Override
    public GameObject getGameObject() {
        return this;
    }

    @Override
    public boolean isAlive() {
        return this.alive;
    }

    @Override
    public Shape getShape(){
        return shape;
    }

    @Override
    public float getLocX() {
        return locX;
    }

    @Override
    public float getLocY() {
        return locY;
    }


}
