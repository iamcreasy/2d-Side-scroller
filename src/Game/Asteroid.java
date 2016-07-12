package Game;

import Core.GFrame;
import Core.GameObject;
import Physics.PhysicsObject;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.util.Random;

public class Asteroid extends GameObject implements PhysicsObject {
    public Color color;
    public float velocity;
    public boolean alive;
    Rectangle2D shape;

    public Random rand = new Random(System.nanoTime());

    public Asteroid() {
        super();

        this.name = "AsteroidName";
        this.locX = rand.nextInt((int) (GFrame.dimension.getWidth() - 20)) + 2;
        this.height = rand.nextInt(100)+15;
        this.width = rand.nextInt(100);
        this.locY =  - height;
        this.tag = "Asteroid";

        this.color = new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
        this.velocity = rand.nextInt(150);
        this.alive = true;
        this.shape  = new Rectangle2D.Float();
    }

    // GameObject Interface
    @Override
    public void update(float tpf, Graphics2D g) {
        g.setColor(color);

        if(alive==true) {
            locY = locY + velocity * tpf;
            shape.setRect(locX, locY, width, height);
        } else {
            locY = locY + velocity * tpf;
            height = height - (650 * tpf);
            shape.setRect(locX, locY, width, height);
        }

        g.fill(shape);
    }

    // PhysicsObject Interface
    @Override
    public String getTag(){
        return this.tag;
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
    public Shape getShape() {
        return this.shape;
    }

    @Override
    public float getLocX() {
        return locX;
    }

    @Override
    public float getLocY() {
        return locY;
    }


    public void onDestroySound(){
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File("./Resources/hit.wav"));
            clip.open(ais);
            clip.start();
        }catch (Exception e){
            System.out.println("Error occured during audio playback.");
        }
    }
}
