package Engine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.concurrent.CopyOnWriteArrayList;

public class GFrame {
    private String tempTitle;
    private long gameStartTime;
    private float tpf;
    private int userfps = 60;    // Set it to zero for unlimited fps

    public static String gameTitle;
    public static Dimension dimension;
    public static Dimension crtJfrmDim, crtCnvsDim;

    public static boolean fullscreen = false;
    public static boolean cursorVisible = false;
    public static boolean running = true;
    public static boolean paused = false;

    public static JFrame jFrame;
    public static Canvas canvas;
    public static BufferStrategy bufferStrategy;

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

        // set cursor invisible
        if(!cursorVisible){
            BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
            Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
            jFrame.getContentPane().setCursor(blankCursor);
        }

        // Setup BufferStrategy for canvas
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();

        // initial dummy value of tpf
        tpf = (float)(System.nanoTime() -  gameStartTime)/1000000000;
    }

    public void start(){
        // Initialize game loop variabless
        long oneSecInterval = System.nanoTime();
        long loopStartTime, nextLoopTime = 0; // Requirs initialization for multiple data path
        long loopCount = 0;
        long fps = 0;

        if(userfps > 0){
            tpf = (1000000000 / userfps)/(float)1000000000;
        }

        // Game loop
        while (running){
            tempTitle = "";
            tempTitle = tempTitle.concat(gameTitle);
            loopStartTime = System.nanoTime();
            if(userfps > 0) {
                nextLoopTime = loopStartTime + (1000000000 / userfps);
            }

            // Update the game
            Graphics2D g = (Graphics2D)bufferStrategy.getDrawGraphics();
            if(!paused)
                update(tpf, g);
            g.dispose();
            bufferStrategy.show();

            // Game loop counter
            loopCount++;

            // Game Title Update
            if(System.nanoTime() - oneSecInterval > 1000000000) {
                fps = loopCount;
                loopCount = 0;
                oneSecInterval = System.nanoTime();
            }
            tempTitle = tempTitle.concat(" FPS " + fps + " ; " + gameResourceList.size());
            jFrame.setTitle(tempTitle);

            if(userfps > 0) {
                if(System.nanoTime() < nextLoopTime) {
                    GFrameUtility.sleep((nextLoopTime - System.nanoTime()) / 1000000); // Convert nano sec to mili sec
                }
            } else {
                tpf = (float) (System.nanoTime() - loopStartTime) / 1000000000;
            }
        }

        // Cleanup
    }

//	ArrayList<GameResource> gameResourceList = new ArrayList<>();
    CopyOnWriteArrayList<GameResource> gameResourceList = new CopyOnWriteArrayList<>();

    public void add(GameResource gameResource){
        if(gameResource == null)
            throw new NullPointerException();
        else {
            // add GameResource Object
            gameResourceList.add(gameResource);

            // add Listeners implementation to Canvas
            if(gameResource instanceof KeyListener)
                canvas.addKeyListener((KeyListener)gameResource);

            if(gameResource instanceof MouseListener)
                canvas.addMouseListener((MouseListener)gameResource);

            if(gameResource instanceof MouseMotionListener)
                canvas.addMouseMotionListener((MouseMotionListener)gameResource);

            if(gameResource instanceof MouseWheelListener)
                canvas.addMouseWheelListener((MouseWheelListener)gameResource);
        }
    }

    public void add(GameResource[] gameResource){
        if(gameResource == null)
            throw new NullPointerException();
        else
            for(int i = 0; i<gameResource.length; i++){
                add(gameResource[i]);
            }
    }

    private void update(float tpf, Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, (int)dimension.getWidth(), (int)dimension.getHeight());

        for(GameResource gameResource : gameResourceList)
            gameResource.update(tpf, g);
    }
}
