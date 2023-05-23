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
        mapNum = new int[panel.screenColumns][panel.screenRows];

        getImage();
        getMap();
    }

    public void getImage(){
        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("backgroundIMG/grass.png")));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("backgroundIMG/water.png")));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("backgroundIMG/wood.png")));


        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void getMap(){
        try{
            InputStream is = getClass().getClassLoader().getResourceAsStream("maps/map01.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < panel.screenColumns && row < panel.screenRows){
                String line = br.readLine();

                while(col < panel.screenColumns){
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapNum[col][row] = num;
                    col++;

                }

                if(col == panel.screenColumns){
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
        int x = 0;
        int y = 0;

        while (col < panel.screenColumns && row < panel.screenRows){
            int tileNum = mapNum[col][row];
            g2.drawImage(tile[tileNum].image, x, y, panel.tileSize, panel.tileSize, null);
            col ++;
            x += panel.tileSize;

            if(col == panel.screenColumns){
                col = 0;
                x = 0;
                row++;
                y += panel.tileSize;
            }
        }
    }
}
