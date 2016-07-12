package Core;

import java.awt.*;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.Random;

public class GameObject {
    Random rand = new Random(System.nanoTime());
    public String name;
    public float locX, locY, width, height;
    public String tag;          // Tag is find similar objects; Tag is to group objects
    public ArrayList<EventListener> eventListeners = new ArrayList<>(); // This initialization is good enough

    public GameObject(){
        this.name = "Unnamed";
        this.locX = rand.nextInt((int)GFrame.dimension.getWidth());
        this.locY = rand.nextInt((int)GFrame.dimension.getWidth());
        this.width = 100;
        this.height = 100;
        this.tag = "Untagged";
    }

    public GameObject(String name, float locX, float locY, float width, float height, String tag) {
        this.name = name;
        this.locX = locX;
        this.locY = locY;
        this.width = width;
        this.height = height;
        this.tag = tag;
    }

    public void setTag(String tag){
        this.tag = tag;
    }
    public String getTag(String tag) { return this.tag; }

    public void addEventListener(EventListener eventListener){
        eventListeners.add(
                eventListener);
    }

    public ArrayList<EventListener> getEventListeners(){
        return eventListeners;
    }

    // Override this method in the child class
    public void update(float tpf, Graphics2D g){

    }
}
