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
        solidArea.y = 8;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 16;
        solidArea.height = 16;

        setDefaultValues();
        getPlayerImage();
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
        try {

            up1 = ImageIO.read(getClass().getResourceAsStream("../assets/fighter/3.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("../assets/fighter/4.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("../assets/fighter/1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("../assets/fighter/2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("../assets/fighter/5.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("../assets/fighter/7.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("../assets/fighter/6.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("../assets/fighter/8.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true
                || keyH.rightPressed == true) {

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
            if (collisionOn == false) {
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

    public void interactNPC(int i) {
        if (i != 999) {
            // if (gp.keyH.enterPressed == true) {
            // gp.gameState = gp.dialogueState;
            // gp.npc[i].speak();
            // }
            // gp.keyH.enterPressed = false;
            gp.gameState = gp.dialogueState;
            gp.npc[i].speak();
        }
    }

    public void draw(Graphics2D g2) {
        // g2.setColor(Color.white);
        // g2.fillRect(x,worldY, gp.tileSize, gp.tileSize);
        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
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
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                break;
        }

        if (invincible == true) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }

        g2.drawImage(image, screenX, screenY, 1 * gp.tileSize, 2 * gp.tileSize, null);

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}
