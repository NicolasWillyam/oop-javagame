package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import object.OBJ_Altar;
import object.OBJ_Heart;
import object.SuperObject;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    // Font arial_30;
    Font Minecraft, RainyHearts;
    BufferedImage altarImage, heart_100, heart_75, heart_50, heart_35, heart_10, heart_0;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;

    public String currentDialouge = "";

    public UI(GamePanel gp) {
        this.gp = gp;

        try {
            InputStream is = getClass().getResourceAsStream("../assets/font/Minecraft.ttf");
            Minecraft = Font.createFont(Font.TRUETYPE_FONT, is);
            is = getClass().getResourceAsStream("../assets/font/rainhearts.ttf");
            RainyHearts = Font.createFont(Font.TRUETYPE_FONT, is);

        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        OBJ_Altar altar = new OBJ_Altar();
        altarImage = altar.image;

        SuperObject heart = new OBJ_Heart(gp);
        heart_100 = heart.image;
        heart_75 = heart.image2;
        heart_50 = heart.image3;
        heart_35 = heart.image4;
        heart_10 = heart.image5;
        heart_0 = heart.image6;

    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        g2.setFont(RainyHearts);
        g2.setColor(Color.white);
        g2.drawImage(altarImage, 50, 50, gp.tileSize, gp.tileSize, null);
        g2.drawString("x " + gp.player.hasAltar, 95, 90);

        // MESSAGE
        if (messageOn == true) {
            g2.setFont(g2.getFont().deriveFont(24F));
            g2.drawString(message, gp.tileSize / 2, gp.tileSize * 5);

            messageCounter++;
            if (messageCounter > 120) {
                messageCounter = 0;
                messageOn = false;
            }

        }

        this.g2 = g2;

        g2.setFont(g2.getFont().deriveFont(18F));
        g2.setColor(Color.white);

        // if (gp.gameState == gp.playState) {

        // }
        if (gp.gameState == gp.dialogueState) {
            drawDialogueScreen();
        }

        drawPlayerLife();

    }

    public void drawPlayerLife() {

        int x = gp.tileSize / 4;
        int y = gp.tileSize / 4;

        if (gp.player.life == 6)
            g2.drawImage(heart_100, x, y, gp.tileSize * 3, gp.tileSize, null);
        if (gp.player.life == 5)
            g2.drawImage(heart_75, x, y, gp.tileSize * 3, gp.tileSize, null);
        if (gp.player.life == 4)
            g2.drawImage(heart_50, x, y, gp.tileSize * 3, gp.tileSize, null);
        if (gp.player.life == 3)
            g2.drawImage(heart_35, x, y, gp.tileSize * 3, gp.tileSize, null);
        if (gp.player.life == 2)
            g2.drawImage(heart_10, x, y, gp.tileSize * 3, gp.tileSize, null);
        if (gp.player.life == 1 || gp.player.life == 0)
            g2.drawImage(heart_0, x, y, gp.tileSize * 3, gp.tileSize, null);

    }

    public void drawPauseScreen() {
        String text = "PAUSED";
        int x = getXforCenterText(text);

        int y = gp.screenHeight / 2;

        g2.drawString(text, x, y);
    }

    public void drawDialogueScreen() {

        int width = gp.screenWidth - (gp.tileSize * 2);
        int height = (gp.tileSize * 3);
        int x = gp.tileSize;
        int y = gp.screenHeight - height - gp.tileSize / 2;

        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 18F));
        x += gp.tileSize / 2;
        y += gp.tileSize;

        for (String line : currentDialouge.split("\n")) {
            g2.drawString(line, x, y);
            y += 32;
        }

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 18F));
        g2.setColor(Color.yellow);

        g2.drawString("[Press Enter]", width - 2 * gp.tileSize + 30, y + 20);

    }

    public void drawSubWindow(int x, int y, int width, int height) {

        Color c = new Color(0, 0, 0, 200);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }

    public int getXforCenterText(String text) {

        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }
}
