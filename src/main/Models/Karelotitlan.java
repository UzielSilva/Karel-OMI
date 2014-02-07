package main.Models;

import Compilers.Compiler;
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
    public static Compiler lang = null;
    
  public void init() {
    initComponents();
  }
    public void initComponents(){
        setMinimumSize(new Dimension(660, 380));
        reset();
        this.add(new Ventana2());
    }
    private void reset(){
        lang = new Compiler();
        lang.setParam("UsrLang", "ES");
        lang.setParam("ProgLang", "Pascal");
    }
}