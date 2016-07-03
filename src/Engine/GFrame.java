package Engine;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class GFrame {
    private String gameTitle;

    public static Dimension dimension;
    private Dimension crtJfrmDim, crtCnvsDim;

    public boolean fullscreen = false;

    private JFrame jFrame;
    public Canvas canvas;
    BufferStrategy bufferStrategy;

    public GFrame() {
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
            crtJfrmDim = new Dimension((int) dimension.getWidth() + 6, (int) dimension.getHeight() + 35); // Insets[top=32,left=3,bottom=3,right=3]
            crtCnvsDim = new Dimension((int) dimension.getWidth() + 6, (int) dimension.getHeight() + 35); // Insets[top=32,left=3,bottom=3,right=3]

            // Windowed jFrame and Canvas
            jFrame.setTitle(gameTitle);
            jFrame.setSize(crtJfrmDim);
            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jFrame.setLocationRelativeTo(null);
            jFrame.setUndecorated(false);
            jFrame.setLayout(null);
            jFrame.setResizable(false);
            jFrame.setVisible(true);

            canvas.setSize(crtCnvsDim);
            canvas.setLocation(0,0);
            jFrame.add(canvas);
            canvas.requestFocus();
        }

        // Setup BufferStrategy for canvas
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
            title = title.concat(gameTitle + " U " + (System.currentTimeMillis() - updateStartTime) + " + ");

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
