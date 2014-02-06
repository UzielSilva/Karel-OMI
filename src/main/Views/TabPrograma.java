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
        textPane.setFont(new java.awt.Font("Monospaced", 0, 13));
        
        nuevo.addMouseListener(new LisPrograma(0));
        abrir.addMouseListener(new LisPrograma(1));
        guardar.addMouseListener(new LisPrograma(2));
        guardarcomo.addMouseListener(new LisPrograma(3));
        compilar.addMouseListener(new LisPrograma(4));
        textPane.addCaretListener(new LisPrograma(5));
        textPane.addMouseListener(new LisPrograma(5));
        textPane.addKeyListener(new LisPrograma(5));
    }
}