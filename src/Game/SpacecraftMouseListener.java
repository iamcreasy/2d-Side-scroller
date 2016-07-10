package Game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SpacecraftMouseListener implements MouseListener {
    Spacecraft spacecraft;

    public SpacecraftMouseListener(Spacecraft spacecraft) {
        this.spacecraft = spacecraft;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1)
                spacecraft.mouseDown = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1)
                spacecraft.mouseDown = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
