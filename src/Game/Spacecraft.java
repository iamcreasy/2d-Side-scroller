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

public class Spacecraft implements GameObject{
    public boolean mouseDown =false;
    public GeneralPath shape;
    public int arc, width, height;
    public ArrayList<EventListener> eventListeners = new ArrayList<>();
    public Bullet bullet;

    GFrame game;
    PhysicsSpace physicsSpace;
    Spacecraft(GFrame game, PhysicsSpace physicsSpace){
        this.game = game;
        this.physicsSpace = physicsSpace;
        
        this.width = 50;
        this.height = 50;
        this.arc = 25;
    }

    long startTime = System.currentTimeMillis();
    long bulletInterval = 0;   // bullet interval in nanosecs
    long nextTime = startTime;

    @Override
    public void update(float tpf, Graphics2D g) {
        // Draw SpaceCraft
        g.setColor(Color.RED);
        shape = new GeneralPath();
        shape.moveTo(GFrame.mX - this.width / 2, GFrame.mY);
        shape.curveTo(GFrame.mX - this.width / 2, GFrame.mY, GFrame.mX, GFrame.mY + this.arc, GFrame.mX + this.width / 2, GFrame.mY);
        shape.lineTo(GFrame.mX , GFrame.mY - this.height / 2);
        shape.lineTo(GFrame.mX - this.width / 2, GFrame.mY);
        g.fill(shape);

        // Add bullet
        g.setColor(Color.RED);
        if(mouseDown && System.currentTimeMillis() >= nextTime) {
            bullet = new Bullet(GFrame.mX, GFrame.mY);
            game.add(bullet);
            physicsSpace.add(bullet);

            startTime = System.currentTimeMillis();
            nextTime = startTime + bulletInterval;

            // Rough 'fire' audio playback Code
//            try {
//                Clip clip = AudioSystem.getClip();
//                AudioInputStream ais = AudioSystem.getAudioInputStream(new File("./Resources/bullet.wav"));
//                clip.open(ais);
//                clip.start();
//            }catch (Exception e){
//                System.out.println("Error occured during audio playback.");
//            }
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

    @Override
    public void addEventListener(EventListener eventListener){
        eventListeners.add(eventListener);
    }

    @Override
    public ArrayList<EventListener> getEventListeners() {
        return eventListeners;
    }
}
