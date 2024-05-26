package main;

import entity.NPC_Merchant;
import monster.MONSTER_Golem;
import monster.MONSTER_Moose;
import object.OBJ_Altar;
import object.OBJ_BinhVang;
import object.OBJ_Boot;
import object.OBJ_CCung;
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

        gp.obj[3] = new OBJ_Altar();
        gp.obj[3].worldX = 28 * gp.tileSize;
        gp.obj[3].worldY = 14 * gp.tileSize;

        gp.obj[4] = new OBJ_Altar();
        gp.obj[4].worldX = 41 * gp.tileSize;
        gp.obj[4].worldY = 2 * gp.tileSize;

        gp.obj[5] = new OBJ_Altar();
        gp.obj[5].worldX = 5 * gp.tileSize;
        gp.obj[5].worldY = 31 * gp.tileSize;

        gp.obj[6] = new OBJ_Altar();
        gp.obj[6].worldX = 34 * gp.tileSize;
        gp.obj[6].worldY = 29 * gp.tileSize;

        gp.obj[7] = new OBJ_Altar();
        gp.obj[7].worldX = 26 * gp.tileSize;
        gp.obj[7].worldY = 35 * gp.tileSize;

        gp.obj[8] = new OBJ_BinhVang();
        gp.obj[8].worldX = 7 * gp.tileSize;
        gp.obj[8].worldY = 45 * gp.tileSize;

        gp.obj[9] = new OBJ_BinhVang();
        gp.obj[9].worldX = 20 * gp.tileSize;
        gp.obj[9].worldY = 35 * gp.tileSize;

        gp.obj[10] = new OBJ_BinhVang();
        gp.obj[10].worldX = 32 * gp.tileSize;
        gp.obj[10].worldY = 32 * gp.tileSize;

        gp.obj[11] = new OBJ_BinhVang();
        gp.obj[11].worldX = 46 * gp.tileSize;
        gp.obj[11].worldY = 47 * gp.tileSize;
        int index = 11;

        index++;
        gp.obj[index] = new OBJ_BinhVang();
        gp.obj[index].worldX = 44 * gp.tileSize;
        gp.obj[index].worldY = 20 * gp.tileSize;

        index++;
        gp.obj[index] = new OBJ_Knight();
        gp.obj[index].worldX = 12 * gp.tileSize;
        gp.obj[index].worldY = 15 * gp.tileSize;

        index++;
        gp.obj[index] = new OBJ_CCung();
        gp.obj[index].worldX = 32 * gp.tileSize;
        gp.obj[index].worldY = 10 * gp.tileSize;

        index++;
        gp.obj[index] = new OBJ_Knight();
        gp.obj[index].worldX = 26 * gp.tileSize;
        gp.obj[index].worldY = 27 * gp.tileSize;

        index++;
        gp.obj[index] = new OBJ_CCung();
        gp.obj[index].worldX = 11 * gp.tileSize;
        gp.obj[index].worldY = 29 * gp.tileSize;

        index++;
        gp.obj[index] = new OBJ_Altar();
        gp.obj[index].worldX = 18 * gp.tileSize;
        gp.obj[index].worldY = 10 * gp.tileSize;

        index++;
        gp.obj[index] = new OBJ_Altar();
        gp.obj[index].worldX = 28 * gp.tileSize;
        gp.obj[index].worldY = 7 * gp.tileSize;

        index++;
        gp.obj[index] = new OBJ_Altar();
        gp.obj[index].worldX = 2 * gp.tileSize;
        gp.obj[index].worldY = 17 * gp.tileSize;

        index++;
        gp.obj[index] = new OBJ_Altar();
        gp.obj[index].worldX = 13 * gp.tileSize;
        gp.obj[index].worldY = 34 * gp.tileSize;

        index++;
        gp.obj[index] = new OBJ_Altar();
        gp.obj[index].worldX = 37 * gp.tileSize;
        gp.obj[index].worldY = 35 * gp.tileSize;

        index++;
        gp.obj[index] = new OBJ_Altar();
        gp.obj[index].worldX = 47 * gp.tileSize;
        gp.obj[index].worldY = 24 * gp.tileSize;

    }

    public void setNPC() {
        gp.npc[0] = new NPC_Merchant(gp);
        gp.npc[0].worldX = gp.tileSize * 12;
        gp.npc[0].worldY = gp.tileSize * 5;

    }

    public void setMonster() {

        int index = 0;
        index++;
        gp.monster[index] = new MONSTER_Golem(gp);
        gp.monster[index].worldX = gp.tileSize * 10;
        gp.monster[index].worldY = gp.tileSize * 10;
        index++;

        gp.monster[index] = new MONSTER_Golem(gp);
        gp.monster[index].worldX = gp.tileSize * 16;
        gp.monster[index].worldY = gp.tileSize * 14;

        index++;
        gp.monster[index] = new MONSTER_Moose(gp);
        gp.monster[index].worldX = gp.tileSize * 45;
        gp.monster[index].worldY = gp.tileSize * 8;

        index++;
        gp.monster[index] = new MONSTER_Moose(gp);
        gp.monster[index].worldX = gp.tileSize * 31;
        gp.monster[index].worldY = gp.tileSize * 11;

        index++;
        gp.monster[index] = new MONSTER_Golem(gp);
        gp.monster[index].worldX = gp.tileSize * 20;
        gp.monster[index].worldY = gp.tileSize * 18;

        index++;
        gp.monster[index] = new MONSTER_Moose(gp);
        gp.monster[index].worldX = gp.tileSize * 44;
        gp.monster[index].worldY = gp.tileSize * 19;

        index++;
        gp.monster[index] = new MONSTER_Golem(gp);
        gp.monster[index].worldX = gp.tileSize * 38;
        gp.monster[index].worldY = gp.tileSize * 25;

        index++;
        gp.monster[index] = new MONSTER_Moose(gp);
        gp.monster[index].worldX = gp.tileSize * 8;
        gp.monster[index].worldY = gp.tileSize * 33;

        index++;
        gp.monster[index] = new MONSTER_Golem(gp);
        gp.monster[index].worldX = gp.tileSize * 19;
        gp.monster[index].worldY = gp.tileSize * 36;

        index++;
        gp.monster[index] = new MONSTER_Golem(gp);
        gp.monster[index].worldX = gp.tileSize * 46;
        gp.monster[index].worldY = gp.tileSize * 40;

        index++;
        gp.monster[index] = new MONSTER_Golem(gp);
        gp.monster[index].worldX = gp.tileSize * 16;
        gp.monster[index].worldY = gp.tileSize * 46;

        index++;
        gp.monster[index] = new MONSTER_Golem(gp);
        gp.monster[index].worldX = gp.tileSize * 33;
        gp.monster[index].worldY = gp.tileSize * 42;
    }

}
