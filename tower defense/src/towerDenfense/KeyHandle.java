// tao cái cử chỉ chuột
package towerDenfense;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class KeyHandle implements MouseMotionListener , MouseListener {

    public void mouseClicked(MouseEvent e) {

    }


    public void mousePressed(MouseEvent e) {
        Screen.store.click(e.getButton());


    }


    public void mouseReleased(MouseEvent e) {

    }


    public void mouseEntered(MouseEvent e) {

    }


    public void mouseExited(MouseEvent e){

    }


    public void mouseDragged(MouseEvent e) {
        Screen.mse = new Point(e.getX() -8 ,e.getY() -31) ;

    }


    public void mouseMoved(MouseEvent e) {
        Screen.mse = new Point(e.getX() -8 ,e.getY() -31) ;

    }
}
