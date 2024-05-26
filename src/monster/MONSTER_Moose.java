package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;

public class MONSTER_Moose extends Entity {

    public MONSTER_Moose(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;
        collisionOn = true;
        width = 11;
        height = 6;
        solidArea.x = gp.tileSize * 4;
        solidArea.y = gp.tileSize * 2;
        solidArea.width = gp.tileSize * 0;
        solidArea.height = gp.tileSize * 0;

        type = 2;
        maxLife = 10;
        life = maxLife;

        getImage();
        getAttackImage();

    }

    public void getImage() {
        up1 = setup("../assets/moose/tile000");

        up2 = setup("../assets/moose/tile001");

        down1 = setup("../assets/moose/tile002");

        down2 = setup("../assets/moose/tile003");

        left1 = setup("../assets/moose/tile004");

        left2 = setup("../assets/moose/tile005");

        right1 = setup("../assets/moose/tile006");

        right2 = setup("../assets/moose/tile007");
    }

    public void getAttackImage() {
        up_fight_1 = setup("../assets/moose/1");
        up_fight_2 = setup("../assets/moose/2");
        down_fight_1 = setup("../assets/moose/1");
        down_fight_2 = setup("../assets/moose/2");
        left_fight_1 = setup("../assets/moose/3");
        left_fight_2 = setup("../assets/moose/4");
        right_fight_1 = setup("../assets/moose/3");
        right_fight_2 = setup("../assets/moose/4");
    }

    public void setAction() {
        actionLockCounter++;
        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;

            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i <= 100) {
                direction = "right";
            }
            actionLockCounter = 0;
        }

    }

    public void damageReaction() {

        if (monsterAttacking = true) {
            actionLockCounter = 0;
            monsterAttackCounter++;

            if (monsterAttackCounter <= 5) {
                spriteNum = 1;
            }
            if (monsterAttackCounter > 5 && monsterAttackCounter <= 30) {
                spriteNum = 2;
            }

            if (monsterAttackCounter > 30) {
                spriteNum = 1;
                monsterAttackCounter = 0;
                monsterAttacking = false;
            }
        }

    }

}
