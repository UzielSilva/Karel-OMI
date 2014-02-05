/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import main.Controllers.LisEjecutar;

/**
 *
 * @author macbook
 */
public class TabEjecutar extends Container{
    
    private JLabel lzumba2=null;
    private JLabel elejecu=null;
    private JSplitPane eSplitPane1 =null;
    private JSplitPane eSplitPane2=null;
    public static JScrollBar ebarraH=null;
    public static JScrollBar ebarraV=null;
    public static JTextField etzumba=null;
    public static JTextField etretar=null;
    public static JTextArea edbug=null;
    private JButton eadelante;
    private JButton einiciar;
    private JButton ecorrer;
    private JButton edetener;
    public static VisorMapas Mpanel2=null;
    public static VisorCodigo textPane2=null; 
    public JScrollPane ejScrollPane2=null;
    public TabEjecutar() {
        reset();
        window();
        properties();
    }
    private void reset(){
        
    Mpanel2=new VisorMapas();
  textPane2= new VisorCodigo(); 
    eadelante=new JButton("Adelante");
   einiciar=new JButton("Iniciar");
    ecorrer=new JButton("Correr");
     edetener=new JButton("Detener");
   ebarraH= new JScrollBar(JScrollBar.HORIZONTAL,0,5,0,96);
 ebarraV= new JScrollBar(JScrollBar.VERTICAL,95,10,0,105);
    lzumba2=new JLabel("Zumbadores en la mochila");
    elejecu=new JLabel("Retardo de ejecucion (ms)");
     eSplitPane1 = new JSplitPane();
     eSplitPane2= new JSplitPane();
    etzumba=new JTextField("0");
 etretar=new JTextField("100");
   edbug=new JTextArea();
    ejScrollPane2= new JScrollPane(textPane2);
    }
    private void window(){
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
        eSplitPane2= new JSplitPane(JSplitPane.VERTICAL_SPLIT,ejScrollPane2, edbug);
        eSplitPane2.setDividerLocation(165);
        eSplitPane1= new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,eSplitPane2, cc);
        eSplitPane1.setDividerLocation(250);
        this.setLayout(new BorderLayout());
        this.add(cc21,BorderLayout.NORTH);
        this.add(eSplitPane1,BorderLayout.CENTER);
    }
    private void properties(){
        
        ejScrollPane2.setViewportView(textPane2);
        ejScrollPane2.setBorder(null);
        textPane2.setBackground(new Color(25,25,150));
        textPane2.setSelectionColor(Color.blue);
        textPane2.setEditable(false);
        textPane2.setFont(new java.awt.Font("Monospaced", 0, 13)); 
        edbug.setEditable(false);
        etzumba.setEditable(false); 
        Mpanel2.setBackground(Color.white);
        
        ebarraH.addAdjustmentListener(new LisEjecutar(0));
        ebarraV.addAdjustmentListener(new LisEjecutar(1));
        eadelante.addMouseListener(new LisEjecutar(2));
        einiciar.addMouseListener(new LisEjecutar(3));
        ecorrer.addMouseListener(new LisEjecutar(4));
        edetener.addMouseListener(new LisEjecutar(5));
    }
}
//        eadelante.addMouseListener(new java.awt.event.MouseAdapter() {
//            @Override
//            public void mouseReleased(java.awt.event.MouseEvent evt) {
//                Mpanel2.avanzauno();
//            }
//        });
//        einiciar.addMouseListener(new java.awt.event.MouseAdapter() {
//            @Override
//            public void mouseReleased(java.awt.event.MouseEvent evt) {
//                Mpanel2.reset(true);
//            }
//        });
//        ecorrer.addMouseListener(new java.awt.event.MouseAdapter() {
//            @Override
//            public void mouseReleased(java.awt.event.MouseEvent evt) {
//                Mpanel2.corre();
//            }
//        });
//        edetener.addMouseListener(new java.awt.event.MouseAdapter() {
//            @Override
//            public void mouseReleased(java.awt.event.MouseEvent evt) {
//                Mpanel2.stop();
//            }
//        });
