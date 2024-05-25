package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Tree extends SuperObject {
    public OBJ_Tree() {
        name = "Tree";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("../assets/objects/birch-trees/birch trees_5.png"));
            objCol = 3;
            objRow = 4;
        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }
}
