package Game;

import Engine.GameResource;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class RedBox implements GameResource {
    String name;
    int startLocX, startLocY;
    Rectangle2D rect = new Rectangle2D.Double(100, 100, 50, 100);

    public RedBox(String name, int startLocX, int startLocY) {
        this.name = name;
        this.startLocX = startLocX;
        this.startLocY = startLocY;
    }

    @Override
    public void update() {
        startLocX = startLocX + 1;
        rect.setRect(startLocX, startLocY, 50, 100);
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.RED);
        g.fill(rect);
    }
}
