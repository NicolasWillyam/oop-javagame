package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Door extends SuperObject {
    public OBJ_Door() {
        name = "Door";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("../assets/objects/altar/altar-grass-01.png"));

        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        collision = true;
    }
}
