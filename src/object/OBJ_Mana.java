package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Mana extends SuperObject {
    GamePanel gp;

    public OBJ_Mana(GamePanel gp) {

        this.gp = gp;

        name = "Mana";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("../assets/objects/mana/tile000.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("../assets/objects/mana/tile001.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("../assets/objects/mana/tile002.png"));
            image4 = ImageIO.read(getClass().getResourceAsStream("../assets/objects/mana/tile003.png"));
            image5 = ImageIO.read(getClass().getResourceAsStream("../assets/objects/mana/tile004.png"));
            image6 = ImageIO.read(getClass().getResourceAsStream("../assets/objects/mana/tile005.png"));

        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        collision = true;
    }
}
