package Physics;

import Core.GFrame;
import Core.GameObject;
import Game.Asteroid;
import Game.Bullet;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class PhysicsSpace extends GameObject{
    String sourceTag, checkAgainstTag;
    public CopyOnWriteArrayList<PhysicsObject> physicsObjectsList = new CopyOnWriteArrayList<>();
    ArrayList<PhysicsObject> sourceList = new ArrayList<>();
    ArrayList<PhysicsObject> checkAgainstList = new ArrayList<>();;

    // Objects are also added to physicsObjectList when they are added to gameObjectList
    public void add(PhysicsObject physicsObject){
       if(physicsObject instanceof PhysicsObject)
           physicsObjectsList.add(physicsObject);
    }

    public void add(PhysicsObject physicsObjects[]){
        if(physicsObjects == null)
            throw new NullPointerException();
        else{
            for(int i =0;i<physicsObjects.length; i++)
                add(physicsObjects[i]);
        }
    }

    public void remove(PhysicsObject physicsObject){
        physicsObjectsList.remove(physicsObject);
    }

    public void sourceCheckCollisionAgainst(String sourceTag, String checkAgainstTag) {
        this.sourceTag = sourceTag;
        this.checkAgainstTag = checkAgainstTag;
    }

    public void update(float tpf, Graphics2D g) {
        sourceList.clear();
        checkAgainstList.clear();

        // Step 1: Cycle through all added PhysicsObject and check Tags. Isolate source and destination tagged physics objects to different lists.
        for(int i = 0; i<physicsObjectsList.size(); i++) {
            // add to sourceList
            if (physicsObjectsList.get(i).getTag().equals(sourceTag) && physicsObjectsList.get(i).isAlive() == true)
                if(!sourceList.contains(physicsObjectsList.get(i)))
                    sourceList.add(physicsObjectsList.get(i));

            // add to checkAgainstList
            if(physicsObjectsList.get(i).getTag().equals(checkAgainstTag) && physicsObjectsList.get(i).isAlive() == true)
                if(!checkAgainstList.contains(physicsObjectsList.get(i)))
                    checkAgainstList.add(physicsObjectsList.get(i));
        }

        // Step 2: Run collision check between pairs generated from the source and checkAgainst List.
        for(int i = 0; i<sourceList.size();i++)
            for(int j = 0; j<checkAgainstList.size(); j++) {
                if(checkCollision(sourceList.get(i), checkAgainstList.get(j))) {

                    // If collision is positive
                    // Astoroid : color the checkAgainstList memeber Red, and set alive to True, and play the sound
                    // Bullet : set the bullet alive property to true
                    ((Asteroid) checkAgainstList.get(j)).color = Color.RED;
                    ((Asteroid) checkAgainstList.get(j)).alive = false;
//                    ((Asteroid) checkAgainstList.get(j)).onDestroySound();
                    ((Bullet) sourceList.get(i)).alive = false;
                }
            }
    }

    boolean checkCollision(PhysicsObject b1, PhysicsObject b2){
        // return false if no collision on X axis
        if(!(b1.getLocX() + b1.getShape().getBounds().getWidth() >= b2.getLocX() &&
                b1.getLocX() <= b2.getLocX()+b2.getShape().getBounds().getWidth()))
            return false;

        // return false if no collision on Y axis
        if(!(b1.getLocY() + b1.getShape().getBounds().getHeight() >= b2.getLocY() &&
                b1.getLocY() <= b2.getLocY() + b2.getShape().getBounds().getHeight()))
            return false;

        // or there must be a collision
        return true;
    }
}
