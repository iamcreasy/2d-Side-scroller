package Physics;

import Core.GameObject;

import java.awt.*;

public interface PhysicsObject {
    public GameObject getGameObject();
    public boolean isAlive();

    public String getTag();
    public Shape getShape();

    public float getLocX();
    public float getLocY();
}
