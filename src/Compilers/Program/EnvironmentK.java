/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Compilers.Program;

import main.Models.EditorMapas;
import main.Models.Lib;
import main.Views.TabEjecutar;

/**
 *
 * @author Uziel
 */
public class EnvironmentK {
    
    public boolean isFrontClear(){
            return !Lib.byteBol(EditorMapas.walls[TabEjecutar.Mpanel2.flechax + 1][TabEjecutar.Mpanel2.flechay])[TabEjecutar.Mpanel2.flechad];
    }
    public boolean isLeftClear(){
            return !Lib.byteBol(EditorMapas.walls[TabEjecutar.Mpanel2.flechax + 1][TabEjecutar.Mpanel2.flechay])[(TabEjecutar.Mpanel2.flechad + 3) % 4];
    }
    public boolean isRightClear(){
            return !Lib.byteBol(EditorMapas.walls[TabEjecutar.Mpanel2.flechax + 1][TabEjecutar.Mpanel2.flechay])[(TabEjecutar.Mpanel2.flechad + 1) % 4];
    }
    public boolean isNextToABeeper(){
            return TabEjecutar.Mpanel2.getzumbamap()[TabEjecutar.Mpanel2.flechax + 1][TabEjecutar.Mpanel2.flechay] != 0;
    }
    public boolean isThereAnyBeepersInBag(){
            return TabEjecutar.Mpanel2.z != 0;
    }
    public boolean isFacingNorth(){
            return TabEjecutar.Mpanel2.flechad == 0;
    }
    public boolean isFacingSouth(){
            return TabEjecutar.Mpanel2.flechad == 2;
    }
    public boolean isFacingEast(){
            return TabEjecutar.Mpanel2.flechad == 1;
    }
    public boolean isFacingWest(){
            return TabEjecutar.Mpanel2.flechad == 3;
    }
}
