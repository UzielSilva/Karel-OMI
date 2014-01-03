package main;

import java.util.Stack;
import javax.swing.JOptionPane;

/**
 * @author German gonzalez, Uziel Silva
 */
public class VisorMapas extends Mapas implements Runnable {

    Thread tread = new Thread(this);
    boolean stop=true;
    int line = -1;
    
    public void stop(){
        stop=false;
    }
    public void corre() {
        stop=true;
        if (!tread.isAlive()) {
            tread = new Thread(this);
            tread.start();
        }
    }
    public void reset() {
        flechax = Ventana2.Mpanel.flechax;
        flechay = Ventana2.Mpanel.flechay;
        flechad = Ventana2.Mpanel.flechad; 
        for(byte x=0;x<102;x++)
        for(byte y=0;y<101;y++)
            zumbador[x][y]=Ventana2.Mpanel.zumbador[x][y];
        try {
            Ventana2.etzumba.setText(Ventana2.mtzumba.getText());
            if (Ventana2.etzumba.getText().equals("INFINITO")) {
                z = 0xffff;
            } else {
                z = Integer.decode(Ventana2.etzumba.getText());
            }
        } catch (Exception e) {
            z = 0;
        }
        Ventana2.edbug.setText("");
        stop=false;
        
        Ventana2.textPane2.hightlight=false;
        line = -1;
        repaint();
        System.gc();
    }


    @Override
    public void run() {
//        while(line!=-2 && stop){
////        this.avanzauno();
//        this.repaint();
//        try{
//        Lib.pause(Integer.valueOf(0+Ventana2.etretar.getText()));
//        }catch(Exception e){Lib.pause(3);}
//        }
    }
}