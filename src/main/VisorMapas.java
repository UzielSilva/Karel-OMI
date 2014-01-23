package main;

import Compilers.Program.Program;
import javax.swing.JOptionPane;
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
        Integer i = Ventana2.program.nextAction();
        if(i != null){
            switch(i){
                case 6:turnLeft();break;
                case 30:pickBeeper();break;
                case 35:dropBeeper();break;
                case 26:if(!foward()){JOptionPane.showMessageDialog(this,"Intento cruzar una pared","Karel",JOptionPane.ERROR_MESSAGE);error=true;};break;
            }
            repaint();
        }
        Integer j = Ventana2.program.getCurrentLine();
        if(j != null){
            Ventana2.textPane2.hightlight=true;
            Ventana2.textPane2.line = j;
            Ventana2.textPane2.repaint();
        }
        }
    }
    public void arranca(){
        Ventana2.textPane2.hightlight=true;
        Ventana2.textPane2.line=Ventana2.program.getCurrentLine();
        Ventana2.textPane2.repaint();
    }
    
    public void turnLeft(){
            Ventana2.Mpanel2.flechad = (byte) ((Ventana2.Mpanel2.flechad + 3) % 4);
    }
    public boolean pickBeeper(){
        
            if (Ventana2.Mpanel2.getzumbamap()[Ventana2.Mpanel2.flechax + 1][Ventana2.Mpanel2.flechay] == 0) {error=true;
                JOptionPane.showMessageDialog(this,"Intento tomar un zumbador donde no había ","Karel",JOptionPane.ERROR_MESSAGE);
                return false;
            } else {
                Ventana2.Mpanel2.z++;
                Ventana2.Mpanel2.zumbador[Ventana2.Mpanel2.flechax + 1][Ventana2.Mpanel2.flechay]--;
                Ventana2.etzumba.setText(Ventana2.Mpanel2.z+"");
                return true;
            }
    }
    public boolean dropBeeper(){
        
            if (Ventana2.Mpanel2.z==0) {error=true;
                JOptionPane.showMessageDialog(this,"Intento dejar un zumbador con la mochila vacía","Karel",JOptionPane.ERROR_MESSAGE);
                return false;
            } else {
            if (Ventana2.etzumba.getText().equals("INFINITO")) {
                z = 0xffff;
            } else {
                Ventana2.Mpanel2.z--;
                Ventana2.etzumba.setText(Ventana2.Mpanel2.z+"");
            }
                Ventana2.Mpanel2.zumbador[Ventana2.Mpanel2.flechax + 1][Ventana2.Mpanel2.flechay]++;
                return true;
            }
    }
    public boolean foward(){
            switch (Ventana2.Mpanel2.flechad) {
                case 0:
                    if (Lib.byteBol(VisorMapas.walls[Ventana2.Mpanel2.flechax + 1][Ventana2.Mpanel2.flechay])[0]) {
                        return false;
                    } else {
                        Ventana2.Mpanel2.flechay++;
                        if(Ventana2.Mpanel2.getHeight() - ((Ventana2.Mpanel2.flechay - (94 - Ventana2.Mpanel2.ScrlV)) * 32) - 2 < 30)
                            Ventana2.ebarraV.setValue(Ventana2.ebarraV.getValue()-1);
                    }
                    break;
                case 1:
                    if (Lib.byteBol(VisorMapas.walls[Ventana2.Mpanel2.flechax + 1][Ventana2.Mpanel2.flechay])[1]) {
                        return false;
                    } else {
                        Ventana2.Mpanel2.flechax++;
                        if( (Ventana2.Mpanel2.flechax - Ventana2.Mpanel2.ScrlH) * 32 + 14>Ventana2.Mpanel2.getWidth() -30)
                            Ventana2.ebarraH.setValue(Ventana2.ebarraH.getValue()+1);
                    }
                    break;
                case 2:
                    if (Lib.byteBol(VisorMapas.walls[Ventana2.Mpanel2.flechax + 1][Ventana2.Mpanel2.flechay])[2]) {
                        return false;
                    } else {
                        Ventana2.Mpanel2.flechay--;
                        if( ((Ventana2.Mpanel2.flechay - (94 - Ventana2.Mpanel2.ScrlV)) * 32) - 2 < 35)
                            Ventana2.ebarraV.setValue(Ventana2.ebarraV.getValue()+1);
                    }
                    break;
                case 3:
                    if (Lib.byteBol(VisorMapas.walls[Ventana2.Mpanel2.flechax + 1][Ventana2.Mpanel2.flechay])[3]) {
                        return false;
                    } else {
                        Ventana2.Mpanel2.flechax--;
                        if( (Ventana2.Mpanel2.flechax - Ventana2.Mpanel2.ScrlH) * 32 + 14< 30)
                            Ventana2.ebarraH.setValue(Ventana2.ebarraH.getValue()-1);
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
        Ventana2.program = new Program(Ventana2.parsedDoc,Ventana2.rows,Ventana2.env);
        arranca();
        }
        error=false;
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
        
        line = -1;
        repaint();
        System.gc();
    }


    @Override
    public void run() {
        while(Ventana2.program.getCurrentLine()!=null &&!error&& stop && VisorCodigo.breaak[Ventana2.program.getCurrentLine()-1]==false){
        avanzauno();
        try{
        Lib.pause(Integer.valueOf(0+Ventana2.etretar.getText()));
        }catch(Exception e){Lib.pause(3);}
        }
    }
}