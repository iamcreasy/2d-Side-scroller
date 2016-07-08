package Game;

import Core.GameResource;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public class Asteroid implements GameResource{
    private Random rand = new Random(System.nanoTime());
    private int locX, width, height;
    private float locY, velocity;
    private Color color;
    Rectangle2D shape = new Rectangle2D.Float();

    public Asteroid() {
        locX = rand.nextInt(550);
        locY = rand.nextInt(600) - 600;     // -600 to ensure the spawn loc beyond the canvas height
        width = rand.nextInt(100);
        height = rand.nextInt(100);
        velocity = rand.nextInt(200);

        color = new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
    }

    @Override
    public void update(float tpf, Graphics2D g) {
        g.setColor(color);

        locY = locY + velocity * tpf;
        shape.setRect(locX, locY, width, height);

        g.fill(shape);
    }
}
