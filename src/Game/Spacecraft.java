package Game;

import Core.GFrame;
import Core.GameObject;
import Physics.PhysicsObject;
import Physics.PhysicsSpace;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.geom.GeneralPath;
import java.io.File;
import java.util.ArrayList;
import java.util.EventListener;

public class Spacecraft extends GameObject{
    GFrame game;
    PhysicsSpace physicsSpace;
    public float arc;
    public Bullet bullet;
    public boolean mouseDown;
    public GeneralPath shape;
    long startTime, bulletInterval, nextTime;

    Spacecraft(GFrame game, PhysicsSpace physicsSpace){
        super();
        
        this.name = "SpacecraftName";
        this.locX = GFrame.mX;
        this.locY = GFrame.mY;
        this.width = 50;
        this.height = 50;
        this.tag = "Spacecraft";
        
        this.game = game;
        this.physicsSpace = physicsSpace;
        this.arc = 25;                              // spacecraft arc, default:25(half of width)
        this.mouseDown = false;
        this.startTime = System.currentTimeMillis();
        this.bulletInterval = 15;                  // interval between successive bullet, default:100,
        nextTime = startTime + bulletInterval;
    }

    @Override
    public void update(float tpf, Graphics2D g) {
        locX = GFrame.mX;
        locY = GFrame.mY;
        
        // Draw SpaceCraft
        g.setColor(Color.RED);
        shape = new GeneralPath();
        shape.moveTo(locX - this.width / 2, locY);
        shape.curveTo(locX - this.width / 2,  locY,   locX, locY + this.arc,   locX + this.width / 2, locY);
        shape.lineTo(locX , locY - this.height / 2);
        shape.lineTo(locX - this.width / 2, locY);
        g.fill(shape);

        // Add bullet
        g.setColor(Color.RED);
        if(mouseDown && System.currentTimeMillis() >= nextTime) {
            bullet = new Bullet(locX, locY);
            game.add(bullet);
            physicsSpace.add(bullet);

            startTime = System.currentTimeMillis();
            nextTime = startTime + bulletInterval;

//            fireSound();
        }

        // Cycle through list to Remove Bullet
        for(int i = 0; i<game.getGameObjectList().size(); i++)
            if(game.getGameObjectList().get(i) instanceof Bullet){
                // Check if it's alive or used
                if(((Bullet)game.getGameObjectList().get(i)).alive == true) {
                    // Check if it has passed through the screen
                    if (((Bullet) game.getGameObjectList().get(i)).getLocY() <
                            -((Bullet) game.getGameObjectList().get(i)).getShape().getBounds().getHeight()) {

                        physicsSpace.remove((PhysicsObject) game.getGameObjectList().get(i));
                        game.getGameObjectList().remove(game.getGameObjectList().get(i));
                    }
                } else {
                    // Remove the bullet when it's dead
                    physicsSpace.remove((PhysicsObject) game.getGameObjectList().get(i));
                    game.getGameObjectList().remove(game.getGameObjectList().get(i));
                }
            }
    }

    public void fireSound(){
            try {
                Clip clip = AudioSystem.getClip();
                AudioInputStream ais = AudioSystem.getAudioInputStream(new File("./Resources/bullet.wav"));
                clip.open(ais);
                clip.start();
            }catch (Exception e){
                System.out.println("Error occured during audio playback.");
            }
    }
}
