package Game;

import Core.GameObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.Random;

public class WindowedCanvasTest extends GameObject {
    private Dimension d;
    private int ay1, ax2;
    private int bx1, by2;
    private int cx1, cy2;
    private int dy1, dx2;

    public WindowedCanvasTest(Dimension d) {
        this.d = d;

        ay1 = (int)d.getHeight()/2 - 1;
        ax2 = 0;

        bx1 = (int)d.getWidth()/2 -1;
        by2 = (int)d.getHeight()-1;

        cx1 = (int)d.getWidth()/2-1;
        cy2 = 0;

        dy1 = (int)d.getHeight()/2-1;
        dx2 = (int)d.getWidth()-1;
    }

    @Override
    public void update(float tpf, Graphics2D g) {
        g.setColor(Color.RED);
        g.drawLine(0, ay1, ax2, 0);
        ay1 -= 1;
        ax2 += 1;
        if(ay1 < 0) ay1 = (int)d.getHeight()/2 - 1;
        if(ax2 == (int)d.getWidth()/2-1) ax2 = 0;

        g.setColor(Color.BLUE);
        g.drawLine(bx1, (int)d.getHeight()-1, 0, by2);
        bx1 -= 1;
        by2 -= 1;
        if(bx1 < 0) bx1 = (int)d.getWidth()/2 -1;
        if(by2 == (int)d.getHeight()/2-1) by2 = (int)d.getHeight()-1;

        g.setColor(Color.CYAN);
        g.drawLine(cx1, 0, (int)d.getWidth()-1, cy2);
        cx1 += 1;
        cy2 += 1;
        if(cx1 == (int)d.getWidth()-1) cx1 = (int)d.getWidth()/2-1;
        if(cy2 == (int)d.getHeight()/2-1) cy2 = 0;

        g.setColor(Color.BLACK);
        g.drawLine((int)d.getWidth()-1, dy1, dx2, (int)d.getHeight()-1);
        dy1 += 1;
        dx2 -= 1;
        if(dy1 == (int)d.getHeight()-1) dy1 = (int)d.getHeight()/2-1;
        if(dx2 == (int)d.getWidth()/2-1) dx2 = (int)d.getWidth()-1;
    }
}
