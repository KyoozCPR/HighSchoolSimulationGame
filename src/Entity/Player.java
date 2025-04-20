package Entity;
import Main.Panel;
import Main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends Entity{
    public Panel gP;
    public KeyHandler kH;
    public File input_file;
    public Image image;

    public Player(int x, int y, Panel gP, KeyHandler kH) {
        super(x, 5 ,y);
        this.gP = gP;
        this.kH = kH;
        this.input_file = new File("src/Walking sprites/boy_down_1.png");
    }

    public void update(double deltaTime){
        try {
            if (kH.upPressed) {
                super.y -= (int) (5 * deltaTime);
                input_file = new File("src/Walking sprites/boy_up_1.png");
            }
            if (kH.rightPressed) {
                super.x += (int) (5 * deltaTime);
                input_file = new File("src/Walking sprites/boy_right_1.png");
            }
            if (kH.leftPressed) {
                super.x -= (int) (5 * deltaTime);
                input_file = new File("src/Walking sprites/boy_left_1.png");
            }
            if (kH.downPressed) {
                super.y += (int) (5 * deltaTime);
                input_file = new File("src/Walking sprites/boy_down_1.png");
            }

            image  = new BufferedImage(
                    gP.scaledtileSize, gP.scaledtileSize, BufferedImage.TYPE_INT_ARGB);

            image = ImageIO.read(input_file);

        }
        catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    public void draw(Graphics2D g2){
        System.out.println(super.x + ","+ super.y);
        g2.drawImage(image, super.x, super.y,gP.scaledtileSize, gP.scaledtileSize, null);
    }
}
