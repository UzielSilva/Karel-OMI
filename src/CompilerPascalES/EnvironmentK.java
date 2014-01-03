/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CompilerPascalES;

import main.EditorMapas;
import main.Lib;
import main.Ventana2;

/**
 *
 * @author Uziel
 */
public class EnvironmentK {
    
    public boolean isFrontClear(){
            return !Lib.byteBol(EditorMapas.walls[Ventana2.Mpanel2.flechax + 1][Ventana2.Mpanel2.flechay])[Ventana2.Mpanel2.flechad];
    }
    public boolean isLeftClear(){
            return !Lib.byteBol(EditorMapas.walls[Ventana2.Mpanel2.flechax + 1][Ventana2.Mpanel2.flechay])[(Ventana2.Mpanel2.flechad + 3) % 4];
    }
    public boolean isRightClear(){
            return !Lib.byteBol(EditorMapas.walls[Ventana2.Mpanel2.flechax + 1][Ventana2.Mpanel2.flechay])[(Ventana2.Mpanel2.flechad + 1) % 4];
    }
    public boolean isNextToABeeper(){
            return Ventana2.Mpanel2.getzumbamap()[Ventana2.Mpanel2.flechax + 1][Ventana2.Mpanel2.flechay] != 0;
    }
    public boolean isThereAnyBeepersInBag(){
            return Ventana2.Mpanel2.z != 0;
    }
    public boolean isFacingNorth(){
            return Ventana2.Mpanel2.flechad == 0;
    }
    public boolean isFacingSouth(){
            return Ventana2.Mpanel2.flechad == 2;
    }
    public boolean isFacingEast(){
            return Ventana2.Mpanel2.flechad == 1;
    }
    public boolean isFacingWest(){
            return Ventana2.Mpanel2.flechad == 3;
    }
}
