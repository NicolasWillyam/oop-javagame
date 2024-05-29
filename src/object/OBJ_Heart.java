package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Heart extends SuperObject {
    GamePanel gp;

    public OBJ_Heart(GamePanel gp) {

        this.gp = gp;

        name = "Heart";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("../assets/objects/heart/tile000.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("../assets/objects/heart/tile001.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("../assets/objects/heart/tile002.png"));
            image4 = ImageIO.read(getClass().getResourceAsStream("../assets/objects/heart/tile003.png"));
            image5 = ImageIO.read(getClass().getResourceAsStream("../assets/objects/heart/tile004.png"));
            image6 = ImageIO.read(getClass().getResourceAsStream("../assets/objects/heart/tile005.png"));

        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        collision = true;
    }
}
