package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;

public class MONSTER_Golem extends Entity {

    public MONSTER_Golem(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;
        collisionOn = true;
        width = 3;
        height = 3;
        solidArea.x = gp.tileSize / 2;
        solidArea.y = gp.tileSize / 2;
        solidArea.width = gp.tileSize * width - gp.tileSize;
        solidArea.height = gp.tileSize * height - gp.tileSize / 2;

        type = 2;
        maxLife = 10;
        life = maxLife;

        getImage();
        getAttackImage();

    }

    public void getImage() {
        up1 = setup("../assets/golem/tile000");

        up2 = setup("../assets/golem/tile001");

        down1 = setup("../assets/golem/tile002");

        down2 = setup("../assets/golem/tile003");

        left1 = setup("../assets/golem/tile004");

        left2 = setup("../assets/golem/tile005");

        right1 = setup("../assets/golem/tile006");

        right2 = setup("../assets/golem/tile007");
    }

    public void getAttackImage() {
        up_fight_1 = setup("../assets/golem/up_fight_1");
        up_fight_2 = setup("../assets/golem/up_fight_2");
        down_fight_1 = setup("../assets/golem/down_fight_1");
        down_fight_2 = setup("../assets/golem/down_fight_2");
        left_fight_1 = setup("../assets/golem/left_fight_1");
        left_fight_2 = setup("../assets/golem/left_fight_2");
        right_fight_1 = setup("../assets/golem/right_fight_1");
        right_fight_2 = setup("../assets/golem/right_fight_2");
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
