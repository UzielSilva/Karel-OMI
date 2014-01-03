/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CompilerPascalES;

import main.Lib;
import main.Ventana2;

/**
 *
 * @author uziel
 */
public class Program {
    
    private String[] CommandList;
    
    private Integer[] LineList;
    
    private Integer Counter;
    
    private Integer Parameter;
    
    public Program(String[] s, Integer[] i){
        
        LineList = i;
        CommandList = s; 
        Counter = LineList[0];
    }
    
    public Program(String[] s){
        
        CommandList = s;
        Counter = LineList[0];
        
    }
    
    
    
    
}

interface EnvironmentK {
    
    public boolean isfrontClear(){
            return !Lib.byteBol(walls[x + 1][y])[d];
    }
    public boolean isLeftClear(){
            return !Lib.byteBol(walls[x + 1][y])[(d + 3) % 4];
    }
    public boolean isRightClear(){
            return !Lib.byteBol(walls[x + 1][y])[(d + 1) % 4];
    }
    public boolean isNextToABeeper(){
            return Ventana2.Mpanel2.getzumbamap()[x + 1][y] != 0;
    }
    public boolean isThereAnyBeepersInBag(){
        Ventana2.Mpanel2.z != 0;
    }
    public boolean isFacingNorth(){
            return d == 0;
    }
    public boolean isFacingSouth(){
            return d == 2;
    }
    public boolean isFacingEast(){
            return d == 1;
    }
    public boolean isFacingWest(){
            return d == 3;
    }
}
