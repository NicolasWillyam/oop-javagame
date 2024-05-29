package entity;

import java.util.Random;

import main.GamePanel;

public class NPC_Merchant extends Entity {
    public NPC_Merchant(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;
        collisionOn = true;
        solidArea.x = gp.tileSize;
        solidArea.y = gp.tileSize;
        solidArea.width = gp.tileSize * 2;
        solidArea.height = gp.tileSize * 2;
        width = 3;
        height = 3;

        getImage();
        setDialogue();
    }

    public void getImage() {
        up1 = setup("../assets/NPC/1/tile000");

        up2 = setup("../assets/NPC/1/tile001");

        down1 = setup("../assets/NPC/1/tile002");

        down2 = setup("../assets/NPC/1/tile003");

        left1 = setup("../assets/NPC/1/tile004");

        left2 = setup("../assets/NPC/1/tile005");

        right1 = setup("../assets/NPC/1/tile006");

        right2 = setup("../assets/NPC/1/tile007");
    }

    public void setDialogue() {

        dialogues[0] = "Recently, there's been a terrible monster lurking around the northern forest. \nPlease bring them to me. I can exchange them for valuable items. Be careful!";

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

    public void speak() {
        super.speak();
    }

}
