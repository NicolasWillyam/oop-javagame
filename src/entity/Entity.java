package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
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
    public boolean alive = true;
    public boolean dying = false;

    public int actionLockCounter = 0;
    public int dyingCounter = 0;
    public boolean invincible = false;
    public boolean attacking = false;
    boolean hpBarOn = false;
    public int invincibleCounter = 0;
    public int hpBarCounter = 0;
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

    public void damageReaction() {

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
                gp.playSE(6);

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

            if (type == 2 && hpBarOn == true) {
                double oneScale = (double) gp.tileSize / maxLife;
                double hpBarValue = oneScale * life;

                g2.setColor(new Color(35, 35, 35));
                g2.fillRect(screenX - 2, screenY - 17, gp.tileSize + 4, 8);

                g2.setColor(new Color(255, 0, 30, 200));
                g2.fillRect(screenX, screenY - 15, (int) hpBarValue, 4);

                hpBarCounter++;

                if (hpBarCounter > 600) {
                    hpBarCounter = 0;
                    hpBarOn = false;
                }
            }

            if (invincible == true) {
                hpBarOn = true;
                hpBarCounter = 0;
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
            }
            if (dying == true) {
                dyingAnimation(g2);
            }
            g2.drawImage(image, screenX, screenY, gp.tileSize * width, gp.tileSize * height, null);

            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        }

    }

    public void dyingAnimation(Graphics2D g2) {
        dyingCounter++;
        if (dyingCounter <= 5) {

            changeAlpha(g2, 0f);

        }
        if (dyingCounter > 5 && dyingCounter <= 10) {
            changeAlpha(g2, 1f);

        }
        if (dyingCounter > 10 && dyingCounter <= 15) {
            changeAlpha(g2, 0f);

        }
        if (dyingCounter > 15 && dyingCounter <= 20) {
            changeAlpha(g2, 1f);

        }
        if (dyingCounter > 20 && dyingCounter <= 25) {
            changeAlpha(g2, 0f);

        }
        if (dyingCounter > 25 && dyingCounter <= 30) {
            changeAlpha(g2, 1f);

        }
        if (dyingCounter > 30 && dyingCounter <= 35) {
            changeAlpha(g2, 0f);

        }
        if (dyingCounter > 35 && dyingCounter <= 40) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        }
        if (dyingCounter > 40) {
            dying = false;
            alive = false;
        }
    }

    public void changeAlpha(Graphics2D g2, float alphaValue) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));

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
