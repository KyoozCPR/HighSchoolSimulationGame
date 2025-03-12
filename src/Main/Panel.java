package Main;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Panel extends JPanel implements Runnable{
    //we need to scale the pixels
    final int scaledtileSize = 48; // 16x16px scaled tile
    final int maxScreenCol = 16; // X axis
    final int maxScreenRow = 12; //
    final int screenWidth = maxScreenCol * scaledtileSize;
    final int screenHeight = maxScreenRow * scaledtileSize;
    private int squareX = 50;
    private int squareY = 50;
    private int squareW = 20;
    private int squareH = 20;
    private BufferedImage image;
    Thread gameThread;
    KeyHandler kHandler;
    long waitTimeMs;


    public Panel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.kHandler = new KeyHandler();
        this.addKeyListener(kHandler);


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
        File input_file = new File("src/Walking sprites/boy_down_1.png");;
        try {
        if (kHandler.upPressed) {
            squareY -= 5 * deltaTime;
            input_file = new File("src/Walking sprites/boy_up_1.png");
        }
        if (kHandler.rightPressed) {
            squareX += 5 * deltaTime;
            input_file = new File("src/Walking sprites/boy_right_1.png");
        }
        if (kHandler.leftPressed) {
            squareX -= 5 * deltaTime;
            input_file = new File("src/Walking sprites/boy_left_1.png");
        }
        if (kHandler.downPressed) {
            squareY += 5 * deltaTime;

        }
        image = new BufferedImage(
                scaledtileSize, scaledtileSize, BufferedImage.TYPE_INT_ARGB);
        image = ImageIO.read(input_file);
        }
        catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    @Override
    //JComponent method to paint, overridden for custom components
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // to recall the original method
        g.drawString("This is my custom Panel!",10,20);
        Graphics2D g2 = (Graphics2D)g;
        g2.drawImage(image, squareX, squareY, null);
    }
}


