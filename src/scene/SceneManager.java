package scene;

import java.awt.Graphics2D;

import javax.imageio.ImageIO;

import main.GamePanel;

public class SceneManager {
    GamePanel gp;

    Scene[] scene;

    public SceneManager(GamePanel gp) {
        this.gp = gp;
        scene = new Scene[10];
        getSceneImage();
    }

    public void getSceneImage() {
        try {
            scene[0] = new Scene();
            scene[0].image = ImageIO.read(getClass().getResourceAsStream("../assets/scene/home-scene.png"));

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(scene[0].image, 0, 0, gp.screenWidth, gp.screenHeight, null);
    }
}
