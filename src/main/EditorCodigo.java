package main;

import java.awt.*;
import java.io.*;
import java.util.Scanner;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import javax.swing.undo.*;

/**
 * @author German gonzalez, Uziel Silva
 */
public class EditorCodigo extends VisorCodigo implements Runnable {

    protected UndoManager undoManager = new UndoManager();
    private Thread d=new Thread(this);
    private Caret caret = getCaret();
    private JViewport viewport ;
    private Point startPoint ;
    private Dimension size ;
    private Point endPoint ;
    private int time = -1;
    
    public EditorCodigo() {
        this.getDocument().addUndoableEditListener(
                new UndoableEditListener() {
                    @Override
                    public void undoableEditHappened(UndoableEditEvent e) {
                        if (canremember) {
                            undoManager.addEdit(e.getEdit());
                        }
                    }
                });
    }

    public void open(File f) throws FileNotFoundException {
        Scanner file = new Scanner(f);
        this.setText("");
        String document = "";
        while (file.hasNextLine()) {
            document += file.nextLine() + "\n";
        }
        this.setParagraphAttributes(style[6], false);
        this.setText(document);
        this.search(true);
        undoManager = new UndoManager();
    }

    public void save(File f) {
        try {
            BufferedWriter archivo = Lib.writes(f);
            try {
                archivo.write(doc.getText(0, doc.getLength()));
            } catch (BadLocationException ex) {
                Logger.getLogger(EditorCodigo.class.getName()).log(Level.SEVERE, null, ex);
            }
            archivo.flush();
            archivo.close();
        } catch (IOException ex) {
            Logger.getLogger(EditorCodigo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
boolean paste=false;
    public void pressed(boolean ppaste) {
        caret();
        if(!paste)paste=ppaste;
        time = 10;
        if (!d.isAlive()) {
            d = new Thread(this);
            d.start();
        }
    }

    public void caret() {
        try {
            int pos = caret.getDot();
            int x = (((this.modelToView(pos).x) - 37) / 8) + 1;
            int y = (((this.modelToView(pos).y) - 1) / h) + 1;
            Ventana2.clpos.setText("linea: " + y + ", columa: " + x);
        } catch (Exception e) {
        }
    }

    @Override
    public void run() {
        while (time > 0) {
            time--;
            Lib.pause(15);
        }
        time = -1;
            int pos = caret.getDot();
            viewport = Ventana2.cjScrollPane.getViewport();
        try {
            startPoint=new Point(0,this.modelToView(pos).y);
        } catch (BadLocationException ex) {}
            size = viewport.getExtentSize();
            endPoint = new Point(size.width, startPoint.y +h-1);
            start = this.viewToModel(startPoint);
            end = this.viewToModel(endPoint);
            try {
                content = doc.getText(start, end - start).toLowerCase();
            } catch (BadLocationException ex) {
            }
        search(paste);
        paste=false;
    }

}