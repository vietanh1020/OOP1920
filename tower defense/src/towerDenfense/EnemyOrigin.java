
// enemy
package towerDenfense;

import java.awt.*;
import java.io.File;

public class EnemyOrigin extends Rectangle {
    public SoundPlay newEnemy = new SoundPlay(new File("Sound/enemy1.wav"))  ;
    public SoundPlay die = new SoundPlay(new File("Sound/die.wav"))  ;
    public int health = 10000  ; // máu Enemy
    public int healthSpace = 1, healthHeight = 6; // khởi tạo thanh máu địch !!!!!
    public int xC, yC;
    public int mobSize = 52;

    public int mobWalk = 0;
    public int upWard = 0, downWard = 1, right = 2, left = 3;
    public int direction = right;
    public boolean inGame = false;
    public boolean hasUpWard = false;
    public boolean hasDownWard = false;
    public boolean hasRight = false;
    public boolean hasLeft = false;
    public int mobId = Value.mobAir;

    public EnemyOrigin() {

    }

    public void spawnMod(int mobId) {
        for (int y = 0; y < Screen.map.block.length; y++) {
            if (Screen.map.block[y][0].groundId == Value.groundRoad) {
                setBounds(Screen.map.block[y][0].x, Screen.map.block[y][0].y, mobSize, mobSize);
                xC = 0;
                yC = y;
            }
        }
        this.mobId = mobId;
        inGame = true;
        health= 10000;
        direction=right;
        mobWalk = 0 ;
        newEnemy.play();

    }

    public void deleteMob() {
        inGame = false;
        Screen.scores +=1 ;
        Screen.coinage +=10 ;
        die.play();
    }

    public void looseHealth() {
        Screen.health -= 1;
    }

    public int walkFrame = 0, walkSpeed = 10    ;

    public void physic() {
        if (Screen.map.block[yC][xC].airId == Value.airCave) {
            deleteMob();
            looseHealth();
        }

        if (walkFrame >= walkSpeed) {

            if (direction == right) {
                x += 1;
            } else if (direction == upWard) {
                y -= 1;

            } else if (direction == downWard) {
                y += 1;

            } else if (direction == left) {
                x -= 1;
            }

            mobWalk += 1;
            if (mobWalk == Screen.map.blockSize) {
                if (direction == right) {
                    xC += 1;
                    hasRight = true;
                } else if (direction == upWard) {
                    yC -= 1;
                    hasUpWard = true;
                } else if (direction == downWard) {
                    yC += 1;
                    hasDownWard = true;
                } else if (direction == left) {
                    xC -= 1;
                    hasLeft = true;
                }

                if (!hasDownWard) {
                    try {
                        if (Screen.map.block[yC-1 ][xC].groundId == Value.groundRoad) {
                            direction = upWard;
                        }
                    } catch (Exception e) {
                    }
                }
                if (!hasUpWard) {
                    try {
                        if (Screen.map.block[yC+1 ][xC].groundId == Value.groundRoad) {
                            direction = downWard;
                        }

                    } catch (Exception e) {
                    }
                }
                if (!hasLeft) {
                    try {
                        if (Screen.map.block[yC ][xC + 1].groundId == Value.groundRoad) {
                            direction = right;
                        }
                    } catch (Exception e) {
                    }
                }
                if (!hasRight) {
                    try {
                        if (Screen.map.block[yC ][xC-1 ].groundId == Value.groundRoad) {
                            direction = left;
                        }
                    } catch (Exception e) {
                    }
                }
                mobWalk = 0 ;


            }
            if (Screen.map.block[yC][xC].airId == Value.airCave) {
                deleteMob();
                looseHealth();
            }
            hasDownWard = false;
            hasUpWard = false;
            hasRight = false;
            hasLeft = false;

            walkFrame = 0;
        } else {
            walkFrame += 1;
        }


    }
    public void checkDeath(){
        if(this.health <= 0 ){
            this.deleteMob();
        }
    }
    public void loseHealth(int a){
        this.health -=a ;
        checkDeath();
    }

    public boolean isDead() {
        if(inGame){
            return false ;

        } else {
            return true ;
        }
    }


}
