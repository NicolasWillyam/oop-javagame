package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_BinhVang extends SuperObject {
    public OBJ_BinhVang() {
        name = "BinhVang";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("../assets/objects/thuoc_vang.png"));
            objCol = 1;
            objRow = 1;
        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }
}
