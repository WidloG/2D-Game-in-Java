package tile;
import main.Panel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;


public class Manager {

    Panel panel;
    Tile[] tile;
    int[][] mapNum;

    public Manager(Panel panel) {
        this.panel = panel;
        tile = new Tile[10];
        mapNum = new int[panel.worldCol][panel.worldRow];

        getImage();
        getMap();
    }

    public void getImage(){

        // 0 - grass
        // 1 - water
        // 2 - wood
        // 3 - tree
        // 4 - bush
        // 5 - sand
        // 6 - ground

        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("backgroundIMG/grass.png")));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("backgroundIMG/water.png")));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("backgroundIMG/wood.png")));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("backgroundIMG/tree.png")));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("backgroundIMG/bush.png")));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("backgroundIMG/sand.png")));

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("backgroundIMG/ground.png")));


        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void getMap(){
        try{
            InputStream is = getClass().getClassLoader().getResourceAsStream("maps/mapG.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < panel.worldCol && row < panel.worldRow){
                String line = br.readLine();

                while(col < panel.worldCol){
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapNum[col][row] = num;
                    col++;

                }

                if(col == panel.worldCol){
                    col = 0;
                    row ++;
                }
            }
            br.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
        int col = 0;
        int row = 0;

        while (col < panel.worldCol && row < panel.worldRow){
            int tileNum = mapNum[col][row];
            int worldX = col * panel.tileSize;
            int worldY = row * panel.tileSize;
            int screenX = worldX - panel.player.worldX + panel.player.screenX;
            int screenY = worldY - panel.player.worldY + panel.player.screenY;

            if(worldX + panel.tileSize > panel.player.worldX - panel.player.screenX && worldX - panel.tileSize < panel.player.worldX + panel.player.screenX &&
                    worldY + panel.tileSize > panel.player.worldY - panel.player.screenY && worldY - panel.tileSize < panel.player.worldY + panel.player.screenY ) {
                g2.drawImage(tile[tileNum].image, screenX, screenY, panel.tileSize, panel.tileSize, null);
            }
            col ++;

            if(col == panel.worldCol){
                col = 0;
                row++;
            }
        }
    }
}
