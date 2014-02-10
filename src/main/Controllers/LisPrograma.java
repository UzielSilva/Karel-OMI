/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.Controllers;

import Compilers.Program.EnvironmentK;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import java_cup.runtime.lr_parser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;
import main.Models.Karelotitlan;
import main.Views.TabEjecutar;
import main.Views.TabPrograma;
import main.Views.Ventana2;

/**
 *
 * @author macbook
 */
public class LisPrograma implements MouseListener,CaretListener,KeyListener{
    int method=-1;
            boolean paste=false;
            boolean action=false;
    public LisPrograma(int i){
        method=i;
    }
    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) {
     switch(method){
            case 0:
                cNuevo();
                Karelotitlan.Mapedit=false;
            break;
            case 1:
                cAbrir();
                Karelotitlan.Codedit=false;
                break;
            case 2:
                cGuardar();
                Karelotitlan.Codedit=false;
                break;
            case 3:
                cGuardarComo();
                Karelotitlan.Codedit=false;
                break;
            case 4:
                cCompila();
                break;
            case 5:
                if(e.getButton()==MouseEvent.BUTTON3){
                Ventana2.popup.show(e.getComponent(), e.getX(), e.getY());
                }
                if(e.getButton()==MouseEvent.BUTTON1){
                    TabPrograma.textPane.setBreak(e.getPoint());
                }
                break;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) { }

    @Override
    public void caretUpdate(CaretEvent e) { 
                TabPrograma.textPane.caret();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent evt) {
                    action = evt.isMetaDown();
                    if(evt.getKeyCode()==KeyEvent.VK_Y&&action &&TabPrograma.textPane.undoManager.canRedo()){
                    TabPrograma.textPane.undoManager.redo();
            TabPrograma.textPane.search(true);
                    }
                    if(evt.getKeyCode()==KeyEvent.VK_Z&&action&&TabPrograma.textPane.undoManager.canUndo()){
                    TabPrograma.textPane.undoManager.undo();
            TabPrograma.textPane.search(true);
                    }
                    if(evt.getKeyCode()==KeyEvent.VK_V&&action)
                        paste=true;

                Karelotitlan.Codedit=true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
    
//                System.out.println(evt.getKeyCode());
                TabPrograma.textPane.pressed(paste);
                paste=false;
    }
    
    private void cNuevo() {
        Karelotitlan.code=null;
        TabPrograma.textPane.setText("iniciar-programa\n   inicia-ejecucion\n      apagate;\n   termina-ejecucion\nfinalizar-programa");
//        textPane.undoManager = new UndoManager();
        TabPrograma.textPane.search(true);
    }
    private void cAbrir() {
        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(Ventana2.programa);
        if (returnVal == JFileChooser.APPROVE_OPTION)
            try {
                Karelotitlan.code = fc.getSelectedFile();
                TabPrograma.textPane.open(Karelotitlan.code);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Ventana2.class.getName()).log(Level.SEVERE, null, ex);
            }
    }             
    private static void cGuardarComo() {
        JFileChooser fc = new JFileChooser();
        fc.setFileFilter(new TXT());
        if (fc.showSaveDialog(Ventana2.programa) == JFileChooser.APPROVE_OPTION) {
            Karelotitlan.code = fc.getSelectedFile();
            TabPrograma.textPane.save(Karelotitlan.code);
        }
    }

    public static void cGuardar() {
        if (Karelotitlan.code != null) {
            TabPrograma.textPane.save(Karelotitlan.code);
        } else {
            cGuardarComo();
        }
    }
    private void cCompila() {
        String contents="";
            try {
                contents = TabPrograma.textPane.doc.getText(0, TabPrograma.textPane.doc.getLength()).toLowerCase();
            } catch (BadLocationException e) {
            }
      Reader read=new StringReader(contents);
        try {
            lr_parser parser = Karelotitlan.lang.getParser(read);
            parser.parse();
            Karelotitlan.parsedDoc = parser.parsedDoc.toArray(new String[0]);
            Karelotitlan.rows = parser.row.toArray(new Integer[0]);
            Karelotitlan.env = new EnvironmentK();
            for(int i = 0; i < Karelotitlan.parsedDoc.length; i++)
                System.out.println(Karelotitlan.parsedDoc[i]);
            TabEjecutar.textPane2.setText(TabPrograma.textPane.getText());
            TabPrograma.textPane.search(true);
            TabEjecutar.textPane2.search(true);
            TabEjecutar.Mpanel2.reset(true);
            TabEjecutar.Mpanel2.arranca();
            JOptionPane.showMessageDialog(Ventana2.programa,"Programa Compilado","Karel",JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            System.err.println(ex.getStackTrace());
        JOptionPane.showMessageDialog(Ventana2.programa,"Error de compilacion: " + ex.getMessage(),"Karel",JOptionPane.ERROR_MESSAGE);
        }
//        textPane.setText(Compilador.adjust(textPane.doc));
        
    }
    public static class TXT extends javax.swing.filechooser.FileFilter {
        @Override
        public boolean accept(File file) {
            String filename = file.getName().toUpperCase();
            if (file.isDirectory())
                return true;
            return filename.endsWith(".txt");
        }
        @Override
        public String getDescription() {
            return "*.txt";
        }
    }
}
