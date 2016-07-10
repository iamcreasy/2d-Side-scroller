package Core;

import java.awt.*;
import java.util.ArrayList;
import java.util.EventListener;

public interface GameObject {

    public void update(float tpf, Graphics2D g);
    default public String getTag(){ return null; }

    default public void addEventListener(EventListener eventListener){}
    default public ArrayList<EventListener> getEventListeners(){
        return null;
    }
}
