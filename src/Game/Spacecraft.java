package Game;

import Engine.GFrame;
import Engine.GameResource;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.GeneralPath;
import java.io.File;

public class Spacecraft implements GameResource, MouseMotionListener, MouseListener {
    private float mX, mY;
    private boolean firing=false;
    GeneralPath shape;
    int arc, width, height;

    GFrame game;
    Spacecraft(GFrame game){
        this.game = game;
        
        this.arc = 25;
        this.width = 50;
        this.height = 50;
    }

    long startTime = System.currentTimeMillis();
    int bulletInterval = 200; // bullet interval in miliseconds
    long nextTime = startTime + bulletInterval;

    @Override
    public void update(float tpf, Graphics2D g) {
        g.setColor(Color.RED);
        shape = new GeneralPath();
        shape.moveTo(mX-this.width/2, mY);
        shape.curveTo(mX-this.width/2, mY, mX, mY + this.arc, mX + this.width/2, mY);
        shape.lineTo(mX, mY-this.height/2);
        shape.lineTo(mX-this.width/2, mY);
        g.fill(shape);
        
        if(firing && System.currentTimeMillis() >= nextTime) {
            game.add(new Bullet(mX, mY));
            nextTime = startTime + bulletInterval;
            startTime = System.currentTimeMillis();

            // Rough 'fire' audio playback Code
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

    @Override
    public void mouseDragged(MouseEvent e) {
        this.mX = e.getX();
        this.mY = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.mX = e.getX();
        this.mY = e.getY();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
//        if(!firing) {
            firing = true;
//        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
//        if(firing) {
            firing = false;
//        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
