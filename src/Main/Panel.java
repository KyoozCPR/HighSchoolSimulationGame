package Main;
import Entity.Player;
import Tile.TileManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Panel extends JPanel implements Runnable{
    //we need to scale the pixels
    public final int scaledtileSize = 32*3; // 32x32px scaled tile
    final int maxScreenCol = 20; // X axis
    final float maxScreenRow = 11.25F; // Y
    final int screenWidth = maxScreenCol * scaledtileSize;
    final int screenHeight = (int) (maxScreenRow * scaledtileSize);
    private int squareX;
    private int squareY;
    public Player player;
    public TileManager tileset;
    Thread gameThread;
    KeyHandler kHandler;
    long waitTimeMs;


    public Panel() throws IOException {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.WHITE);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.kHandler = new KeyHandler();
        this.addKeyListener(kHandler);
        this.squareX = (screenWidth/2)-32;
        this.squareY = (screenHeight/2)-32;
        this.player = new Player(squareX,squareY,this, this.kHandler);
        this.tileset = new TileManager(this);


    }

    public void startThread(){
        this.gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        this.add(new JLabel("Ciao!"));
        // Because we want a fixed 120 fps, we measure the time each frame takes, then sleep for the remaining time until 8.3 ms have elapsed.
        final double timePerFrame = 1_000_000_000 / 120.0;// 8.3 ms for 120FPS
        while (gameThread != null){
            long startTime = System.nanoTime();

            update();
            repaint();
            // frame rendered so now we measure how much time it took
            long elapsed = System.nanoTime() - startTime;
            waitTimeMs = (long)(timePerFrame - elapsed) / 1_000_000; // Delta time in ms to sleep

            if (waitTimeMs < 0) // if the frame rendered in more that timePerFrame, then it shouldn't wait
                waitTimeMs = 0;


            try {
                Thread.sleep(waitTimeMs);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    public void update(){
        System.out.println(squareX + "," + squareY);
        double deltaTime = waitTimeMs / 10.0; // actual DT in nanoseconds,  used to make the speed of the player consistent across all fps
        player.update(deltaTime);

    }

    @Override
    //JComponent method to paint, overridden for custom components
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // to recall the original method
        Graphics2D g2 = (Graphics2D)g;
        tileset.draw(g2);
        player.draw(g2); //draw the player image
        setDoubleBuffered(true);
    }
}


