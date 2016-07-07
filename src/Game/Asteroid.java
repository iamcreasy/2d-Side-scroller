package Game;

import Engine.GameResource;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public class Asteroid implements GameResource{
    Random rand = new Random(System.nanoTime());
    Rectangle2D shape = new Rectangle2D.Float();
    int locX, width, height;
    float locY, velocity;
    Color color;

    public Asteroid() {
        locX = rand.nextInt(550);
        locY = rand.nextInt(600) - 600;     // -600 to ensure the spawn loc beyond the canvas height
        width = rand.nextInt(100);
        height = rand.nextInt(100);
        color = new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
        velocity = rand.nextInt(200);
    }

    @Override
    public void update(float tpf, Graphics2D g) {
        g.setColor(color);

        locY = locY + velocity * tpf;
        shape.setRect(locX, locY, width, height);

        g.fill(shape);
    }
}
