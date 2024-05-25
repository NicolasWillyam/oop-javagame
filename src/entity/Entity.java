package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Entity {
    GamePanel gp;
    public int worldX, worldY;
    public int speed;
    public String name;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public BufferedImage up_fight_1, up_fight_2, down_fight_1, down_fight_2, left_fight_1, left_fight_2, right_fight_1,
            right_fight_2;

    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);

    public int width;
    public int height;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea = new Rectangle();
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;

    public int actionLockCounter = 0;
    public boolean invincible = false;
    public boolean attacking = false;
    public int invincibleCounter = 0;
    public int type;

    String dialogues[] = new String[20];
    public int dialogueIndex = 0;

    public int maxLife;
    public int life;

    public Entity(GamePanel gp) {
        this.gp = gp;

    }

    public void setAction() {

    }

    public void speak() {
        if (dialogues[dialogueIndex] == null) {
            dialogueIndex = 0;
        }
        gp.ui.currentDialouge = dialogues[0];
        dialogueIndex++;

    }

    public void update() {
        setAction();

        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if (this.type == 2 && contactPlayer == true) {
            if (gp.player.invincible == false) {
                gp.player.life--;
                gp.player.invincible = true;
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

        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 40) {
                invincible = false;
                invincibleCounter = 0;
            }
        }

    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + 2 * gp.player.screenX > gp.player.worldX - gp.player.screenX &&
                worldX - 2 * gp.player.screenX < gp.player.worldX + gp.player.screenX &&
                worldY + 2 * gp.player.screenY > gp.player.worldY - gp.player.screenY &&
                worldY - 2 * gp.player.screenY < gp.player.worldY + gp.player.screenY) {

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
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
            }
            g2.drawImage(image, screenX, screenY, gp.tileSize * width, gp.tileSize * height, null);

            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        }

    }

    public BufferedImage setup(String imagePath) {
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return image;
    }

}
