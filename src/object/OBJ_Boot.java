package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Boot extends SuperObject {
    public OBJ_Boot() {
        name = "Boot";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("../assets/objects/merchant/tile025.png"));
            objCol = 1;
            objRow = 1;
        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }
}
