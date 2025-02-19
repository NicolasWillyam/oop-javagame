package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, yPressed;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (gp.gameState == gp.tileState) {
            if (code == KeyEvent.VK_W || code == 38) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 2;
                }
            }
            if (code == KeyEvent.VK_S || code == 40) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 2) {
                    gp.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.commandNum == 0) {
                    gp.gameState = gp.playState;
                    gp.playMusic(0);

                }
                if (gp.ui.commandNum == 2) {
                    System.exit(0);
                }
            }
        }

        if (gp.gameState == gp.playState) {
            if (code == KeyEvent.VK_W || code == 38) {
                upPressed = true;
            }
            if (code == KeyEvent.VK_S || code == 40) {
                downPressed = true;
            }
            if (code == KeyEvent.VK_A || code == 37) {
                leftPressed = true;
            }
            if (code == KeyEvent.VK_D || code == 39) {
                rightPressed = true;
            }
            if (code == KeyEvent.VK_ENTER) {

                enterPressed = true;
            }

            if (code == KeyEvent.VK_Y) {
                yPressed = true;
            }
            if (code == KeyEvent.VK_M) {

                gp.gameState = gp.openMapState;
            }
            if (code == KeyEvent.VK_B) {

                gp.gameState = gp.openBagState;
            }
            // if (code == KeyEvent.VK_P) {
            // if (gp.gameState == gp.playState) {
            // gp.gameState = gp.pauseState;
            // } else if (gp.gameState == gp.pauseState) {
            // gp.gameState = gp.playState;

            // }
            // }
        }

        else if (gp.gameState == gp.dialogueState) {
            if (code == KeyEvent.VK_ENTER) {
                gp.gameState = gp.playState;

            }
        } else if (gp.gameState == gp.openMapState) {
            if (code == KeyEvent.VK_ESCAPE) {

                gp.gameState = gp.playState;
            }

        } else if (gp.gameState == gp.openBagState) {
            if (code == KeyEvent.VK_ESCAPE) {

                gp.gameState = gp.playState;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W || code == 38) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S || code == 40) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A || code == 37) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D || code == 39) {
            rightPressed = false;
        }
    }

}
