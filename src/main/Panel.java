package main;

import entity.Player;
import tile.Manager;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel implements Runnable {

    //screen
    final int tileSizeOG = 16;
    final int scale = 3;
    public final int tileSize = tileSizeOG * scale; //48px

    public final int screenColumns = 16;
    public final int screenRows = 12;

    public final int panelWidth =  tileSize * screenColumns;
    public final int panelHeight = tileSize * screenRows;

    //world map
    public final int worldCol = 50;
    public final int worldRow = 50;
    public final int worldWidth = tileSize * worldCol;
    public final int worldHeight = tileSize * worldRow;

    int FPS = 30;
    Thread mainThread;

    KeyboardInput key = new KeyboardInput();
    public Player player = new Player(this, key);
    Manager tile = new Manager(this);


    //Panel options
    public Panel(){
        this.setPreferredSize(new Dimension(panelWidth,panelHeight));
        this.setDoubleBuffered(true);
        this.addKeyListener(key);
        this.setFocusable(true);
        this.setBackground(Color.black);
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
        player.update();
    }

    //Drawing
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        tile.draw(g2);
        player.draw(g2);
        g2.dispose();

    }
}
