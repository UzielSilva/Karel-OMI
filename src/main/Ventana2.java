package main;

import Compilers.Box;
import Compilers.Program.EnvironmentK;
import Compilers.Pascal.Lexer;
import Compilers.Pascal.Parser;
import Compilers.Program.Program;
import com.apple.eawt.Application;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.BadLocationException;
import javax.swing.undo.UndoManager;

/**
 * @author German Gonzalez
 */
public class Ventana2 extends javax.swing.JApplet {

    boolean action = false;
    private boolean Mapedit=false;
    private boolean Codedit=false;
    
    public static File world=null;
    public static File code=null;
   
    public static String[] parsedDoc=null;
    public static Integer[] rows=null;
    public static EnvironmentK env=null;
    public static Program program=null;
    public static Box.Language lang = null;
    
    public static EditorMapas Mpanel =null;
    public static VisorMapas Mpanel2=null;
    public static EditorCodigo textPane =null;
    public static VisorCodigo textPane2=null; 
    private JPopupMenu popmenu=null;
  private JMenuItem menuItem=null;
    private JButton mnuevo=null;
    private JButton cnuevo=null;
    private JButton mAbrir=null;
    private JButton cAbrir=null;
    private JButton mGuardar=null;
    private JButton cGuardar=null;
    private JButton mGuardarcomo=null;
    private JButton cGuardarcomo=null;
    private JButton mInfinitos=null;
    private JButton cCompilar;
    private JButton eadelante;
    private JButton einiciar;
    private JButton ecorrer;
    private JButton edetener;
    public static JScrollBar mbarraH=null;
    public static JScrollBar ebarraH=null;
    public static JScrollBar mbarraV=null;
    public static JScrollBar ebarraV=null;
    private JLabel lzumba=null;
    private JLabel lzumba2=null;
    public static JLabel clpos=null;
    private JLabel elejecu=null;
    private JSplitPane eSplitPane1 =null;
    private JSplitPane eSplitPane2=null;
    public static JTextField mtzumba=null;
    public static JTextField mtzamba=null;
    public static JTextField etzumba=null;
    public static JTextField etretar=null;
    public static JTextArea edbug=null;
    public static JTabbedPane tabpanel=null;
    public static JScrollPane cjScrollPane=null;
    public JScrollPane ejScrollPane2=null;
    @Override
  public void init() {
    initComponents();
  }
    public void initComponents(){
        
        
   
  lang = Box.ES;
    
     Mpanel= new EditorMapas();
    Mpanel2=new VisorMapas();
    textPane = new EditorCodigo();
  textPane2= new VisorCodigo(); 
    popmenu=new JPopupMenu();
     mnuevo=new JButton("Nuevo");
     cnuevo=new JButton("Nuevo");
    mAbrir=new JButton("Abrir");
 cAbrir=new JButton("Abrir");
    mGuardar=new JButton("Guardar");
    cGuardar=new JButton("Guardar");
  mGuardarcomo=new JButton("Guardar Como");
  cGuardarcomo=new JButton("Guardar Como");
     mInfinitos=new JButton("Infinitos");
   cCompilar=new JButton("Compilar");
    eadelante=new JButton("Adelante");
   einiciar=new JButton("Iniciar");
    ecorrer=new JButton("Correr");
     edetener=new JButton("Detener");
    mbarraH= new JScrollBar(JScrollBar.HORIZONTAL,0,5,0,87);
   ebarraH= new JScrollBar(JScrollBar.HORIZONTAL,0,5,0,96);
    mbarraV= new JScrollBar(JScrollBar.VERTICAL,95,10,0,105);
 ebarraV= new JScrollBar(JScrollBar.VERTICAL,95,10,0,105);
    lzumba=new JLabel("Zumbadores en la mochila");
    lzumba2=new JLabel("Zumbadores en la mochila");
    clpos=new JLabel("linea 0, columna 0");
    elejecu=new JLabel("Retardo de ejecucion (ms)");
     eSplitPane1 = new JSplitPane();
     eSplitPane2= new JSplitPane();
    mtzumba=new JTextField("0");
    mtzamba=new JTextField("0");
    etzumba=new JTextField("0");
 etretar=new JTextField("100");
   edbug=new JTextArea();
   tabpanel=new JTabbedPane();
    cjScrollPane= new JScrollPane();
    ejScrollPane2= new JScrollPane(textPane2);
        
        
        
        
        
        
        
        setMinimumSize(new Dimension(660, 380));
        cjScrollPane.setViewportView(textPane);
        ejScrollPane2.setViewportView(textPane2);
        cjScrollPane.setBorder(null);
        ejScrollPane2.setBorder(null);
       textPane.setBackground(new Color(25,25,150));
        textPane.setCaretColor(new Color(0, 255, 255));
        textPane2.setBackground(new Color(25,25,150));
        textPane.setSelectionColor(Color.blue);
        textPane2.setSelectionColor(Color.blue);
        mtzumba.setDragEnabled(false); 
        tabpanel.setFocusable(false);
        textPane2.setEditable(false);
        edbug.setEditable(false);
        etzumba.setEditable(false); 
        Mpanel.setLayout(null);
        Mpanel.setBackground(Color.white);
        Mpanel2.setBackground(Color.white);
        Mpanel.add(mtzamba);
       mtzamba.setPreferredSize(new Dimension(34,28));
        mtzamba.setHorizontalAlignment(JTextField.CENTER);
        mtzamba.setVisible(false);
        JPanel c=new JPanel();
        c.setLayout(new BorderLayout());
        c.add(mbarraH,BorderLayout.SOUTH);
        c.add(mbarraV,BorderLayout.EAST);
        c.add(Mpanel,BorderLayout.CENTER);
        c.setBorder(BorderFactory.createLineBorder(Color.black));
        Container c2=new Container();
        c2.setLayout(new GridLayout(2,2,2,2));
        c2.setPreferredSize(new Dimension(210,70));
        c2.add(mnuevo);
        c2.add(mAbrir);
        c2.add(mGuardar);
        c2.add(mGuardarcomo);

        Container c222=new Container();
        c222.setLayout(new GridLayout(2,3,2,2));
        c222.setPreferredSize(new Dimension(360,65));
        c222.add(lzumba);
        c222.add(mtzumba);
        JPanel pp = new JPanel();
        pp.setVisible(false);
        c222.add(pp);
        c222.add(mInfinitos);
        Container c22=new Container();
        c22.setLayout(new FlowLayout());
        c22.add(c2);
        c22.add(c222);
        Container c21=new Container();
        c21.setLayout(new BorderLayout());
        c21.add(c22,BorderLayout.WEST);
        Container c3=new Container();
        c3.setLayout(new BorderLayout());
        c3.add(c21,BorderLayout.NORTH);
        c3.add(c,BorderLayout.CENTER);
                    tabpanel.addTab("Mundo", c3);
        Container c4=new Container();
        c4.setLayout(new FlowLayout());
        c4.add(cnuevo);
        c4.add(cAbrir);
        c4.add(cGuardar);
        c4.add(cGuardarcomo);
        c4.add(cCompilar);
        Container c44=new Container();
        c44.setLayout(new BorderLayout());
        c44.add(c4,BorderLayout.WEST);
        c44.add(clpos,BorderLayout.EAST);
        Container c5=new Container();
        c5.setLayout(new BorderLayout());
        c5.add(c44,BorderLayout.NORTH);
        textPane.setBorder(BorderFactory.createLineBorder(Color.black));
        c5.add(cjScrollPane,BorderLayout.CENTER);
                    tabpanel.addTab("Programa", c5);

        JPanel cc=new JPanel();
        cc.setLayout(new BorderLayout());
        cc.add(ebarraH,BorderLayout.SOUTH);
        cc.add(ebarraV,BorderLayout.EAST);
        cc.add(Mpanel2,BorderLayout.CENTER);
        cc.setBorder(BorderFactory.createLineBorder(Color.black));
        Container cc2=new Container();
        cc2.setLayout(new GridLayout(2,2,2,2));
        cc2.setPreferredSize(new Dimension(210,70));
        cc2.add(eadelante);
        cc2.add(edetener);
        cc2.add(ecorrer);
        cc2.add(einiciar);

        Container cc222=new Container();
        cc222.setLayout(new GridLayout(2,3,2,2));
        cc222.setPreferredSize(new Dimension(360,65));
        cc222.add(lzumba2);
        cc222.add(etzumba);
        cc222.add(elejecu);
        cc222.add(etretar);
        Container cc22=new Container();
        cc22.setLayout(new FlowLayout());
        cc22.add(cc2);
        cc22.add(cc222);
        Container cc21=new Container();
        cc21.setLayout(new BorderLayout());
        cc21.add(cc22,BorderLayout.WEST);
        menuItem = new JMenuItem("Undo");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,
                                KeyEvent.META_MASK));
        popmenu.add(menuItem);
        menuItem.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            textPane.undoManager.undo();textPane.search(true);
            Codedit=true;
        }
        });
        menuItem = new JMenuItem("Redo");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y,
                                KeyEvent.META_MASK));
        popmenu.add(menuItem);
        menuItem.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            textPane.undoManager.redo();textPane.search(true);
            Codedit=true;
        }
        });
                popmenu.addSeparator();
        menuItem = new JMenuItem("Cut");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
                                KeyEvent.META_MASK));
        popmenu.add(menuItem);
        menuItem.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            textPane.cut();
            Codedit=true;
        }
        });
        menuItem = new JMenuItem("Copy");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
                                KeyEvent.META_MASK));
        popmenu.add(menuItem);
        menuItem.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            textPane.copy();
        }
        });
        menuItem = new JMenuItem("Paste");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
                                KeyEvent.META_MASK));
        popmenu.add(menuItem);
        menuItem.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            textPane.paste();
        textPane.search(true);
            Codedit=true;
        }
        });
                popmenu.addSeparator();
        menuItem = new JMenuItem("Select All");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
                                KeyEvent.META_MASK));
        popmenu.add(menuItem);
        menuItem.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            textPane.selectAll();
        }
        });
                    popmenu.addSeparator();
        menuItem = new JMenuItem("Dar Formato");
        popmenu.add(menuItem);
        menuItem.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            textPane.setText(textPane.adjust(textPane.doc));
            textPane.search(true);
            Codedit=true;
        }
        });
        eSplitPane2= new JSplitPane(JSplitPane.VERTICAL_SPLIT,ejScrollPane2, edbug);
        eSplitPane2.setDividerLocation(165);
        eSplitPane1= new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,eSplitPane2, cc);
        eSplitPane1.setDividerLocation(250);
        Container cc3=new Container();
        cc3.setLayout(new BorderLayout());
        cc3.add(cc21,BorderLayout.NORTH);
        cc3.add(eSplitPane1,BorderLayout.CENTER);
                    tabpanel.addTab("Ejecutar", cc3);
        this.add(tabpanel);
               Mpanel.reset(mbarraH.getValue(), mbarraV.getValue());
        mnuevo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent evt) {
               world=null;
            Mapedit=false;
               Mpanel.reset(mbarraH.getValue(), mbarraV.getValue());
            Ventana2.Mpanel2.reset(false);
            }
        });
        mGuardar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent evt) {
                mGuardar();
            Mapedit=false;
            }
        });
        mAbrir.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent evt) {
                mAbrir();
            Mapedit=false;
            }
        });
        mGuardarcomo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent evt) {
                mGuardarComo();
            Mapedit=false;
            }
        });
        Mpanel.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                mbarraV.setValue(mbarraV.getValue()+e.getWheelRotation());
                Mpanel.savez(mbarraH.getValue(), mbarraV.getValue());
            }
        });
        mInfinitos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent evt) {
                mtzumba.setText("INFINITO");
                Mpanel2.reset(false);
            Mapedit=true;
            }
        });
        mbarraH.addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent evt) {
                Mpanel.savez(mbarraH.getValue(), mbarraV.getValue());
            }
        });
        mbarraV.addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent evt) {
                Mpanel.savez(mbarraH.getValue(), mbarraV.getValue());
            }
        });
        ebarraH.addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent evt) {
                Mpanel2.refresh(ebarraH.getValue(), ebarraV.getValue());
            }
        });
        ebarraV.addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent evt) {
                Mpanel2.refresh(ebarraH.getValue(), ebarraV.getValue());
            }
        });
        Mpanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent evt) {
                Mpanel.select(evt, mbarraH.getValue(), mbarraV.getValue());
            }
        });
        mtzamba.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                mtzamba(evt);
            }
        });
        cnuevo.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
               cNuevo();
            Codedit=false;
            }
        });
        cGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cGuardar();
            Codedit=false;
            }
        });
        cAbrir.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cAbrir();
            Codedit=false;
            }
        });
        cGuardarcomo.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cGuardarComo();
            Codedit=false;
            }
        });

        cCompilar.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cCompila();
            }
        });
        textPane.setFont(new java.awt.Font("Monospaced", 0, 13));
        textPane.addCaretListener(new javax.swing.event.CaretListener() {
            @Override
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                textPane.caret();
            }
        });
        textPane.addMouseListener(new MouseAdapter(){
            public void mouseReleased(MouseEvent Me){
                
            if(Me.getButton()==MouseEvent.BUTTON3){
            popmenu.show(Me.getComponent(), Me.getX(), Me.getY());
            }
            if(Me.getButton()==MouseEvent.BUTTON1){
                textPane.setBreak(Me.getPoint());
            }
            }
            });
        textPane.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                action = evt.isMetaDown();
                if(evt.getKeyCode()==KeyEvent.VK_Y&&action &&textPane.undoManager.canRedo()){
                textPane.undoManager.redo();
        textPane.search(true);
                }
                if(evt.getKeyCode()==KeyEvent.VK_Z&&action&&textPane.undoManager.canUndo()){
                textPane.undoManager.undo();
        textPane.search(true);
                }
                if(evt.getKeyCode()==KeyEvent.VK_V&&action)
                    paste=true;
                
            Codedit=true;
            }
            boolean paste=false;
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
//                System.out.println(evt.getKeyCode());
                textPane.pressed(paste);
                paste=false;
            }
        });
        textPane.setFont(new java.awt.Font("Monospaced", 0, 13)); 
        textPane2.setFont(new java.awt.Font("Monospaced", 0, 13)); 
        eadelante.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Mpanel2.avanzauno();
            }
        });
        einiciar.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Mpanel2.reset(true);
            }
        });
        ecorrer.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Mpanel2.corre();
            }
        });
        edetener.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Mpanel2.stop();
            }
        });
        
    }
    private void mtzamba(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER)
            Mpanel.savez(mbarraH.getValue(), mbarraV.getValue());
    }
    private void mAbrir() {
        JFileChooser fc = new JFileChooser();
        fc.setFileFilter(new MDO());
        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            world = fc.getSelectedFile();
            Mpanel.open(world);
        }
        Mpanel.savez(mbarraH.getValue(), mbarraV.getValue());
            Ventana2.Mpanel2.reset(false);
    }
    private void mGuardar() {
        if (world != null) {
            Mpanel.save();
        } else {
            mGuardarComo();
        }
    }
    private void mGuardarComo() {
        JFileChooser fc = new JFileChooser();
        fc.setFileFilter(new MDO());
        int returnVal = fc.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File JFC = fc.getSelectedFile();
            String PATH = JFC.getAbsolutePath();
                    if(!(PATH.endsWith(".mdo"))){
                        File temp = new File(PATH+".mdo");
                        JFC=temp;//renombramos el archivo
                    }
            world = JFC;
            Mpanel.save();
        }
    }
    private void cNuevo() {
        code=null;
        textPane.setText("iniciar-programa\n   inicia-ejecucion\n      apagate;\n   termina-ejecucion\nfinalizar-programa");
        textPane.undoManager = new UndoManager();
        textPane.search(true);
    }
    private void cAbrir() {
        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION)
            try {
                code = fc.getSelectedFile();
                textPane.open(code);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Ventana2.class.getName()).log(Level.SEVERE, null, ex);
            }
    }             
    private void cGuardarComo() {
        JFileChooser fc = new JFileChooser();
        fc.setFileFilter(new TXT());
        if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            code = fc.getSelectedFile();
            textPane.save(code);
        }
    }

    private void cGuardar() {
        if (code != null) {
            textPane.save(code);
        } else {
            cGuardarComo();
        }
    }
    private void cCompila() {
        String contents="";
            try {
                contents = textPane.doc.getText(0, textPane.doc.getLength()).toLowerCase();
            } catch (BadLocationException e) {
            }
      Reader read=new StringReader(contents);
        Lexer lexer=new Lexer(read);
        lexer.setLanguage(lang);
        Parser parser = new Parser(lexer);
        parser.setLanguage(lang);
        try {
            parser.parse();
            parsedDoc = parser.parsedDoc.toArray(new String[0]);
            rows = parser.row.toArray(new Integer[0]);
            env = new EnvironmentK();
            for(int i = 0; i < parsedDoc.length; i++)
                System.out.println(parsedDoc[i]);
            textPane2.setText(textPane.getText());
            textPane.search(true);
            textPane2.search(true);
            Ventana2.Mpanel2.reset(true);
            Mpanel2.arranca();
            JOptionPane.showMessageDialog(this,"Programa Compilado","Karel",JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
        JOptionPane.showMessageDialog(this,"Error de compilacion: " + ex.getMessage(),"Karel",JOptionPane.ERROR_MESSAGE);
        }
//        textPane.setText(Compilador.adjust(textPane.doc));
        
    }
    class MDO extends javax.swing.filechooser.FileFilter {
        @Override
        public boolean accept(File file) {
            String filename = file.getName().toUpperCase();
            if (file.isDirectory())
                return true;
            return filename.endsWith(".MDO");
        }
        @Override
        public String getDescription() {
            return "*.MDO";
        }
    }
    class TXT extends javax.swing.filechooser.FileFilter {
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
