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
    public String gameTitle;
    private String tempTitle;

    private long gameStartTime;
    private float tpf;

    public static Dimension dimension;
    private Dimension crtJfrmDim, crtCnvsDim;

    public boolean fullscreen = false;
    public boolean cursorVisible = false;

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
        boolean running = true;
        long interval = System.nanoTime();
        long loopStartTime;
        long loopCount = 0;
        long fps = 0;

        // Game loop
        while (running){
            tempTitle = "";
            tempTitle = tempTitle.concat(gameTitle);
            loopStartTime = System.nanoTime();

            // Render logic
            Graphics2D g = (Graphics2D)bufferStrategy.getDrawGraphics();
            update(tpf, g);
            g.dispose();
            bufferStrategy.show();

            // Game loop counter Update
            loopCount++;

            // Game Title Update
            if(System.nanoTime() - interval > 1000000000) {
                fps = loopCount;
                loopCount = 0;
                interval = System.nanoTime();
            }
            tempTitle = tempTitle.concat(" FPS " + fps + " ; " + gameResourceList.size());
            jFrame.setTitle(tempTitle);

            tpf = (float)(System.nanoTime() - loopStartTime) / 1000000000;
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
