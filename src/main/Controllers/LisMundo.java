/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.Controllers;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.File;
import javax.swing.JFileChooser;
import main.Models.Karelotitlan;
import main.Views.TabEjecutar;
import main.Views.TabMundo;
import main.Views.Ventana2;

/**
 *
 * @author macbook
 */
public class LisMundo implements MouseListener,MouseWheelListener,AdjustmentListener,KeyListener{
    int method=-1;
    public LisMundo(int i){
        method=i;
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        switch(method){
            case 2:
                Karelotitlan.world=null;
                Karelotitlan.Mapedit=false;
                TabMundo.editor.refresh(TabMundo.barraH.getValue(), TabMundo.barraV.getValue());
                TabEjecutar.Mpanel2.reset(false);
            break;
            case 3:
                mAbrir();
                Karelotitlan.Mapedit=false;
                break;
            case 4:
                mGuardar();
                Karelotitlan.Mapedit=false;
                break;
            case 5:
                mGuardarComo();
                Karelotitlan.Mapedit=false;
                break;
            case 6:
                TabMundo.tzumba.setText("INFINITO");
                TabEjecutar.Mpanel2.reset(false);
                Karelotitlan.Mapedit=true;
                break;
            case 7:
                TabMundo.editor.select(e, TabMundo.barraH.getValue(), TabMundo.barraV.getValue());
                break;
        }
    }
    
    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        TabMundo.editor.refresh(TabMundo.barraH.getValue(), TabMundo.barraV.getValue());
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}


    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
    
        switch(method){
            case 8:
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
            TabMundo.editor.savez(TabMundo.barraH.getValue(), TabMundo.barraV.getValue());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        switch(method){
            case 7:
                TabMundo.barraV.setValue(TabMundo.barraV.getValue()+e.getWheelRotation());
                TabMundo.editor.savez(TabMundo.barraH.getValue(), TabMundo.barraV.getValue());
        }
    }

    
    public void mAbrir() {
        JFileChooser fc = new JFileChooser();
        fc.setFileFilter(new MDO());
        if (fc.showOpenDialog(Ventana2.mundo) == JFileChooser.APPROVE_OPTION) {
            Karelotitlan.world = fc.getSelectedFile();
            TabMundo.editor.open(Karelotitlan.world);
        }
        TabMundo.editor.savez(TabMundo.barraH.getValue(), TabMundo.barraV.getValue());
            TabEjecutar.Mpanel2.reset(false);
    }
    public void mGuardar() {
        if (Karelotitlan.world != null) {
            TabMundo.editor.save();
        } else {
            mGuardarComo();
        }
    }
    public  void mGuardarComo() {
        JFileChooser fc = new JFileChooser();
        fc.setFileFilter(new MDO());
        int returnVal = fc.showSaveDialog(Ventana2.mundo);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File JFC = fc.getSelectedFile();
            String PATH = JFC.getAbsolutePath();
                    if(!(PATH.endsWith(".mdo"))){
                        File temp = new File(PATH+".mdo");
                        JFC=temp;//renombramos el archivo
                    }
            Karelotitlan.world = JFC;
            TabMundo.editor.save();
        }
    }
    
    public class MDO extends javax.swing.filechooser.FileFilter {
        @Override
        public boolean accept(File file) {
            String filename = file.getName().toUpperCase();
            if (file.isDirectory())
                return true;
            return filename.endsWith(".MDO");
        }
        @Override
        public String getDescription() {
            return "*.MDO";
        }
    }
}
