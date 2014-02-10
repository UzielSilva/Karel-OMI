package main.Models;

import Compilers.Compiler;
import Compilers.Program.EnvironmentK;
import Compilers.Program.Program;
import com.apple.eawt.Application;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import main.Controllers.LisMundo;
import main.Controllers.LisPrograma;
import main.Views.TabMundo;
import main.Views.Ventana2;

/**
 * @author German gonzalez, Uziel Silva
 */
public class Karelotitlan extends javax.swing.JApplet {

    public static boolean Mapedit = false;
    public static boolean Codedit = false;
    public static File world = null;
    public static File code = null;
    public static String[] parsedDoc = null;
    public static Integer[] rows = null;
    public static EnvironmentK env = null;
    public static Program program = null;
    public static Compiler lang = null;

    @Override
    public void init() {
        reset();
        this.add(new Ventana2(false));
    }

    public static void main(String str[]) {
        JFrame f = new JFrame();
        f.setMinimumSize(new Dimension(660, 380));
        f.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        f.setTitle("KAREL OMI por German Gonzalez y Uziel Silva");
        Image image = Toolkit.getDefaultToolkit().getImage(Ventana2.class.getResource("../Logo.png"));
        f.setIconImage(image);
        try {
            Application application = Application.getApplication();
            application.setDockIconImage(image);
        } catch (Exception e) {
        }
        f.addWindowListener(new WindowAdapter(){
	public void windowClosing(WindowEvent we){
            boolean close=false;
            if(Mapedit){
		int eleccion = JOptionPane.showConfirmDialog(null, "Hay cambios sin guardar en el mapa, desea guardar los cambios?");
		if ( eleccion == 1) {
                    close=true;
		}			
                if ( eleccion == 0) {
                    LisMundo.mGuardar();
		}
            }
            if(Codedit){
		int eleccion = JOptionPane.showConfirmDialog(null, "Hay cambios sin guardar en el código, desea guardar los cambios?");
		if ( eleccion == 1) {
                    close=true;
		}			
                if ( eleccion == 0) {
                    LisPrograma.cGuardar();
		}
            }
                if(close || (!Mapedit&&!Codedit))
                System.exit(0);
                      
	}
});
        reset();
        f.add(new Ventana2(true));
        f.pack();
        f.setSize(new Dimension(860, 580));
        f.setVisible(true);
    }

    private static void reset() {
        lang = new Compiler();
        lang.setParam("UsrLang", "ES");
        lang.setParam("ProgLang", "Pascal");
    }
}