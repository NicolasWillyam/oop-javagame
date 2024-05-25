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
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = gp.tileSize * 3;
        solidArea.height = gp.tileSize * 3;
        width = 3;
        height = 3;
        type = 2;

        getImage();

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

}
