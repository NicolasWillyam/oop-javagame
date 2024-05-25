package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Altar extends SuperObject {
    public OBJ_Altar() {
        name = "Altar";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("../assets/objects/merchant/tile000.png"));
            objCol = 1;
            objRow = 1;
        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }
}
