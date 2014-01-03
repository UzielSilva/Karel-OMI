package main;

import CompilerPascalES.LexerPascal;
import CompilerPascalES.ParserPascal;
import CompilerPascalES.Program;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.BadLocationException;
import javax.swing.undo.UndoManager;
import CompilerPascalES.EnvironmentK;

/**
 * @author German Gonzalez
 */
public class Ventana2 extends JFrame {

    boolean action = false;
    public static File world;
    public static File code;
   
    public static String[] parsedDoc;
    public static Integer[] rows;
    public static EnvironmentK env;
    public static Program program;
    
    public static EditorMapas Mpanel= new EditorMapas();
    public static VisorMapas Mpanel2=new VisorMapas();
    public static EditorCodigo textPane = new EditorCodigo();
    public static VisorCodigo textPane2= new VisorCodigo(); 
    private JPopupMenu popmenu=new JPopupMenu();
  private JMenuItem menuItem;
    private JButton mnuevo=new JButton("Nuevo");
    private JButton cnuevo=new JButton("Nuevo");
    private JButton mAbrir=new JButton("Abrir");
    private JButton cAbrir=new JButton("Abrir");
    private JButton mGuardar=new JButton("Guardar");
    private JButton cGuardar=new JButton("Guardar");
    private JButton mGuardarcomo=new JButton("Guardar Como");
    private JButton cGuardarcomo=new JButton("Guardar Como");
    private JButton mInfinitos=new JButton("Infinitos");
    private JButton cCompilar=new JButton("Compilar");
    private JButton eadelante=new JButton("Adelante");
    private JButton einiciar=new JButton("Iniciar");
    private JButton ecorrer=new JButton("Correr");
    private JButton edetener=new JButton("Detener");
    public static JScrollBar mbarraH= new JScrollBar(JScrollBar.HORIZONTAL,0,5,0,87);
    public static JScrollBar ebarraH= new JScrollBar(JScrollBar.HORIZONTAL,0,5,0,96);
    public static JScrollBar mbarraV= new JScrollBar(JScrollBar.VERTICAL,95,10,0,105);
    public static JScrollBar ebarraV= new JScrollBar(JScrollBar.VERTICAL,95,10,0,105);
    private JLabel lzumba=new JLabel("Zumbadores en la mochila");
    private JLabel lzumba2=new JLabel("Zumbadores en la mochila");
    public static JLabel clpos=new JLabel("linea 0, columna 0");
    private JLabel elejecu=new JLabel("Retardo de ejecucion (ms)");
    private JSplitPane eSplitPane1 = new JSplitPane();
    private JSplitPane eSplitPane2= new JSplitPane();
    public static JTextField mtzumba=new JTextField("0");
    public static JTextField mtzamba=new JTextField("0");
    public static JTextField etzumba=new JTextField("0");
    public static JTextField etretar=new JTextField("100");
    public static JTextArea edbug=new JTextArea();
    public static JTabbedPane tabpanel=new JTabbedPane();
    private JEditorPane help= new JEditorPane("text/html",""); 
    public static JScrollPane cjScrollPane= new JScrollPane();
    public JScrollPane ejScrollPane2= new JScrollPane(textPane2);
@Override
public Image getIconImage() {
Image retValue = Toolkit.getDefaultToolkit().
getImage(ClassLoader.getSystemResource("Logo.png"));


return retValue;
}
    public Ventana2(){
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("KAREL OMI por German Gonzalez y Uziel Silva");
        setMinimumSize(new Dimension(660, 380));
        
//addWindowListener(new WindowAdapter(){
//	public void windowClosing(WindowEvent we){
//		int eleccion = JOptionPane.showConfirmDialog(null, "Hay cambios sin guardar en el mapa, decea salir sin guardar?");
//		if ( eleccion == 0) {
//			System.exit(0);
//		}			
//                if ( eleccion == 1) {
//            mGuardarComo();
//		}	
//	}
//});
//    Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource ("logo.png"));
//    this.setIconImage(image);
//        try {
//            Application application = Application.getApplication();
//            application.setDockIconImage(image);
//        } catch (Exception e) {
//        }
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
        cjScrollPane.setBorder(BorderFactory.createLineBorder(Color.black));
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
        }
        });
        menuItem = new JMenuItem("Redo");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y,
                                KeyEvent.META_MASK));
        popmenu.add(menuItem);
        menuItem.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            textPane.undoManager.redo();textPane.search(true);
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
        eSplitPane2= new JSplitPane(JSplitPane.VERTICAL_SPLIT,ejScrollPane2, edbug);
        eSplitPane2.setDividerLocation(165);
        eSplitPane1= new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,eSplitPane2, cc);
        eSplitPane1.setDividerLocation(250);
        Container cc3=new Container();
        cc3.setLayout(new BorderLayout());
        cc3.add(cc21,BorderLayout.NORTH);
        cc3.add(eSplitPane1,BorderLayout.CENTER);
                    tabpanel.addTab("Ejecutar", cc3);
                    tabpanel.addTab("Ayuda", new JScrollPane(help));
        help.setEditable(false);
        try {
            help.setPage(getClass().getResource("HelpDoc/KarelSyntax_es.html"));
        } catch (IOException ex) {}
        this.add(tabpanel);
               Mpanel.reset(mbarraH.getValue(), mbarraV.getValue());
        this.pack();
        help.addHyperlinkListener(new HyperlinkListener() {
            @Override
            public void hyperlinkUpdate(HyperlinkEvent event) {
              if (event.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                try {
                  help.setPage(event.getURL());
                } catch(IOException ioe) {
                }
              }
            }
        });
        mnuevo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent evt) {
               world=null;
               Mpanel.reset(mbarraH.getValue(), mbarraV.getValue());
            Ventana2.Mpanel2.reset();
            }
        });
        mGuardar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent evt) {
                mGuardar();
            }
        });
        mAbrir.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent evt) {
                mAbrir();
            }
        });
        mGuardarcomo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent evt) {
                mGuardarComo();
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
                Mpanel2.reset();
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
            }
        });
        cGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cGuardar();
            }
        });
        cAbrir.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cAbrir();
            }
        });
        cGuardarcomo.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cGuardarComo();
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
            if(!Me.isPopupTrigger()&&Me.getButton()==MouseEvent.BUTTON3){
            popmenu.show(Me.getComponent(), Me.getX(), Me.getY());
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
                avanzauno();
            }
        });
        einiciar.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Mpanel2.reset();
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
            Ventana2.Mpanel2.reset();
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
    private void avanzauno(){
        System.out.println(program.nextAction());
    }
    private void cCompila() {
        String contents="";
            try {
                contents = textPane.doc.getText(0, textPane.doc.getLength()).toLowerCase();
            } catch (BadLocationException e) {
            }
      Reader read=new StringReader(contents);
        LexerPascal lexer=new LexerPascal(read);
        ParserPascal parser = new ParserPascal(lexer);
        try {
            parser.parse();
            parsedDoc = parser.parsedDoc.toArray(new String[0]);
            rows = parser.row.toArray(new Integer[0]);
            env = new EnvironmentK();
            program = new Program(parsedDoc,rows,env);
            System.out.println(contents);
            textPane2.setText(textPane.getText());
            textPane.search(true);
            textPane2.search(true);
            Ventana2.Mpanel2.reset();
            JOptionPane.showMessageDialog(this,"Programa Compilado","Karel",JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
        JOptionPane.showMessageDialog(this,"Error de compilacion: " + ex.getMessage(),"Karel",JOptionPane.INFORMATION_MESSAGE);
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
