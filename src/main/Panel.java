package main;

import javax.swing.*;

public class Panel extends JPanel {

    final int tileSizeOG = 32;
    final int scale = 3;
    final int tileSize = tileSizeOG * scale; //96px

    final int screenColumns = 16;
    final int screenRows = 12;

    final int windowWidth =  tileSize * screenColumns; // 1536px
    final int windowHeight = tileSize * screenRows; // 1152px




}
