// khởi tạo và vẽ cửa hàng mua tháp

package towerDenfense;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Store {
    public static int shopWidth = 8;
    public static int buttonSize = 32;
    public static int cellSpace = 2;
    public static int iconSize = 20;
    public static int iconSpace = 5;
    public static int itemIn = 4;
    public static int iconTextY = 15;
    public static int heldId = -1;
    public static int realId = -1;
    public static Image[] towerlist = new Image[8];



    public static int[] buttonId = {Value.airTowerLaze1, Value.airTowerLaze2, Value.airTowerLaze3, Value.airTowerLaze4, Value.airTowerLaze5, Value.airTowerLaze6, Value.airTowerLaze1, Value.airTrashCan};
    public static int[] buttonPrice = {5, 10, 20, 50, 75, 75, 0, 0};

    public Rectangle[] button = new Rectangle[shopWidth];
    public Rectangle buttonHealth;
    public Rectangle buttonCoins;
    public boolean holdItem = false;

    public Store() {
        define();

    }

    public void click(int mouseButton) {
        if (mouseButton == 1) {
            System.out.println("ok");


            System.out.println(Screen.mse.x);
            for (int i = 0; i < button.length; i++) {
                if (buttonId[i] != Value.airAir) {
                    if (button[i].contains(Screen.mse)) {
                        if (buttonId[i] == Value.airTrashCan) {
                            holdItem = false;
                        } else {
                            heldId = buttonId[i];
                            realId = i;
                            holdItem = true;

                        }
                    }
                }
            }

            if (holdItem) {
                if (Screen.coinage >= buttonPrice[realId]) {
                    for (int y = 0; y < Screen.map.block.length; y++) {
                        for (int x = 0; x < Screen.map.block.length; x++) {
                            if (Screen.map.block[y][x].contains(Screen.mse)) {
                                if (Screen.map.block[y][x].groundId != Value.groundRoad && Screen.map.block[y][x].airId == Value.airAir) {
                                    Screen.map.block[y][x].airId = heldId;
                                    Screen.coinage -= buttonPrice[realId];
                                }
                            }
                        }
                    }

                }
            }
        }


    }

    public void define() {
        for (int i = 0; i < button.length; i++) {
            button[i] = new Rectangle((Screen.myWidth / 2 - shopWidth * (buttonSize + cellSpace) / 2) + ((buttonSize + cellSpace) * i), 589 + Screen.map.blockSize + cellSpace, buttonSize, buttonSize);

        }
        buttonHealth = new Rectangle(Screen.map.block[0][0].x - 1, button[0].y, iconSize, iconSize);
        buttonCoins = new Rectangle(Screen.map.block[0][0].x - 1, button[0].y + button[0].height - iconSize + 10, iconSize, iconSize);

    }

    public void draw(Graphics g) {
        towerlist[0] = new ImageIcon("res/tower.png").getImage();
        towerlist[1] = new ImageIcon("res/tower2.png").getImage();
        towerlist[2] = new ImageIcon("res/tower3.png").getImage();
        towerlist[3] = new ImageIcon("res/tower4.png").getImage();
        towerlist[4] = new ImageIcon("res/tower5.png").getImage();
        towerlist[5] = new ImageIcon("res/tower6.png").getImage();


        for (int i = 0; i < button.length; i++) {

            if (button[i].contains(Screen.mse)) {
                g.setColor(new Color(0, 0, 255, 150));
                g.fillRect((Screen.myWidth / 2 - shopWidth * (buttonSize + cellSpace) / 2) + ((buttonSize + cellSpace) * i), 598 + Screen.map.blockSize + cellSpace, buttonSize, buttonSize);
            }
            g.drawImage(Screen.tileset_res[0], button[i].x, button[i].y, button[i].width, button[i].height, null);
            if (buttonId[i] != Value.airAir) {

                g.drawImage(towerlist[i], button[i].x, button[i].y, button[i].width, button[i].height, null);

            }
            if (buttonPrice[i] >= 0) {
                g.setColor(Color.black);
                g.setFont(new Font("Courier New", Font.BOLD, 14));
                g.drawString("$" + buttonPrice[i], button[i].x, button[i].y + 30);

            }
        }

        g.setFont(new Font("Courier new", Font.BOLD, 14));
        g.setColor(Color.white);
        g.drawString("" + Screen.health, 871, 65); // hiển thị số mạng
        g.drawString("" + Screen.coinage, 962, 65);   // hiển thị tiền
        g.setColor(Color.yellow);
        //g.setFont(new Font("lam", Font.LAYOUT_NO_PAIRED_CHARS_AT_SCRIPT_SPLIT, 20)); // hiển thị số điểm
        g.drawString("             "+Screen.scores , 1006, 65);

        if (holdItem) {
            g.drawImage(Screen.tileset_air[heldId], Screen.mse.x - (button[0].width - itemIn * 2) / 2 + itemIn, Screen.mse.y - (button[0].width - itemIn * 2) / 2 + itemIn, button[0].width, button[0].height, null);
        }


    }
}

