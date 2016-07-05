package Game;

import Engine.GFrame;

import java.awt.*;

public class GameMain {
    public static void main(String[] args) {
        GFrame game = new GFrame();

        BoxMoveTest b = new BoxMoveTest("Box", 100, 100, 50, 50, Color.RED);
        game.add(b);
        game.canvas.addKeyListener(b);
//        game.add(new WindowedCanvasTest(GFrame.dimension));

        game.start();
    }
}
