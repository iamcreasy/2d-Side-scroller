package Game;

import Engine.GFrame;

public class GameMain {
    public static void main(String[] args) {
        GFrame game = new GFrame();

        game.add(new RedBox("The RedBox", 100, 100));

        game.start();
    }
}
