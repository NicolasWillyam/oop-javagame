package main;

import entity.NPC_Merchant;
import monster.MONSTER_Golem;
import object.OBJ_Altar;
import object.OBJ_Boot;
import object.OBJ_Knight;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {

        gp.obj[1] = new OBJ_Altar();
        gp.obj[1].worldX = 5 * gp.tileSize;
        gp.obj[1].worldY = 9 * gp.tileSize;

        gp.obj[2] = new OBJ_Knight();
        gp.obj[2].worldX = 4 * gp.tileSize;
        gp.obj[2].worldY = 11 * gp.tileSize;

        // gp.obj[3] = new OBJ_Boot();
        // gp.obj[3].worldX = 10 * gp.tileSize;
        // gp.obj[3].worldY = 11 * gp.tileSize;

    }

    public void setNPC() {
        gp.npc[0] = new NPC_Merchant(gp);
        gp.npc[0].worldX = gp.tileSize * 12;
        gp.npc[0].worldY = gp.tileSize * 5;

    }

    public void setMonster() {
        gp.monster[0] = new MONSTER_Golem(gp);
        gp.monster[0].worldX = gp.tileSize * 10;
        gp.monster[0].worldY = gp.tileSize * 10;

        gp.monster[1] = new MONSTER_Golem(gp);
        gp.monster[1].worldX = gp.tileSize * 16;
        gp.monster[1].worldY = gp.tileSize * 14;
    }

}
