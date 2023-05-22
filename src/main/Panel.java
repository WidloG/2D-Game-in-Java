package main;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel implements Runnable {

    final int tileSizeOG = 16;
    final int scale = 3;
    final int tileSize = tileSizeOG * scale; //96px

    final int screenColumns = 16;
    final int screenRows = 12;

    final int panelWidth =  tileSize * screenColumns;
    final int panelHeight = tileSize * screenRows;

    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    int FPS = 60;

    KeyboardInput key = new KeyboardInput();
    Thread mainThread;

    //Panel options
    public Panel(){
        this.setPreferredSize(new Dimension(panelWidth,panelHeight));
        this.setDoubleBuffered(true);
        this.addKeyListener(key);
        this.setFocusable(true);
    }

    //Time management
    public void startThread(){
        mainThread = new Thread(this);
        mainThread.start();
    }

    //Game loop CORE
    @Override
    public void run() {
        double sleeping = 1000000000/FPS;
        double drawSleep = System.nanoTime() + sleeping;


        while(mainThread != null){
            update();
            repaint();  //paintComponent();


            try {
                double remainTime = drawSleep - System.nanoTime();
                remainTime = remainTime/1000000;

                if (remainTime < 0) remainTime = 0;
                Thread.sleep((long)remainTime);

                drawSleep += sleeping;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

    }

    //Update of position
    public void update(){
        if(key.up) playerY -= playerSpeed;
        if(key.down) playerY += playerSpeed;
        if(key.left) playerX -= playerSpeed;
        if(key.right) playerX += playerSpeed;
    }

    //Drawing
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.white);
        g2.fillRect(playerX, playerY, tileSize, tileSize);
        g2.dispose();

    }
}
