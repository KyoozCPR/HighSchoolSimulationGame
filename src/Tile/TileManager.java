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
        try{
            BufferedReader reader = new BufferedReader(new FileReader("src/Tile/map_rapresentation.txt"));

            int j=0;
            ArrayList<Tile> ids = new ArrayList<>();
            while (reader.readLine() != null){
                String[] id = reader.readLine().split(" ");
                for (int i=0; i< id.length; i++)
                    ids.add(tileset[Integer.parseInt(id[i])]);
                tilemap.add(ids);
                j++;

            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void draw(Graphics2D g2){

        int x=-32;
        int y=0;
        for (int i=0; i<tilemap.size(); i++){
            x+=32;
            for (int j=0; j<tilemap.get(i).size(); j++){
                g2.drawImage(tilemap.get(i).get(j).image, x+32, y, gP.scaledtileSize, gP.scaledtileSize, null);
            }
            y+=32;
        }
    }
}

