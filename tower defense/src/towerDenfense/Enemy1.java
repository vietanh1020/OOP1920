package towerDenfense;

import javax.swing.*;
import java.awt.*;

public class Enemy1 extends EnemyOrigin {
    Image image;
    //public int health = 20000  ; // máu Enemy
    public int healthSpace = 1, healthHeight = 8; // khởi tạo thanh máu địch !!!!!
    public Enemy1(){
        ImageIcon imageIcon = new ImageIcon("res/enemyxx.jpg");
        image = imageIcon.getImage();
    }
    public void draw(Graphics g) {
        g.drawImage( image, x,y,width/2,height/2,null ) ;
        g.setColor(Color.red);
        g.fillRect(x,y-(healthSpace+healthHeight),health/400,healthHeight);
        g.setColor(Color.BLACK);
        g.drawRect(x,(y-(healthSpace+healthHeight)),health/400 -1,healthHeight -1);

    }
}
