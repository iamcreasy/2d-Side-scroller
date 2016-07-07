package Engine;

import java.awt.*;
import java.math.BigInteger;
import java.util.Random;

public class GFrameUtility {
    public static Rectangle[][] shapes = new Rectangle[32][24];
    private static Random rand = new Random(System.nanoTime());

    public static void updateBenchmark() {
        for(int r = 0; r<24; r++)
            for(int c = 0; c<32; c++)
                shapes[c][r] = new Rectangle(c*20, r*20, 20, 20);
    }

    public static void renderBenchmark(Graphics2D g) {
        for(int r = 0; r<24; r++)
            for(int c = 0; c<32; c++){
                g.setColor(new Color( rand.nextInt(255),rand.nextInt(255),rand.nextInt(255) ));
                g.fill(shapes[c][r]);
            }
    }

    public static BigInteger fib(long nth){
        long count = 0;
        BigInteger first = BigInteger.ZERO;
        BigInteger second = BigInteger.ONE;

        BigInteger third = null;
        while(count < nth){
            third = new BigInteger(first.add(second).toString());
            first = new BigInteger(second.toString());
            second = new BigInteger(third.toString());
            count++;
        }

        return third;
    }

    public static void sleep(long miliSec) {
        try {
            Thread.sleep(miliSec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
