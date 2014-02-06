package main.Models;

import main.Views.VisorCodigo;
import java.awt.*;
import java.io.*;
import java.util.Scanner;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import javax.swing.undo.*;
import main.Views.TabPrograma;
import main.Views.Ventana2;

/**
 * @author German gonzalez, Uziel Silva
 */
public class EditorCodigo extends VisorCodigo implements Runnable {

    public  UndoManager undoManager = new UndoManager();
    private Thread d=new Thread(this);
    private Caret caret = getCaret();
    private JViewport viewport ;
    private Point startPoint ;
    private Dimension size ;
    public static String lines[];
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
            content = "";
            viewport = TabPrograma.jScrollPane.getViewport();
            startPoint = viewport.getViewPosition();
            size = viewport.getExtentSize();
            endPoint = new Point(startPoint.x + size.width, startPoint.y + size.height);
            start = this.viewToModel(startPoint);
            end = this.viewToModel(endPoint);
            content = doc.getText(start, end - start).toLowerCase();
        } catch (BadLocationException e) {
        }

        try {
            int pos = caret.getDot();
            int x = (((this.modelToView(pos).x) - 37) / 8) + 1;
            int y = (((this.modelToView(pos).y) - 1) / h) + 1;
            TabPrograma.clpos.setText("linea: " + y + ", columa: " + x);
        } catch (Exception e) {
        }
    }

        public void setBreak(Point m){
        try {
            int pos = caret.getDot();
            if  (m.x<35){
                breaak[(((this.modelToView(pos).y) - 1) / h) ]=!breaak[(((this.modelToView(pos).y) - 1) / h)];
                repaint();
            }
        } catch (BadLocationException ex) {
            Logger.getLogger(EditorCodigo.class.getName()).log(Level.SEVERE, null, ex);
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
            viewport = TabPrograma.jScrollPane.getViewport();
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
    
    public static String adjust(Document doc) {
        try {
            String document = doc.getText(0, doc.getLength()).toLowerCase();
            while (document.contains("( ")) {
                document = document.replace("( ", "(");
            }
                document = document.replace("(", " (");
            while (document.contains(" )")) {
                document = document.replace(" )", ")");
            }
            for (String s : new String[]{"\n", "|", "  "})
                while (document.contains(s)) {
                    document = document.replace(s, " ");
                }
            while (document.contains(";;")) {
                document = document.replace(";;", ";");
            }
            for (String s : new String[]{";", "iniciar-programa", "termina-ejecucion", "inicia-ejecucion", "finalizar-programa"}) {
                document = document.replace(s + " ", s);
                document = document.replace(s, s + "\n");
            }
            for (String s : new String[]{"hacer", "como", "entonces", "veces", "inicio"}) {
                document = document.replace(s + " ", s + "\n");
                document = document.replace(s + "\ninicio ", s + "\ninicio");
                document = document.replace(s + "\ninicio", s + " inicio\n");
            }

            lines = document.split("\n");

            marca(0, 0);
            document = "";
            for (String s : lines)
                document += s + "\n";
            return document;
        } catch (BadLocationException ex) {}
        return null;
    }

    private static String spaces(int e) {
        String str = "";
        for (int i = 0; i < e; i++)
            str += "   ";
        return str;
    }
    private static int submarca(int line, int e) {
        if (lines[line].endsWith("fin sino inicio"))
            lines[line] = spaces(e - 1) + lines[line++];
        lines[line] = spaces(e) + lines[line];
        if (lines[line].endsWith("inicio") || lines[line].endsWith("iniciar-programa") || lines[line].endsWith("inicia-ejecucion")) {
            line = marca(line + 1, e + 1);
        } else if (lines[line].endsWith("veces") || lines[line].endsWith("hacer") || lines[line].endsWith("como")
                || lines[line].endsWith("entonces")) {
            line = submarca(line + 1, e + 1);
        } else line++;
        return line;
    }

    private static int marca(int line, int e) {
        while (line < lines.length) {
            if (lines[line].contains("fin;") || lines[line].contains("termina-ejecucion") || lines[line].contains("finalizar-programa"))
                break;
            line = submarca(line, e);
        }
        if (line < lines.length)
            lines[line] = spaces(e - 1) + lines[line];
        return line + 1;
    }
}