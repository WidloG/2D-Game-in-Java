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

    Thread mainThread;

    public Panel(){
        this.setPreferredSize(new Dimension(panelWidth,panelHeight));
        this.setDoubleBuffered(true);
    }

    //Time management
    public void startThread(){
        mainThread = new Thread(this);
        mainThread.start();
    }

    @Override
    public void run() {

    }
}
