package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Knight extends SuperObject {
    public OBJ_Knight() {
        name = "Knight";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("../assets/objects/knight/tile008.png"));
            objCol = 1;
            objRow = 1;
        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }
}
