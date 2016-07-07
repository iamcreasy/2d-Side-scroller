package Game;

import Engine.GameResource;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Bullet implements GameResource{
    Rectangle2D bulletShape;
    float locX, locY, velocity;
    Color color;

    public Bullet(float locX, float locY) {
        this.locX = locX;
        this.locY = locY;
        this.color = Color.RED;
        this.velocity = 500f;
    }


    @Override
    public void update(float tpf, Graphics2D g) {
        g.setColor(color);

        bulletShape = new Rectangle2D.Float(locX-3, locY-40, 6, 15);    // 3 and 40 are the fix to move the firing location
        g.fill(bulletShape);

        locY = locY - velocity * tpf;
    }
}
