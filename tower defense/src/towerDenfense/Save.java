package towerDenfense;

import java.io.File;
import java.util.Scanner;

public class Save {
    public void loadSave(File loadPath){
        try{
            Scanner loadScanner = new Scanner(loadPath) ;
            while (loadScanner.hasNext()){
                for(int y=0 ; y<Screen.map.block.length ; y++){
                    for(int x=0  ; x<Screen.map.block[0].length ; x++){
                        Screen.map.block[y][x].groundId = loadScanner.nextInt() ;

                    }
                }
                for(int y=0 ; y<Screen.map.block.length ; y++){
                    for(int x=0  ; x<Screen.map.block[0].length ; x++){
                        Screen.map.block[y][x].airId = loadScanner.nextInt() ;

                    }
                }

            }
            loadScanner.close();
        }catch (Exception e) {}

    }
}
