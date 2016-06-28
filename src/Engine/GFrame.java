package Engine;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class GFrame {
    Dimension dimension;
    public JFrame jFrame;
    public Canvas canvas;
	BufferStrategy bufferStrategy;
    String gameName;

    public GFrame() {
        gameName = "GameTitle";
        dimension = new Dimension(640, 480);
        jFrame = new JFrame();

        // setup JFrame
        jFrame.setTitle(gameName);
        jFrame.setSize(dimension);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLayout(null);
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(true);
        jFrame.setVisible(true);

        // setup Canvas
        canvas = new Canvas();
        canvas.setSize(dimension);
        canvas.setLocation(0,0);
        jFrame.add(canvas);
        canvas.requestFocus();
		
	// Setup BufferStrategy
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
    }

    public void start(){
        // Initialize game loop variabless
        boolean running = true;
        long currentTime = System.currentTimeMillis();
        long gameLoopCount = 0;

        long systemRefreshRate = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getRefreshRate();
        long deltaMili = 1000 / systemRefreshRate;
        long deltaNano = (long)(((1000 % systemRefreshRate) / (double)systemRefreshRate ) * 1_000_000); // 1 mili = 1e6 nano; 1s = 1e9 nano
        DecimalFormat df = new DecimalFormat("#.##");
        long updateStartTime, nextUpdateTime, renderStartTime;

        // Game loop
        while (running){
            String title = "";
            updateStartTime = System.currentTimeMillis();
            nextUpdateTime = System.currentTimeMillis() + deltaMili;

            // Update logic
            updateState();
            title = title.concat(gameName + " U " + (System.currentTimeMillis() - updateStartTime) + " + ");

            // Render logic
            renderStartTime = System.currentTimeMillis();

            Graphics2D g = (Graphics2D)bufferStrategy.getDrawGraphics();

            renderState(g);

            g.dispose();
            bufferStrategy.show();
            title = title.concat("R " + (System.currentTimeMillis() - renderStartTime) + " / ");

            // Game loop counter Update
            gameLoopCount++;
            title = title.concat(df.format(1000 / (double)systemRefreshRate) + " ; G " + gameLoopCount + " / S " + systemRefreshRate);

            // Game loop Wait
            if(nextUpdateTime > System.currentTimeMillis()){
                GFrameUtility.sleep(nextUpdateTime - System.currentTimeMillis()); // Adding nano(deltaNano) causes undershot
            }

            // Game Title Update
            if(System.currentTimeMillis() - currentTime > 1000) {
                jFrame.setTitle(title);
                currentTime = System.currentTimeMillis();
                gameLoopCount = 0;
            }

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

    private void updateState() {
        for(GameResource gameResource : gameResourceList)
            gameResource.update();
    }

    private void renderState(Graphics2D g) {
        // Clean the Canvas before redrawing
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, (int)dimension.getWidth(), (int)dimension.getHeight());

        for(GameResource gameResource : gameResourceList)
            gameResource.render(g);
    }
}
