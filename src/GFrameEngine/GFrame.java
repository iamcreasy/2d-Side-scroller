package GFrameEngine;

import javax.swing.*;
import java.awt.*;

public class GFrame {
    Dimension dimension;
    public JFrame jFrame;
    public Canvas canvas;
    String gameName;

    public void start(){
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
    }
}
