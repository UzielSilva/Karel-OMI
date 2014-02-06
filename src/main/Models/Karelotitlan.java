package main.Models;

import Compilers.Box;
import Compilers.Program.EnvironmentK;
import Compilers.Program.Program;
import java.awt.Dimension;
import java.io.File;
import main.Views.Ventana2;


/**
 * @author German gonzalez, Uziel Silva
 */
public class Karelotitlan extends javax.swing.JApplet { 
    public static  boolean Mapedit=false;
    public static boolean Codedit=false;
    
    public static File world=null;
    public static File code=null;
   
    public static String[] parsedDoc=null;
    public static Integer[] rows=null;
    public static EnvironmentK env=null;
    public static Program program=null;
    public static Box.Language lang = null;
    
  public void init() {
    initComponents();
  }
    public void initComponents(){
        setMinimumSize(new Dimension(660, 380));
        reset();
        this.add(new Ventana2());
    }
    private void reset(){
  lang = Box.PASCALES;
    }
}