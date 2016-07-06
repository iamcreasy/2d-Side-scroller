package Engine;

import java.awt.*;
import java.util.EventListener;

public interface GameResource extends EventListener {
    public void update(float tpf, Graphics2D g);
}
