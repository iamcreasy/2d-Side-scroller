package Game;

import Core.GameResource;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Bullet implements GameResource{
    Rectangle2D shape = new Rectangle2D.Float();
    private float locX, locY, velocity;
    private Color color;

    public Bullet(float locX, float locY) {
        this.locX = locX;
        this.locY = locY;
        this.color = Color.RED;
        this.velocity = 500f;
    }


    @Override
    public void update(float tpf, Graphics2D g) {
        g.setColor(color);

        shape.setRect(locX-3, locY-40, 6, 15);  // 3 and 40 are the fix to move the firing location
        g.fill(shape);

        locY = locY - velocity * tpf;
    }
}
