package main;

import java.awt.*;
import java.awt.datatransfer.*;
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

    private SimpleAttributeSet Attrib = new SimpleAttributeSet();
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
                archivo.write(doc.getText(0, doc.getLength()).replace("|", " "));
            } catch (BadLocationException ex) {
                Logger.getLogger(EditorCodigo.class.getName()).log(Level.SEVERE, null, ex);
            }
            archivo.flush();
            archivo.close();
        } catch (IOException ex) {
            Logger.getLogger(EditorCodigo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void pressed(boolean estaPresionado, boolean full) {
        caret();
        time = 15;
        if (!d.isAlive()) {
            d = new Thread(this);
            d.start();
        }
        if (estaPresionado == true) {
            if (full) {
                start = 0;
                end = doc.getLength();
            }

            int mark = caret.getMark() - start;
            String espacio = tabula(mark, content);
            mark += 1 + start;
            try {
                doc.insertString(mark - 1, espacio, Attrib);
            } catch (BadLocationException ex) {
                Logger.getLogger(EditorCodigo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void caret() {
        try {
            content = "";
            viewport = Ventana2.cjScrollPane.getViewport();
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
            Ventana2.clpos.setText("linea: " + y + ", columa: " + x);
        } catch (Exception e) {
        }
    }

    private String tabula(int posW, String content) {
        int ultimaLinea = espacios(posW, content, 2);
        int espaciosVacios = 0;
        String espacio = "";
        while ((" " + content + " ").charAt(ultimaLinea + 2 + espaciosVacios) == ' '
                || (" " + content + " ").charAt(ultimaLinea + 2 + espaciosVacios) == '|') {
            espaciosVacios += 1;
            espacio += " ";

        }
        return espacio;
    }

    private int espacios(int posW, String content, int offset) {
        int current = posW - offset;
        while (current >= 0 && content.charAt(current) != '\n') {
            current--;
        }
        return current;
    }


    public void tcopy() {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        TransferHandler transferHandler = this.getTransferHandler();
        transferHandler.exportToClipboard(this, clipboard, TransferHandler.COPY);
    }

    public void tpaste() {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        TransferHandler transferHandler = this.getTransferHandler();
        transferHandler.importData(this, clipboard.getContents(null));
        try {
            if (((String) clipboard.getContents(null).getTransferData(DataFlavor.stringFlavor)).split("\n").length > 18) {
                search(true);
            }
        } catch (UnsupportedFlavorException ex) {
            Logger.getLogger(EditorCodigo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EditorCodigo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        while (time > 0) {
            time--;
            Lib.pause(40);
        }
        time = -1;
        search(false);
    }

}