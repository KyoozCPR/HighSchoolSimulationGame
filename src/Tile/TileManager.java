package Tile;

import javax.imageio.ImageIO;
import Main.Panel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class TileManager {
    public Panel gP;
    public Tile[] tileset;
    public ArrayList<ArrayList<Tile>> tilemap;

    public TileManager(Panel gP) throws IOException {
        this.gP = gP;
        setTileset();
        setTileMap();

    }




    // retrieve tile images from Maps directory, creating new Tiles in the tileMap
    private void setTileset() throws IOException {
        this.tileset = new Tile[11];
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
        for(Tile tile: tileset){
            System.out.println(tile.image);
        }

    }

    private void setTileMap() {
        this.tilemap = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/Tile/map_rapresentation.txt"));
            String line; // used inside the condition otherwise it would call readline() 2 times per iteration
            while ((line = reader.readLine()) != null){
                ArrayList<Tile> ids = new ArrayList<>();
                String[] id = line.split(" ");
                for (int i=0; i<id.length; i++) {
                    System.out.println(id[i]);
                    ids.add(tileset[Integer.parseInt(id[i])]);
                }
                tilemap.add(ids);

            }

            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void draw(Graphics2D g2){

        int x=-32*3;
        int y=0;

        for (int i=0; i<tilemap.size(); i++){

            for (int j=0; j<20; j++){
                g2.drawImage(tilemap.get(i).get(j).image, x+=32*3, y, gP.scaledtileSize, gP.scaledtileSize, null);
            }
            y+=32*3;
            x=-32*3;
        }
    }
}

