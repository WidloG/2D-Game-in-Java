package entity;
import main.Panel;
import main.KeyboardInput;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{
    Panel panel;
    KeyboardInput key;

    public final int screenX;
    public final int screenY;

    public Player(Panel panel, KeyboardInput key){
        this.panel = panel;
        this.key = key;

        screenY = panel.panelHeight/2 - (panel.tileSize/2);
        screenX = panel.panelWidth/2 - (panel.tileSize/2);

        solid = new Rectangle(8, 16, 32, 32);

        setValues();
        getImage();

    }

    public void setValues() {
        worldX = panel.tileSize * 26;
        worldY = panel.tileSize * 26;
        speed = 5;
        direction = "left";
    }

    public void getImage()  {
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("playerIMG/up1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("playerIMG/up2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("playerIMG/down1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("playerIMG/down2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("playerIMG/left1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("playerIMG/left2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("playerIMG/right1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("playerIMG/right2.png")));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void update(){
        if(key.up || key.down || key.right || key.left) {
            if (key.up) direction = "up";
            if (key.down) direction = "down";
            if (key.left) direction = "left";
            if (key.right) direction = "right";


            //Player Colissions with tiles
            collision = false;
            panel.collisionCheck.checkTile(this);

            if(!collision){
                switch (direction) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }
            }

            //Walking visualization

            spriteCounter++;
            if (spriteCounter > 12 - speed) {
                if (spriteNum == 1) spriteNum = 2;
                else if (spriteNum == 2) spriteNum = 1;
                spriteCounter = 0;
            }
        } else {
          spriteNum = 1;
        }
    }

    public void draw(Graphics2D g2){
        BufferedImage image = null;

        switch (direction) {
            case "up" -> {
                if (spriteNum == 1) image = up1;
                if (spriteNum == 2) image = up2;
            }
            case "down" -> {
                if (spriteNum == 1) image = down1;
                if (spriteNum == 2) image = down2;
            }
            case "right" -> {
                if (spriteNum == 1) image = right1;
                if (spriteNum == 2) image = right2;
            }
            case "left" -> {
                if (spriteNum == 1) image = left1;
                if (spriteNum == 2) image = left2;
            }
        }

        g2.drawImage(image, screenX, screenY, panel.tileSize, panel.tileSize, null);

    }
}
