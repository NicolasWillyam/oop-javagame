package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_CCung extends SuperObject {
    public OBJ_CCung() {
        name = "CCung";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("../assets/objects/cung.png"));
            objCol = 2;
            objRow = 2;
        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }
}
