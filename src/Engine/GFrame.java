package Engine;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class GFrame {
    public String gameTitle;
    private String tempTitle;

    private long gameStartTime;
    private float tpf;

    public static Dimension dimension;
    private Dimension crtJfrmDim, crtCnvsDim;

    public boolean fullscreen = false;

    private JFrame jFrame;
    public Canvas canvas;
    BufferStrategy bufferStrategy;

    public GFrame() {
        gameStartTime = System.nanoTime();
        gameTitle = "GameTitle";
        dimension = new Dimension(600, 600);
        jFrame = new JFrame();
        canvas = new Canvas();

        if(fullscreen) {
            // correct dimension

            // Fullscreen jFrame and canvas

        } else
        {
            // correct dimension
//            crtJfrmDim = new Dimension((int) dimension.getWidth() + 6, (int) dimension.getHeight() + 35); // Insets[top=32,left=3,bottom=3,right=3]
//            crtCnvsDim = new Dimension((int) dimension.getWidth() + 6, (int) dimension.getHeight() + 35); // Insets[top=32,left=3,bottom=3,right=3]

            // Windowed jFrame and Canvas
            jFrame.setTitle(gameTitle);
            jFrame.setSize(dimension);
            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jFrame.setLocationRelativeTo(null);
//            jFrame.setLayout(null);
            jFrame.setUndecorated(false);
            jFrame.setResizable(false);
            jFrame.setVisible(true);

            canvas.setSize(dimension);
            canvas.setPreferredSize(dimension);
            canvas.setMaximumSize(dimension);
            canvas.setMinimumSize(dimension);
            jFrame.add(canvas);
            jFrame.pack();
            canvas.requestFocus();
        }

        // Setup BufferStrategy for canvas
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();

        tpf = (float)(System.nanoTime() -  gameStartTime)/1000000000;
    }

    public void start(){
        // Initialize game loop variabless
        boolean running = true;
        long intervalTime = System.nanoTime();
        long loopStartTime;
        long loopCount = 0;

        // Game loop
        while (running){
            tempTitle = "";
            loopStartTime = System.nanoTime();

            // Render logic
            Graphics2D g = (Graphics2D)bufferStrategy.getDrawGraphics();
            update(tpf, g);
            g.dispose();
            bufferStrategy.show();

            // Game loop counter Update
            loopCount++;

            // Game Title Update
            if(System.nanoTime() - intervalTime > 1000000000) {
                tempTitle = tempTitle.concat(gameTitle + " FPS : " + String.valueOf(loopCount));
                jFrame.setTitle(tempTitle);
                loopStartTime = System.nanoTime();
                loopCount = 0;
                intervalTime = System.nanoTime();
            }

            tpf = (float)(System.nanoTime() - loopStartTime) / 1000000000;
//            System.out.printf("%.10f\n", tpf);
        }

        // Cleanup
    }
	
	ArrayList<GameResource> gameResourceList = new ArrayList<>();

    public void add(GameResource gameResource){
        if(gameResource == null)
            throw new NullPointerException();
        else
            gameResourceList.add(gameResource);
    }

    private void update(float tpf, Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, (int)dimension.getWidth(), (int)dimension.getHeight());

        for(GameResource gameResource : gameResourceList)
            gameResource.update(tpf, g);
    }
}
