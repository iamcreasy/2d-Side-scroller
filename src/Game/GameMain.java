package Game;

import Engine.GFrame;

import java.awt.*;

public class GameMain {
    public static void main(String[] args) {
        GFrame game = new GFrame();

//        Box b = new Box("Box", 100, 100, 100, 100, Color.RED, 100);
//        game.add(b);
//        game.canvas.addKeyListener(b);
//        game.canvas.addMouseMotionListener(b);
//        game.add(new WindowedCanvasTest(GFrame.dimension));

        Box[] boxes = new Box[500];
        for(int i = 0 ; i<boxes.length; i++)
            boxes[i] = new Box();

        game.add(boxes);

//        game.add(new BulletSource(game));

//        game.add(AsteroidField.generateField(10));

//        game.add(new Asteroid());

        game.start();
    }
}
