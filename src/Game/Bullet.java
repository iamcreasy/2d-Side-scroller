package Game;

import Core.GameObject;
import Physics.PhysicsObject;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.EventListener;

public class Bullet extends GameObject implements PhysicsObject {
    private Color color;
    private float velocity;
    public boolean alive;
    Rectangle2D shape;

    public Bullet(float locX, float locY) {
        super();

        this.name = "BulletName";
        this.locX = locX;
        this.locY = locY;
        this.width = 6;
        this.height = 15;
        this.tag = "Bullet";

        this.color = Color.RED;
        this.velocity = 500f;
        this.alive = true;
        this.shape = new Rectangle2D.Float();
    }

    // GameObject Interface
    @Override
    public void update(float tpf, Graphics2D g) {
        g.setColor(color);

        shape.setRect(locX-3, locY-40, width, height);  // Adjustment of fire starting point
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
