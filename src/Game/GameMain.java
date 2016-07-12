package Game;

import Core.GFrame;
import Core.GFrameUtility;
import Physics.PhysicsSpace;

import java.awt.*;

public class GameMain {
    public static void main(String[] args) {
        GFrame game = new GFrame();
        GFrame.cursorVisible = false;
        GFrame.userfps = 60;
        // set settings before calling init method
        game.init();

//        game.add(new WindowedCanvasTest(GFrame.dimension));
//
//        Box[] boxes = new Box[1000];
//        for(int i = 0 ; i<boxes.length; i++) {
//            boxes[i] = new Box();
//            boxes[i].addEventListener(new BoxKeyListener(boxes[i]));
//            boxes[i].addEventListener(new BoxMouseListener(boxes[i]));
//        }
//        game.add(boxes);

        PhysicsSpace physicsSpace = new PhysicsSpace();
        physicsSpace.sourceCheckCollisionAgainst("Bullet", "Asteroid");

        AsteroidField asteroidField = new AsteroidField(game, physicsSpace);
        game.add(asteroidField);

        Spacecraft spacecraft = new Spacecraft(game, physicsSpace);
        spacecraft.addEventListener(new SpacecraftMouseListener(spacecraft));
        game.add(spacecraft);

        game.add(physicsSpace);

        game.start();
    }
}
