package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileUpper {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileUpper(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[2505];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap();
    }

    public void getTileImage() {
        try {

            for (int i = 0; i < 1250; i++) {
                tile[i] = new Tile();
                String fileName;

                if (i < 10) {
                    fileName = String.format("../assets/maps/upper/upper1/tile00%d.png", i);
                } else if (i < 100) {
                    fileName = String.format("../assets/maps/upper/upper1/tile0%d.png", i);
                } else {
                    fileName = String.format("../assets/maps/upper/upper1/tile%d.png", i);
                }

                tile[i].image = ImageIO.read(getClass().getResourceAsStream(fileName));
            }

            for (int i = 1250; i < 2500; i++) {
                tile[i] = new Tile();
                String fileName;

                int local = i - 1250;

                if (local < 10) {
                    fileName = String.format("../assets/maps/upper/upper2/tile00%d.png", local);
                } else if (local < 100) {
                    fileName = String.format("../assets/maps/upper/upper2/tile0%d.png", local);
                } else {
                    fileName = String.format("../assets/maps/upper/upper2/tile%d.png", local);
                }

                tile[i].image = ImageIO.read(getClass().getResourceAsStream(fileName));
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    void loadMap() {
        try {

            InputStream is = getClass().getResourceAsStream("../assets/maps/map01.txt");

            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                // System.out.println("Row" + row);

                String line = br.readLine();

                while (col < gp.maxWorldCol) {
                    // System.out.println(col);
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;

                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;

                }
            }

            br.close();

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldRow < gp.maxWorldRow) {
            while (worldCol < gp.maxWorldCol) {

                int tileNum = mapTileNum[worldCol][worldRow];

                int worldX = worldCol * gp.tileSize;
                int worldY = worldRow * gp.tileSize;
                int screenX = worldX - gp.player.worldX + gp.player.screenX;
                int screenY = worldY - gp.player.worldY + gp.player.screenY;

                if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                        worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                        worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                        worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                    g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
                }

                worldCol++;

            }
            worldCol = 0;

            worldRow++;

        }
    }

}
