package Game;

public class AsteroidField {
    private static Asteroid[] asteroids;

    public static Asteroid[] generateField(int number){
        asteroids = new Asteroid[number];
        for(int i = 0; i<number;i++)
            asteroids[i] = new Asteroid();

        return  asteroids;
    }
}
