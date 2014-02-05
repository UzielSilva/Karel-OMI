/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import main.Models.EditorCodigo;

/**
 *
 * @author macbook
 */
public class TabPrograma extends Container {

    private JButton nuevo = null;
    private JButton abrir = null;
    private JButton guardar = null;
    private JButton guardarcomo = null;
    private JButton compilar;
    public static JLabel clpos = null;
    public static JScrollPane jScrollPane = null;
    public static EditorCodigo textPane = null;

    public TabPrograma() {
        reset();
        window();
        properties();
    }

    private void reset() {
        nuevo = new JButton("Nuevo");
        abrir = new JButton("Abrir");
        guardar = new JButton("Guardar");
        guardarcomo = new JButton("Guardar Como");
        compilar = new JButton("Compilar");
        textPane = new EditorCodigo();
        clpos = new JLabel("linea 0, columna 0");
        jScrollPane = new JScrollPane();
    }

    private void window() {
        Container c4 = new Container();
        c4.setLayout(new FlowLayout());
        c4.add(nuevo);
        c4.add(abrir);
        c4.add(guardar);
        c4.add(guardarcomo);
        c4.add(compilar);
        Container c44 = new Container();
        c44.setLayout(new BorderLayout());
        c44.add(c4, BorderLayout.WEST);
        c44.add(clpos, BorderLayout.EAST);
        this.setLayout(new BorderLayout());
        this.add(c44, BorderLayout.NORTH);
        textPane.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(jScrollPane, BorderLayout.CENTER);
    }

    private void properties() {

        jScrollPane.setViewportView(textPane);
        jScrollPane.setBorder(null);
        textPane.setBackground(new Color(25, 25, 150));
        textPane.setCaretColor(new Color(0, 255, 255));
        textPane.setSelectionColor(Color.blue);
    }
}
//////   cnuevo.addMouseListener(new java.awt.event.MouseAdapter() {
//            @Override
//            public void mouseReleased(java.awt.event.MouseEvent evt) {
//               cNuevo();
//            Codedit=false;
//            }
//        });
//        cGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
//            @Override
//            public void mouseReleased(java.awt.event.MouseEvent evt) {
//                cGuardar();
//            Codedit=false;
//            }
//        });
//        cAbrir.addMouseListener(new java.awt.event.MouseAdapter() {
//            @Override
//            public void mouseReleased(java.awt.event.MouseEvent evt) {
//                cAbrir();
//            Codedit=false;
//            }
//        });
//        cGuardarcomo.addMouseListener(new java.awt.event.MouseAdapter() {
//            @Override
//            public void mouseReleased(java.awt.event.MouseEvent evt) {
//                cGuardarComo();
//            Codedit=false;
//            }
//        });
//
//        cCompilar.addMouseListener(new java.awt.event.MouseAdapter() {
//            @Override
//            public void mouseReleased(java.awt.event.MouseEvent evt) {
//                cCompila();
//            }
//        });
//        textPane.setFont(new java.awt.Font("Monospaced", 0, 13));
//        textPane.addCaretListener(new javax.swing.event.CaretListener() {
//            @Override
//            public void caretUpdate(javax.swing.event.CaretEvent evt) {
//                textPane.caret();
//            }
//        });
//        textPane.addMouseListener(new MouseAdapter(){
//            public void mouseReleased(MouseEvent Me){
//                
//            if(Me.getButton()==MouseEvent.BUTTON3){
//            popmenu.show(Me.getComponent(), Me.getX(), Me.getY());
//            }
////            if(Me.getButton()==MouseEvent.BUTTON1){
////                textPane.setBreak(Me.getPoint());
////            }
////            }
////            });
////        textPane.addKeyListener(new java.awt.event.KeyAdapter() {
////            @Override
////            public void keyPressed(java.awt.event.KeyEvent evt) {
//////                action = evt.isMetaDown();
//////                if(evt.getKeyCode()==KeyEvent.VK_Y&&action &&textPane.undoManager.canRedo()){
//////                textPane.undoManager.redo();
//////        textPane.search(true);
//////                }
//////                if(evt.getKeyCode()==KeyEvent.VK_Z&&action&&textPane.undoManager.canUndo()){
//////                textPane.undoManager.undo();
//////        textPane.search(true);
//////                }
//////                if(evt.getKeyCode()==KeyEvent.VK_V&&action)
//////                    paste=true;
//////                
//////            Codedit=true;
////            }
////            boolean paste=false;
////            @Override
////            public void keyReleased(java.awt.event.KeyEvent evt) {
//////                System.out.println(evt.getKeyCode());
////                textPane.pressed(paste);
////                paste=false;
////            }
////        });
////        textPane.setFont(new java.awt.Font("Monospaced", 0, 13)); 
//
//    private void cNuevo() {
//        code=null;
//        textPane.setText("iniciar-programa\n   inicia-ejecucion\n      apagate;\n   termina-ejecucion\nfinalizar-programa");
////        textPane.undoManager = new UndoManager();
//        textPane.search(true);
//    }
//    private void cAbrir() {
//        JFileChooser fc = new JFileChooser();
//        int returnVal = fc.showOpenDialog(this);
//        if (returnVal == JFileChooser.APPROVE_OPTION)
//            try {
//                code = fc.getSelectedFile();
//                textPane.open(code);
//            } catch (FileNotFoundException ex) {
//                Logger.getLogger(Ventana2.class.getName()).log(Level.SEVERE, null, ex);
//            }
//    }             
//    private void cGuardarComo() {
//        JFileChooser fc = new JFileChooser();
//        fc.setFileFilter(new TXT());
//        if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
//            code = fc.getSelectedFile();
//            textPane.save(code);
//        }
//    }
//
//    private void cGuardar() {
//        if (code != null) {
//            textPane.save(code);
//        } else {
//            cGuardarComo();
//        }
//    }
//    private void cCompila() {
//        String contents="";
//            try {
//                contents = textPane.doc.getText(0, textPane.doc.getLength()).toLowerCase();
//            } catch (BadLocationException e) {
//            }
//      Reader read=new StringReader(contents);
//        Lexer lexer=new Lexer(read);
//        lexer.setLanguage(lang);
//        Parser parser = new Parser(lexer);
//        parser.setLanguage(lang);
//        try {
//            parser.parse();
//            parsedDoc = parser.parsedDoc.toArray(new String[0]);
//            rows = parser.row.toArray(new Integer[0]);
//            env = new EnvironmentK();
//            for(int i = 0; i < parsedDoc.length; i++)
//                System.out.println(parsedDoc[i]);
//            textPane2.setText(textPane.getText());
//            textPane.search(true);
//            textPane2.search(true);
//            Ventana2.Mpanel2.reset(true);
//            Mpanel2.arranca();
//            JOptionPane.showMessageDialog(this,"Programa Compilado","Karel",JOptionPane.INFORMATION_MESSAGE);
//        } catch (Exception ex) {
//        JOptionPane.showMessageDialog(this,"Error de compilacion: " + ex.getMessage(),"Karel",JOptionPane.ERROR_MESSAGE);
//        }
////        textPane.setText(Compilador.adjust(textPane.doc));
//        
//    }