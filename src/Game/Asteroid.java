package Game;

import Core.GameObject;
import Physics.PhysicsObject;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.util.Random;

public class Asteroid implements GameObject, PhysicsObject {
    public String tag = "Asteroid";
    public Random rand = new Random(System.nanoTime());
    public float locX, locY, width, height, velocity;
    public Color color;
    public boolean alive = true;
    Rectangle2D shape = new Rectangle2D.Float();

    public Asteroid() {
        width = rand.nextInt(100);
        height = rand.nextInt(100)+15;
        locX = rand.nextInt(580) + 2;
        locY = - height;
        velocity = rand.nextInt(150);

        color = new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
//        color = Color.BLACK;
    }

    public void onDestroy(){
//        try {
//            Clip clip = AudioSystem.getClip();
//            AudioInputStream ais = AudioSystem.getAudioInputStream(new File("./Resources/hit.wav"));
//            clip.open(ais);
//            clip.start();
//        }catch (Exception e){
//            System.out.println("Error occured during audio playback.");
//        }
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

    @Override
    public String getTag(){
        return tag;
    }

    // PhysicsObject Interface
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
}
