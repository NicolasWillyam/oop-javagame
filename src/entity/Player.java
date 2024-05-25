package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public int hasAltar = 0;
    public int hasSkill = 0;

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        attackArea.width = 48;
        attackArea.height = 48;

        setDefaultValues();
        getPlayerImage();
        getPlayerFightImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 7;
        worldY = gp.tileSize * 5;
        speed = 4;
        direction = "down";

        // PLAYER STATUS
        maxLife = 6;
        life = maxLife;
    }

    public void getPlayerImage() {
        up1 = setup("../assets/fighter/3");
        up2 = setup("../assets/fighter/4");
        down1 = setup("../assets/fighter/1");
        down2 = setup("../assets/fighter/2");
        left1 = setup("../assets/fighter/5");
        left2 = setup("../assets/fighter/7");
        right1 = setup("../assets/fighter/6");
        right2 = setup("../assets/fighter/8");

    }

    public void getPlayerFightImage() {
        up_fight_1 = setup("../assets/fighter/up_fight_1");
        up_fight_2 = setup("../assets/fighter/up_fight_2");
        down_fight_1 = setup("../assets/fighter/down_fight_1");
        left_fight_1 = setup("../assets/fighter/left_fight_1");
        left_fight_2 = setup("../assets/fighter/left_fight_2");
        right_fight_1 = setup("../assets/fighter/right_fight_1");
        right_fight_2 = setup("../assets/fighter/right_fight_2");

    }

    public void update() {

        if (attacking == true) {

            attacking();
        } else if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true
                || keyH.rightPressed == true || keyH.enterPressed == true) {

            if (keyH.upPressed == true) {
                direction = "up";

            } else if (keyH.downPressed == true) {
                direction = "down";

            } else if (keyH.leftPressed == true) {
                direction = "left";

            } else if (keyH.rightPressed == true) {
                direction = "right";
            }

            // CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // CHECK OBJECT COLLISION

            int objIndex = gp.cChecker.checkObject(this, true);
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            interactNPC(npcIndex);
            pickUpObject(objIndex);
            contactMonster(monsterIndex);

            // IF COLLISION IS FALSE, PLAYER CAN MOVE
            if (collisionOn == false && keyH.enterPressed == false) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;

                }
            }

            gp.keyH.enterPressed = false;
            spriteCounter++;
            if (spriteCounter > 10) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        } else {
            spriteNum = 1;
        }
        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void attacking() {
        spriteCounter++;

        if (spriteCounter <= 5) {
            spriteNum = 1;
        }
        if (spriteCounter > 5 && spriteCounter <= 20) {
            spriteNum = 2;

            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            switch (direction) {
                case "up":
                    worldY -= attackArea.height;
                    break;
                case "down":
                    worldY += attackArea.height;
                    break;
                case "left":
                    worldX -= attackArea.height;
                    break;
                case "right":
                    worldX += attackArea.height;
                    break;
            }

            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex);

            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }
        if (spriteCounter > 20) {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }

    public void pickUpObject(int i) {
        if (i != 999) {
            String objectName = gp.obj[i].name;
            switch (objectName) {
                case "Altar":
                    gp.playSE(1);
                    hasAltar++;
                    gp.obj[i] = null;
                    gp.ui.showMessage("You got a Altar!");
                    break;
                case "Knight":
                    gp.playSE(1);
                    gp.ui.showMessage("You got a Sword!");

                    hasSkill++;
                    gp.obj[i] = null;
                    break;

                case "Boot":
                    gp.playSE(2);
                    gp.ui.showMessage("Speed Up!");

                    speed += 2;
                    gp.obj[i] = null;
                    break;

            }
        }
    }

    public void contactMonster(int i) {
        if (i != 999) {
            if (invincible == false) {
                if (life > 1)
                    life--;
                invincible = true;
            }

        }
    }

    public void damageMonster(int i) {
        if (i != 999) {
            if (gp.monster[i].invincible == false) {
                gp.monster[i].life--;
                gp.monster[i].invincible = true;

                if (gp.monster[i].life <= 0) {
                    gp.monster[i] = null;
                }
            }
        }
    }

    public void interactNPC(int i) {
        if (i != 999) {
            gp.gameState = gp.dialogueState;
            gp.npc[i].speak();
        }
        if (gp.keyH.enterPressed == true) {

            attacking = true;
        }

    }

    public void draw(Graphics2D g2) {
        // g2.setColor(Color.white);
        // g2.fillRect(x,worldY, gp.tileSize, gp.tileSize);
        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (attacking == false) {
                    if (spriteNum == 1) {
                        image = up1;
                    }

                    if (spriteNum == 2) {
                        image = up2;
                    }
                } else {
                    if (spriteNum == 1) {
                        image = up_fight_1;
                    }
                    if (spriteNum == 2) {
                        image = up_fight_2;
                    }
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (attacking == false) {
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                } else {
                    if (spriteNum == 1) {
                        image = left_fight_1;
                    }
                    if (spriteNum == 2) {
                        image = left_fight_2;
                    }
                }
                break;
            case "right":
                if (attacking == false) {
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                } else {
                    if (spriteNum == 1) {
                        image = right_fight_1;
                    }
                    if (spriteNum == 2) {
                        image = right_fight_2;
                    }
                }
                break;
        }

        if (invincible == true) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        // System.out.println(image);

        g2.drawImage(image, screenX, screenY, 2 * gp.tileSize, 2 * gp.tileSize, null);

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}
