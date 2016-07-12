package Game;

import Core.GFrame;
import Core.GameObject;
import Physics.PhysicsObject;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.Random;

public class Box extends GameObject implements PhysicsObject {
    private static Random rand = new Random(System.nanoTime()); // Static is needed to use it with super(rand.nextInt)

    public Rectangle2D shape;
    public Color color;
    public boolean alive;
    public boolean up, down, left, right;
    public float velocity;

    // Default Constructor
    public Box(){
        super("BoxName",
                rand.nextInt((int)GFrame.dimension.getWidth()),
                rand.nextInt((int)GFrame.dimension.getHeight()),
                rand.nextInt(100),
                rand.nextInt(100),
                "BoxTag");

        this.shape = new Rectangle2D.Float();
        this.color = new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
        this.alive = true;
        this.up = this.down = this.left = this.right = false;
        this.velocity = rand.nextInt(1000);
    }

    // Custom Box Constructor
    public Box(String name, float locX, float locY, float width, float height, Color color, float velocity, String tag) {
        super();

        this.name = name;
        this.locX = locX;
        this.locY = locY;
        this.width = width;
        this.height = height;
        this.tag = "Untagged";

        this.shape = new Rectangle2D.Float();
        this.color = color;
        this.alive = true;
        this.up = this.down = this.left = this.right = false;
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
