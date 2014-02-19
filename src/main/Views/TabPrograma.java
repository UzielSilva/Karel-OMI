/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import main.Controllers.LisMundo;
import main.Controllers.LisPrograma;
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
    public static ButtonGroup code= null;
    public static ButtonGroup lang= null;
    public static JRadioButton ES= null;
    public static JRadioButton EN= null;
    public static JRadioButton java= null;
    public static JRadioButton pascal= null;

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
        code=new ButtonGroup();
        lang=new ButtonGroup();
        ES=new JRadioButton("ES");
        ES.setSelected(true);
        EN=new JRadioButton("EN");
        pascal=new JRadioButton("Pascal");
        pascal.setSelected(true);
        
        java=new JRadioButton("Java");
    }

    private void window() {
        JPanel codepanel = new JPanel(new GridLayout(1, 0));
        codepanel.add(pascal);
        codepanel.add(java);
        JPanel langpanel = new JPanel(new GridLayout(1, 0));
        langpanel.add(ES);
        langpanel.add(EN);
        langpanel.setOpaque(false);
        codepanel.setOpaque(false);
        code.add(pascal);
        code.add(java);
        lang.add(ES);
        lang.add(EN);   
        Container c4 = new Container();
        c4.setLayout(new FlowLayout());
        c4.add(nuevo);
        c4.add(abrir);
        c4.add(guardar);
        c4.add(guardarcomo);
        c4.add(compilar);
        c4.add(codepanel);
        c4.add(langpanel);
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
        textPane.setFont(new java.awt.Font("Monospaced", 0, 13));
        
        nuevo.addMouseListener(new LisPrograma(0));
        abrir.addMouseListener(new LisPrograma(1));
        guardar.addMouseListener(new LisPrograma(2));
        guardarcomo.addMouseListener(new LisPrograma(3));
        compilar.addMouseListener(new LisPrograma(4));
        textPane.addCaretListener(new LisPrograma(5));
        textPane.addMouseListener(new LisPrograma(5));
        textPane.addKeyListener(new LisPrograma(5));
        ES.addActionListener(new LisPrograma(9));
        EN.addActionListener(new LisPrograma(10));
        java.addActionListener(new LisPrograma(11));
        pascal.addActionListener(new LisPrograma(12));
    }
}