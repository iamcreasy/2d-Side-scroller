package Game;

import Core.GFrame;
import Core.GameObject;
import Physics.PhysicsObject;
import Physics.PhysicsSpace;

import java.awt.*;

public class AsteroidField extends GameObject{
    private final GFrame game;
    private final PhysicsSpace physicsSpace;

    private Asteroid asteroid;
    public int asteroidPresent;                          // at the beginning of the game there is no Asteroid, hence initialized to Zero
    public int asteroidLimit;                         // max number of asteroids allowed to spawn, default:65 ,

    private long startSpawnTime = System.currentTimeMillis();
    private long spawnDelay;                             // asteroid spawn delay in milliseconds, default:100 ,
    private long nextSpawnTime = startSpawnTime + spawnDelay;

    // GFrame and PhysicsSpace reference is needed to dynamically update their object list
    public AsteroidField(GFrame game, PhysicsSpace physicsSpace) {
        super();

        this.game = game;
        this.physicsSpace = physicsSpace;

        setAsteroidLimit(250);
        setSpawnDelay(10);
    }

    @Override
    public void update(float tpf, Graphics2D g) {
        // Add Asteroid
        if(asteroidPresent < asteroidLimit && System.currentTimeMillis() >= nextSpawnTime){
            asteroid = new Asteroid();

            game.add(asteroid);
            physicsSpace.add(asteroid);
            asteroidPresent++;

            startSpawnTime = System.currentTimeMillis();
            nextSpawnTime = startSpawnTime + spawnDelay;
        }

        // Cycle through list to Remove Asteroid
        for(int i = 0; i<game.getGameObjectList().size(); i++)
            if(game.getGameObjectList().get(i) instanceof Asteroid) {
                if (((Asteroid) game.getGameObjectList().get(i)).alive == true) {

                    // When the asteroid has passed through without hitting anything
                    if (((Asteroid) game.getGameObjectList().get(i)).getLocY() > GFrame.dimension.getHeight()) {
                        physicsSpace.remove((PhysicsObject) game.getGameObjectList().get(i));
                        game.getGameObjectList().remove(i);
                        asteroidPresent--;
                    }
                } else {

                    // When the asteroid has been hit

                    if(((Asteroid) game.getGameObjectList().get(i)).getShape().getBounds().getHeight() < 10) {
                        physicsSpace.remove((PhysicsObject) game.getGameObjectList().get(i));
                        game.getGameObjectList().remove(i);
                        asteroidPresent--;
                    }
                }
            }
    }

    public long getSpawnDelay() {
        return spawnDelay;
    }

    public void setSpawnDelay(long spawnDelay) {
        this.spawnDelay = spawnDelay;
    }

    public int getAsteroidLimit() {
        return asteroidLimit;
    }

    public void setAsteroidLimit(int asteroidLimit) {
        this.asteroidLimit = asteroidLimit;
    }
}
