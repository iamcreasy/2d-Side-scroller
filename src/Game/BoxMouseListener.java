package Game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class BoxMouseListener implements MouseMotionListener {
    Box box;

    public BoxMouseListener(Box box) {
        this.box = box;
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        box.locX = e.getX();
        box.locY = e.getY();
    }
}
