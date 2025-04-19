package Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TileManager {
    public Tile[] tileset;
    public Tile[][] tilemap;

    public TileManager() throws IOException {
        setTileset();
        setTileMap();
    }


    // retrieve tile images from spritesheet.png by separating them into 32x32px tiles
    private void setTileset() throws IOException {
        this.tileset = new Tile[10];
        File[] tilesDirectory = new File("src/Maps").listFiles();

        for (int i=0; i<tileset.length; i++){
            BufferedImage image  = new BufferedImage(
                    32*3, 32*3, BufferedImage.TYPE_INT_ARGB);
            try {
                image = ImageIO.read(tilesDirectory[i].getAbsoluteFile());
            }
            catch(IOException e){
                e.printStackTrace();
            }
            tileset[i] = new Tile(image);

        }

    }
}
