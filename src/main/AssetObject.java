package main;

import object.Chest;
import object.Door;
import object.Key;

public class AssetObject {

    Panel panel;

    public AssetObject(Panel panel){
        this.panel = panel;
    }

    public void setObject(){
        panel.object[0] = new Key();
        panel.object[0].worldX = 3 * panel.tileSize;
        panel.object[0].worldY = 14 * panel.tileSize;

        panel.object[1] = new Key();
        panel.object[1].worldX = 34 * panel.tileSize;
        panel.object[1].worldY = 37 * panel.tileSize;

        panel.object[2] = new Door();
        panel.object[2].worldX = 6 * panel.tileSize;
        panel.object[2].worldY = 41 * panel.tileSize;

        panel.object[3] = new Chest();
        panel.object[3].worldX = 3 * panel.tileSize;
        panel.object[3].worldY = 45 * panel.tileSize;
    }
}
