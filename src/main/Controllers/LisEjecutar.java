/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.Controllers;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import main.Views.TabEjecutar;

/**
 *
 * @author macbook
 */
public class LisEjecutar implements MouseListener,AdjustmentListener{
    int method=-1;
    public LisEjecutar(int i){
        method=i;
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        switch(method){
            case 2:TabEjecutar.Mpanel2.avanzauno();break;
            case 3:TabEjecutar.Mpanel2.reset(true);break;
            case 4:TabEjecutar.Mpanel2.corre();break;
            case 5:TabEjecutar.Mpanel2.stop();break;
        }
    }
    
    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        TabEjecutar.Mpanel2.refresh(TabEjecutar.ebarraH.getValue(), TabEjecutar.ebarraV.getValue());
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}


    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    
}
