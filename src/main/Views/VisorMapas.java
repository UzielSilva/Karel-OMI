package main.Views;

import Compilers.Program.Program;
import javax.swing.JOptionPane;
import main.Models.Karelotitlan;
import main.Models.Lib;
/**
 * @author German gonzalez, Uziel Silva
 */
public class VisorMapas extends Mapas implements Runnable {

    Thread tread = new Thread(this);
    boolean stop=true;
    boolean error=false;
    int line = -1;
    
    public void avanzauno(){
        if(!error){
        Integer i = Karelotitlan.program.nextAction();
        if(i != null){
            switch(i){
                case 6:turnLeft();break;
                case 30:pickBeeper();break;
                case 35:dropBeeper();break;
                case 26:if(!foward()){JOptionPane.showMessageDialog(this,"Intento cruzar una pared","Karel",JOptionPane.ERROR_MESSAGE);error=true;};break;
            }
            repaint();
        }
        Integer j = Karelotitlan.program.getCurrentLine();
        if(j != null){
            TabEjecutar.textPane2.hightlight=true;
            TabEjecutar.textPane2.line = j;
            TabEjecutar.textPane2.repaint();
        }
        }
    }
    public void arranca(){
        TabEjecutar.textPane2.hightlight=true;
        TabEjecutar.textPane2.line=Karelotitlan.program.getCurrentLine();
        TabEjecutar.textPane2.repaint();
    }
    
    public void turnLeft(){
            TabEjecutar.Mpanel2.flechad = (byte) ((TabEjecutar.Mpanel2.flechad + 3) % 4);
    }
    public boolean pickBeeper(){
        
            if (TabEjecutar.Mpanel2.getzumbamap()[TabEjecutar.Mpanel2.flechax + 1][TabEjecutar.Mpanel2.flechay] == 0) {error=true;
                JOptionPane.showMessageDialog(this,"Intento tomar un zumbador donde no había ","Karel",JOptionPane.ERROR_MESSAGE);
                return false;
            } else {
                TabEjecutar.Mpanel2.z++;
                TabEjecutar.Mpanel2.zumbador[TabEjecutar.Mpanel2.flechax + 1][TabEjecutar.Mpanel2.flechay]--;
                TabEjecutar.etzumba.setText(TabEjecutar.Mpanel2.z+"");
                return true;
            }
    }
    public boolean dropBeeper(){
        
            if (TabEjecutar.Mpanel2.z==0) {error=true;
                JOptionPane.showMessageDialog(this,"Intento dejar un zumbador con la mochila vacía","Karel",JOptionPane.ERROR_MESSAGE);
                return false;
            } else {
            if (TabEjecutar.etzumba.getText().equals("INFINITO")) {
                z = 0xffff;
            } else {
                TabEjecutar.Mpanel2.z--;
                TabEjecutar.etzumba.setText(TabEjecutar.Mpanel2.z+"");
            }
                TabEjecutar.Mpanel2.zumbador[TabEjecutar.Mpanel2.flechax + 1][TabEjecutar.Mpanel2.flechay]++;
                return true;
            }
    }
    public boolean foward(){
            switch (TabEjecutar.Mpanel2.flechad) {
                case 0:
                    if (Lib.byteBol(VisorMapas.walls[TabEjecutar.Mpanel2.flechax + 1][TabEjecutar.Mpanel2.flechay])[0]) {
                        return false;
                    } else {
                        TabEjecutar.Mpanel2.flechay++;
                        if(TabEjecutar.Mpanel2.getHeight() - ((TabEjecutar.Mpanel2.flechay - (94 - TabEjecutar.Mpanel2.ScrlV)) * 32) - 2 < 30)
                            TabEjecutar.ebarraV.setValue(TabEjecutar.ebarraV.getValue()-1);
                    }
                    break;
                case 1:
                    if (Lib.byteBol(VisorMapas.walls[TabEjecutar.Mpanel2.flechax + 1][TabEjecutar.Mpanel2.flechay])[1]) {
                        return false;
                    } else {
                        TabEjecutar.Mpanel2.flechax++;
                        if( (TabEjecutar.Mpanel2.flechax - TabEjecutar.Mpanel2.ScrlH) * 32 + 14>TabEjecutar.Mpanel2.getWidth() -30)
                            TabEjecutar.ebarraH.setValue(TabEjecutar.ebarraH.getValue()+1);
                    }
                    break;
                case 2:
                    if (Lib.byteBol(VisorMapas.walls[TabEjecutar.Mpanel2.flechax + 1][TabEjecutar.Mpanel2.flechay])[2]) {
                        return false;
                    } else {
                        TabEjecutar.Mpanel2.flechay--;
                        if( ((TabEjecutar.Mpanel2.flechay - (94 - TabEjecutar.Mpanel2.ScrlV)) * 32) - 2 < 35)
                            TabEjecutar.ebarraV.setValue(TabEjecutar.ebarraV.getValue()+1);
                    }
                    break;
                case 3:
                    if (Lib.byteBol(VisorMapas.walls[TabEjecutar.Mpanel2.flechax + 1][TabEjecutar.Mpanel2.flechay])[3]) {
                        return false;
                    } else {
                        TabEjecutar.Mpanel2.flechax--;
                        if( (TabEjecutar.Mpanel2.flechax - TabEjecutar.Mpanel2.ScrlH) * 32 + 14< 30)
                            TabEjecutar.ebarraH.setValue(TabEjecutar.ebarraH.getValue()-1);
                    }
                    break;
            }
            return true;
            
    }
    public void stop(){
        stop=false;
    }
    public void corre() {
        stop=true;
        if (!tread.isAlive()) {
            avanzauno();
            tread = new Thread(this);
            tread.start();
        }
    }
    public void reset(boolean parser) {
        if(parser){
        Karelotitlan.program = new Program(Karelotitlan.parsedDoc,Karelotitlan.rows,Karelotitlan.env);
        arranca();
        }
        error=false;
        flechax = TabMundo.editor.flechax;
        flechay = TabMundo.editor.flechay;
        flechad = TabMundo.editor.flechad; 
        for(byte x=0;x<102;x++)
        for(byte y=0;y<101;y++)
            zumbador[x][y]=TabMundo.editor.zumbador[x][y];
        try {
            TabEjecutar.etzumba.setText(TabMundo.tzumba.getText());
            if (TabEjecutar.etzumba.getText().equals("INFINITO")) {
                z = 0xffff;
            } else {
                z = Integer.decode(TabEjecutar.etzumba.getText());
            }
        } catch (Exception e) {
            z = 0;
        }
        TabEjecutar.edbug.setText("");
        stop=false;
        
        line = -1;
        repaint();
        System.gc();
    }


    @Override
    public void run() {
        while(Karelotitlan.program.getCurrentLine()!=null &&!error&& stop && VisorCodigo.breaak[Karelotitlan.program.getCurrentLine()-1]==false){
        avanzauno();
        try{
        Lib.pause(Integer.valueOf(0+TabEjecutar.etretar.getText()));
        }catch(Exception e){Lib.pause(3);}
        }
    }
}