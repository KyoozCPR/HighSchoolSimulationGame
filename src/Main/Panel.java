package Main;
import javax.swing.*;
import java.awt.*;




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
    Thread gameThread;
    KeyHandler kHandler;

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
        // measure the time each frame takes, then sleep for the remaining time until 16.67 ms have elapsed.
        final double timePerFrame = 1_000_000_000 / 60.0; // 16.67 ms for 60FPS
        while (gameThread != null){
            long startTime = System.nanoTime();

            update();
            repaint();
            // frame rendered so now we measure how much time it took
            long elapsed = System.nanoTime() - startTime;
            long waitTime = (long)(timePerFrame - elapsed) / 1_000_000; // convert to ms

            if (waitTime < 0) {
                waitTime = 5; // minimal wait to prevent tight looping if behind schedule
            }

            try {
                Thread.sleep(waitTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    public void update(){
        System.out.println(squareX + "," + squareY);
        if (kHandler.upPressed)
            squareY -= 5 ;
        if (kHandler.rightPressed)
            squareX += 5;
        if (kHandler.leftPressed)
            squareX -= 5;
        if (kHandler.downPressed)
            squareY += 5;
    }

    @Override
    //JComponent method to paint, overridden for custom components
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // to recall the original method
        g.drawString("This is my custom Panel!",10,20);
        Graphics2D g2 = (Graphics2D)g;
        g2.fillRect(squareX, squareY, squareW, squareH);
        g2.dispose();
    }
}


