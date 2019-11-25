package towerDenfense;

import java.awt.*;

public class Enemy extends EnemyOrigin {
    //public int health = 10000  ; // máu Enemy
    public int healthSpace = 1, healthHeight = 6; // khởi tạo thanh máu địch !!!!!
    public void draw(Graphics g) {
        g.drawImage(Screen.tileset_enemy[mobId] , x,y,width,height,null ) ;
        g.setColor(Color.red);
        g.fillRect(x,y-(healthSpace+healthHeight),health/400,healthHeight);
        g.setColor(Color.BLACK);
        g.drawRect(x,(y-(healthSpace+healthHeight)),health/400 -1,healthHeight -1);

    }
}
