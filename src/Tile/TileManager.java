package Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class TileManager {
    public Tile[] tileset;
    public Tile[][] tilemap;

    public TileManager() throws IOException {
        setTileset();
        setTileMap();
    }




    // retrieve tile images from Maps directory, creating new Tiles in the tileMap
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

    private void setTileMap() {
        try{
            BufferedReader reader = new BufferedReader(new FileReader("map_represetation.txt"));
            int j=0;
            while (reader.readLine() != null){
                String[] id = reader.readLine().split(" ");
                for (int i=0; i< id.length; i++)
                    tilemap[i][j] = tileset[Integer.parseInt(id[i])];
                j++;

            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

