package Game;

import Core.GameObject;
import Physics.PhysicsObject;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.EventListener;

public class Bullet implements GameObject, PhysicsObject {
    public String tag = "Bullet";
    public boolean alive = true;
    Rectangle2D shape = new Rectangle2D.Float();
    private float locX, locY, velocity;
    private Color color;

    public Bullet(float locX, float locY) {
        this.locX = locX;
        this.locY = locY;
        this.color = Color.RED;
        this.velocity = 500f;
    }

    // GameObject Interface
    @Override
    public void update(float tpf, Graphics2D g) {
        g.setColor(color);

        shape.setRect(locX-3, locY-40, 6, 15);  // 3 and 40 are the fix to move the firing location
        g.fill(shape);

        locY = locY - velocity * tpf;
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
    public String getTag(){
        return tag;
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
