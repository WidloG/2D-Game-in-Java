package main;

import entity.Entity;

public class Collision {

    Panel panel;

    public Collision(Panel panel){
        this.panel = panel;
    }

    public void checkTile(Entity entity){
        int entityLeftX = entity.worldX + entity.solid.x;
        int entityRightX = entity.worldX + entity.solid.x + entity.solid.width;
        int entityTopY = entity.worldY + entity.solid.y;
        int entityBottomY = entity.worldY + entity.solid.y + entity.solid.height;

        int entityLeftCol = entityLeftX/panel.tileSize;
        int entityRightCol = entityRightX/panel.tileSize;
        int entityTopRow = entityTopY/panel.tileSize;
        int entityBottomRow = entityBottomY/panel.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up" -> {
                entityTopRow = (entityTopY - entity.speed) / panel.tileSize;
                tileNum1 = panel.tile.mapNum[entityLeftCol][entityTopRow];
                tileNum2 = panel.tile.mapNum[entityRightCol][entityTopRow];
                if (panel.tile.tile[tileNum1].collision || panel.tile.tile[tileNum2].collision) entity.collision = true;
            }
            case "down" -> {
                entityBottomRow = (entityBottomY + entity.speed) / panel.tileSize;
                tileNum1 = panel.tile.mapNum[entityLeftCol][entityBottomRow];
                tileNum2 = panel.tile.mapNum[entityRightCol][entityBottomRow];
                if (panel.tile.tile[tileNum1].collision || panel.tile.tile[tileNum2].collision) entity.collision = true;
            }
            case "left" -> {
                entityLeftCol = (entityLeftX - entity.speed) / panel.tileSize;
                tileNum1 = panel.tile.mapNum[entityLeftCol][entityTopRow];
                tileNum2 = panel.tile.mapNum[entityLeftCol][entityBottomRow];
                if (panel.tile.tile[tileNum1].collision || panel.tile.tile[tileNum2].collision) entity.collision = true;
            }
            case "right" -> {
                entityRightCol = (entityRightX + entity.speed) / panel.tileSize;
                tileNum1 = panel.tile.mapNum[entityRightCol][entityTopRow];
                tileNum2 = panel.tile.mapNum[entityRightCol][entityBottomRow];
                if (panel.tile.tile[tileNum1].collision || panel.tile.tile[tileNum2].collision) entity.collision = true;
            }
        }
    }
}
