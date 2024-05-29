package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_KiemNuoc extends SuperObject {
    public OBJ_KiemNuoc() {
        name = "KiemNuoc";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("../assets/objects/kiem_nuoc.png"));
            objCol = 1;
            objRow = 1;
        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }
}
