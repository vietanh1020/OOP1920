// tạo phím bấm cho game

package towerDenfense;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Buttons implements KeyListener {
    private Screen screen ;
    public Buttons(Screen screen){
        this.screen = screen ;
    }
    public void keyTyped(KeyEvent e) {


    }


    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode() ;
        System.out.println(keyCode);
        if(keyCode == 27){

        }
        if(keyCode == 32){
            if(Screen.StartGame){
                Screen.StartGame = false ;
            }else {
                Screen.StartGame= true ;
                
            }

        }


    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
