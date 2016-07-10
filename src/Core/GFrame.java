package Core;

import Game.Asteroid;
import Game.Bullet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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

    public static int mX, mY;

    public void init() {
        gameStartTime = System.nanoTime();
        gameTitle = "Asteroid";
        dimension = new Dimension(600, 600);
        jFrame = new JFrame();
        canvas = new Canvas();

        if(fullscreen) {
            // Fix dimension

            // Fullscreen jFrame and canvas

        } else
        {
            // Fix dimension
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

        // Add simple mouse motion listener
        canvas.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if(GFrame.paused == false){
                    GFrame.mX = e.getX();
                    GFrame.mY = e.getY();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                if(GFrame.paused == false){
                    GFrame.mX = e.getX();
                    GFrame.mY = e.getY();
                }
            }
        });

        canvas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_P){
                    if(GFrame.paused == true) {
                        GFrame.paused = false;

                        try {
                            Robot robot = new Robot();
                            robot.mouseMove((int)GFrame.jFrame.getLocationOnScreen().getX() + GFrame.mX, (int)GFrame.jFrame.getLocationOnScreen().getY() + GFrame.mY);
                        } catch (AWTException e1) {
                            e1.printStackTrace();
                        }
                    }
                    else
                        GFrame.paused = true;
                }

                if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                    System.exit(0);
                }
            }
        });

        // initial dummy value of tpf
        tpf = (float)(System.nanoTime() -  gameStartTime)/1000000000;
    }

    public void start(){
        // Initialize game loop variables
        long oneSecInterval = System.nanoTime();
        long loopStartTime, nextLoopTime = 0; // Requires initialization for multiple data path
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
            if(!paused)         // If paused not update takes place, but the game loops still keeps running
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
            tempTitle = tempTitle.concat(" FPS " + fps + " ; " + gameObjectList.size());
            jFrame.setTitle(tempTitle);

            // Thread.sleep() for capped fps, or calculate tpf
            if(userfps > 0) {
                if(System.nanoTime() < nextLoopTime) {
                    GFrameUtility.sleep((nextLoopTime - System.nanoTime()) / 1000000); // Convert nano sec to mili sec
                }
            } else {
                tpf = (float) (System.nanoTime() - loopStartTime) / 1000000000;
            }
        }

        // GFrame exit cleanup
    }

//	ArrayList<GameObject> gameObjectList = new ArrayList<>();
    CopyOnWriteArrayList<GameObject> gameObjectList = new CopyOnWriteArrayList<>();

    // Add one GameObject
    public void add(GameObject gameObject){
        if(gameObject == null)
            throw new NullPointerException();
        else {
            // add GameObject
            gameObjectList.add(gameObject);

            // get listeners using GameObject.getEventListeners()
            if(gameObject.getEventListeners() != null && gameObject.getEventListeners().size() >0 ){
                for(int i = 0; i< gameObject.getEventListeners().size(); i++) {
                    // Add KeyListener
                    if(gameObject.getEventListeners().get(i) instanceof KeyListener )
                        canvas.addKeyListener((KeyListener)gameObject.getEventListeners().get(i));

                    // Add MouseListener
                    if(gameObject.getEventListeners().get(i) instanceof MouseListener )
                        canvas.addMouseListener((MouseListener)gameObject.getEventListeners().get(i));

                    // Add MouseMotionListener
                    if (gameObject.getEventListeners().get(i) instanceof MouseMotionListener)
                        canvas.addMouseMotionListener((MouseMotionListener) gameObject.getEventListeners().get(i));
                }
            }
        }
    }

    // Add an array of GameObject
    public void add(GameObject[] gameObject){
        if(gameObject == null)
            throw new NullPointerException();
        else
            for(int i = 0; i<gameObject.length; i++){
                add(gameObject[i]);
            }
    }

    // return gameObjectList
    public CopyOnWriteArrayList getGameObjectList(){
        return gameObjectList;
    }

    // Call update methods all GameObjects
    private void update(float tpf, Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, (int)dimension.getWidth(), (int)dimension.getHeight());

        for(GameObject gameObject : gameObjectList)
            gameObject.update(tpf, g);
    }
}
