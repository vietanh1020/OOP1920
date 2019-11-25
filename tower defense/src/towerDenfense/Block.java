package towerDenfense;

import java.awt.*;
import java.io.File;

public class Block extends Rectangle {
    public Rectangle towerSquare ;
    public int  towerSquareSize = 150 ; // phạm vi bắn của Tower
    public int groundId;
    public int airId ;
    public int lostFrame =0 , lostTime =1000 ;

    public int shotMob = -1 ;
    public boolean shoting = false ;

    public static SoundPlay ShoundShot = new SoundPlay(new File("Sound/lazer.wav")) ;

    public Block(int x,int y,int width ,int height , int groundId , int airId){
        setBounds(x,y,width,height);
        towerSquare = new Rectangle(x-towerSquareSize/2,y-towerSquareSize/2 ,width+towerSquareSize,height+towerSquareSize) ;
        this.groundId = groundId ;
        this.airId = airId ;
    }
    public void draw(Graphics g){
        g.drawImage(Screen.tileset_ground[groundId],x,y,width,height,null) ;

        if(airId != Value.airAir){
            g.drawImage(Screen.tileset_air[airId],x,y,width,height,null) ;

        }

    }
    public void physic() {

        if (shotMob!=-1 && towerSquare.intersects(Screen.enemys[shotMob])) {
            shoting = true ;
        }else {
            shoting = false ;
        }
        if (airId == Value.airTowerLaze1 ||airId == Value.airTowerLaze2 ||airId == Value.airTowerLaze3 ||airId == Value.airTowerLaze4 ||airId == Value.airTowerLaze5 ) {
            for (int i = 0; i < Screen.enemys.length; i++) {
                if (Screen.enemys[i].inGame) {
                    if (towerSquare.intersects(Screen.enemys[i])) {
                        shoting = true ;
                        shotMob = i ;
                    }
                }
            }

        }
        if(shoting){
            if(lostFrame >= lostTime ){
                Screen.enemys[shotMob].loseHealth(1);
                lostFrame +=0 ;


            }else {
                lostFrame +=1 ;
            }
            Screen.enemys[shotMob].loseHealth(2);
            if(Screen.enemys[shotMob].isDead()){
                setMoney(shotMob) ;
                shoting = true ;
                shotMob =-1 ;
            }
        }

    }

    public void setMoney(int modId){
        for(int i=0 ; i<Value.deathReward.length ; i++){
            if(i == modId) {
                Screen.coinage += Value.deathReward[i] ;
            }
        }

    }
    public  void setScores(){
        Screen.scores +=1 ;
    }

    public void fight(Graphics g){
        if(Screen.isDebug) {
            /* if (airId == Value.airTowerLaze) {
                g.drawRect(towerSquare.x, towerSquare.y, towerSquare.width, towerSquare.height);
            } */
            if(shoting) {

                    ShoundShot.play();
                    g.setColor(Color.RED);
                    g.drawLine((x + width / 2), (y + height / 2), (Screen.enemys[shotMob].x + Screen.enemys[shotMob].width / 4), Screen.enemys[shotMob].y + Screen.enemys[shotMob].height / 4);

            }
        }
    }
    public void fight1(Graphics g){
        if(Screen.isDebug) {
            /* if (airId == Value.airTowerLaze) {
                g.drawRect(towerSquare.x, towerSquare.y, towerSquare.width, towerSquare.height);
            } */
            if(shoting) {

                ShoundShot.play();
                g.setColor(Color.RED);

            }
        }
    }
}
