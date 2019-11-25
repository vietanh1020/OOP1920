// tạo bản đồ game

package towerDenfense;

import java.awt.*;

public class map {
    public int worldWidth =15;
    public int worldHeight =28;
    public int blockSize = 45;

    public Block[][] block;

    public map() {
        define();
    }

    public void define() {
        block = new Block[worldWidth][worldHeight];
        for (int y = 0; y < block.length; y++) {
            for (int x = 0; x < block[0].length; x++) {
                //block[y][x] = new Block((Screen.myWidth / 2 - (worldWidth * blockSize) / 2) + x * blockSize, y * blockSize, blockSize, blockSize, Value.groundGrass, Value.airAir);
                block[y][x] = new Block(0 + x * blockSize, y * blockSize, blockSize, blockSize, Value.groundGrass, Value.airAir);

            }

        }
    }

    public void physic() {
        for (int y = 0; y < block.length; y++) {
            for (int x = 0; x < block[0].length; x++) {
                block[y][x].physic();
                //block[y][x].physic1();
            }
        }

    }

    public void draw(Graphics g) {
        for (int y = 0; y < block.length; y++) {
            for (int x = 0; x < block[0].length; x++) {
                block[y][x].draw(g);

            }

        }
        for (int y = 0; y < block.length; y++) {
            for (int x = 0; x < block[0].length; x++) {
                block[y][x].fight(g);
                //block[y][x].fight1(g);
            }

        }
    }
}
