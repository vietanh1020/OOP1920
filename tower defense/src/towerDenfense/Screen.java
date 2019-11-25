package towerDenfense;


//tạo màn hình và hiểm thị game

import javax.sound.midi.Instrument;
import javax.sound.midi.Patch;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTreeUI ;
import java.awt.*;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageObserver ;
import java.awt.image.ImageProducer ;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Screen extends JPanel implements Runnable  {
    public  Thread thread= new Thread(this);
    public Random Rd = new Random() ;
    private int wait = 0,wait1=0;


    public static Image[] tileset_ground = new Image[100] ;
    public static Image[] tileset_air = new Image[100] ;
    public static Image[] tileset_res = new Image[100] ;
    public static Image[] tileset_enemy = new Image[100] ;

    public static Image gameOver ;

    public static SoundPlay SoundTrack = new SoundPlay(new File("Sound/soundTrack.wav"));
    public static SoundPlay lose = new SoundPlay(new File("Sound/lose.wav")) ;

    //public static Media startMedia = new Media("res/Last Hope - Tower Defense - Launch Trailer.mp4") ;

    public static Image health_coins ;

    public static int coinage = 100 , health = 10 ,
            scores = 0 ;

    public static Boolean isFirst  =  true ;
    public static Boolean isDebug = true ;


    public static Point mse = new Point(0,0) ;

    public static int myWidth , myHeight  ;
    public static map map;
    public static Save save ;
    public static Store store ;

    public static boolean StartGame = true ;
    public static Enemy[] enemys = new Enemy[10] ;


    public Screen(Frame frame){
        frame.addMouseListener(new KeyHandle());
        frame.addMouseMotionListener(new KeyHandle());
        frame.addKeyListener(new Buttons(this));

        thread.start();

    }
    //public void Menu(Graphics graphics){
      //  this.startMedia.start()

    //}

    public void define(){

        map = new map() ;
        save = new Save() ;
        store = new Store() ;


        for (int i=0 ; i<tileset_ground.length ; i++) {
            tileset_ground[i] = new ImageIcon("res/title_ground.png").getImage() ;
        tileset_ground[i] = createImage(new FilteredImageSource(tileset_ground[i].getSource(),new CropImageFilter(0,26*i,26,26))) ;

        tileset_ground[0] = new ImageIcon("res/co.jpg").getImage() ;
        tileset_ground[1] = new ImageIcon("res/duong.jpg").getImage() ;
        tileset_ground[2] = new ImageIcon("res/flower.png").getImage() ;
        tileset_ground[3] = new ImageIcon("res/nuoc.png").getImage() ;
        tileset_ground[4] = new ImageIcon("res/sen.png").getImage() ;
        tileset_ground[5] = new ImageIcon("res/hoasen.png").getImage() ;
        tileset_ground[6] = new ImageIcon("res/cay.png").getImage() ;
    }

        tileset_res[0]=new ImageIcon("res/cell.png").getImage() ;
        tileset_res[1]= new ImageIcon("res/heart.png").getImage() ;
        tileset_res[2]= new ImageIcon("res/coins.png").getImage() ;
        tileset_air[2]= new ImageIcon("res/tower.png").getImage() ;
        tileset_air[3]= new ImageIcon("res/tower1.png").getImage() ;
        tileset_air[4]= new ImageIcon("res/tower2.png").getImage() ;
        tileset_air[5]= new ImageIcon("res/tower3.png").getImage() ;
        tileset_air[6]= new ImageIcon("res/tower4.png").getImage() ;

        tileset_enemy[0] = new ImageIcon("res/Enemy.png").getImage();






        save.loadSave(new File("save/mission1.ulixava"));
        for(int i=0 ; i< enemys.length ; i++){
            enemys[i] = new Enemy() ;

            //enemys[i].spawnMod(0);
        }


    }
    public void paintComponent(Graphics g){

            super.paintComponent(g);



            if (isFirst && health > 0) {
                myWidth = getWidth();
                myHeight = getHeight();
                this.define();
                isFirst = false;
            }


            //g.setColor(new Color(150,150,150));
            //g.fillRect(0,0,getWidth(),getHeight());
            g.setColor(new Color(255, 255, 255));
            g.drawLine(map.block[0][0].x - 1, 0, map.block[0][0].x - 1, map.block[8][0].y + map.blockSize - 1);
            map.draw(g);


            for (int i = 0; i < enemys.length; i++) {
                if (enemys[i].inGame) {
                    enemys[i].draw(g);

                }

            }


            health_coins = new ImageIcon("res/health_coins_scorer.png").getImage();
            g.drawImage(health_coins, 806, 26, null);
            store.draw(g);


            if (health < 1) {

                gameOver = new ImageIcon("res/gameOver.png").getImage();
                g.drawImage(gameOver, 0, 0, myWidth, myHeight, null);
                thread.stop();
                SoundTrack.stop();
                lose.play();


            }
        }



    public static int fpsFrame = 1000;
    public int spawnTime =100 , spawnFrame = 0 +Rd.nextInt(50) ;

    public void enemySpawner(int n){
        if(spawnFrame  >= spawnTime){
            for(int i=0 ; i<enemys.length ; i++){
                if(!enemys[i].inGame){
                    enemys[i].spawnMod(Value.modGreeny);
                    break;
                }
            }
            spawnFrame = 0 ;
        }
        else {
            spawnFrame += n ;
        }
    }


    public void run() {




        this.define();
        this.SoundTrack.play();


        while (true) {
            if(!StartGame ){
                thread.stop();
                this.SoundTrack.close();

            } else{


            }
            if (isFirst) {
                map.physic();
            }
            if(wait>20) {
                this.enemySpawner(Rd.nextInt(5));
                wait=0;
            }
            else wait++;

            map.physic();
            //map.physic1();

            for (int i = 0; i < enemys.length; i++) {
                if (enemys[i].inGame) {

                    enemys[i].physic();
                }
            }

            repaint();


                try {
                    thread.sleep(1);

                } catch (Exception e) { }
        }

    }


}



