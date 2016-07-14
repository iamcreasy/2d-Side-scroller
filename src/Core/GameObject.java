package Core;

import java.awt.*;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.Random;

public class GameObject {
    Random rand = new Random(System.nanoTime());
    public String name;
    public float locX, locY, width, height;
    public String tag;                              // Tag is find similar objects; Tag is to group objects
    public ArrayList<EventListener> eventListeners;

    public GameObject(){
        setName("Unnamed");
        setLocX(rand.nextInt((int)GFrame.dimension.getWidth()));
        setLocY(rand.nextInt((int)GFrame.dimension.getWidth()));
        setWidth(100);
        setHeight(100);
        setTag("Untagged");
        eventListeners = new ArrayList<>();
    }

    public GameObject(String name, float locX, float locY, float width, float height, String tag) {
        setName(name);
        setLocX(locX);
        setLocY(locY);
        setWidth(width);
        setHeight(height);
        setTag(tag);
        eventListeners = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getLocX() {
        return locX;
    }

    public void setLocX(float locX) {
        this.locX = locX;
    }

    public float getLocY() {
        return locY;
    }

    public void setLocY(float locY) {
        this.locY = locY;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag){
        this.tag = tag;
    }

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
