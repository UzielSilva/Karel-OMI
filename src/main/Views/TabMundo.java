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
import javax.swing.JTextField;
import main.Controllers.LisMundo;
import main.Models.EditorMapas;

/**
 *
 * @author macbook
 */
public class TabMundo extends Container {

    JPanel c = new JPanel();
    JPanel pp = new JPanel();
    Container c2 = new Container();
    Container c22 = new Container();
    Container c222 = new Container();
    private JLabel lzumba = null;
    private JButton nuevo = null;
    private JButton abrir = null;
    private JButton guardar = null;
    private JButton infinitos = null;
    private JButton guardarcomo = null;
    public static JScrollBar barraV = null;
    public static JScrollBar barraH = null;
    public static EditorMapas editor = null;
    public static JTextField tzumba = null;
    public static JTextField tzamba = null;

    public TabMundo() {
        reset();
        window();
        properties();
    }

    private void reset() {
        editor = new EditorMapas();
        nuevo = new JButton("Nuevo");
        abrir = new JButton("Abrir");
        guardar = new JButton("Guardar");
        guardarcomo = new JButton("Guardar Como");
        infinitos = new JButton("Infinitos");
        barraH = new JScrollBar(JScrollBar.HORIZONTAL, 0, 5, 0, 87);
        barraV = new JScrollBar(JScrollBar.VERTICAL, 95, 10, 0, 105);
        lzumba = new JLabel("Zumbadores en la mochila");
        tzumba = new JTextField("0");
        tzamba = new JTextField("0");
    }

    private void window() {

        c.setLayout(new BorderLayout());
        c.add(barraH, BorderLayout.SOUTH);
        c.add(barraV, BorderLayout.EAST);
        c.add(editor, BorderLayout.CENTER);
        c.setBorder(BorderFactory.createLineBorder(Color.black));
        c2.setLayout(new GridLayout(2, 2, 2, 2));
        c2.setPreferredSize(new Dimension(210, 70));
        c2.add(nuevo);
        c2.add(abrir);
        c2.add(guardar);
        c2.add(guardarcomo);
        c222.setLayout(new GridLayout(2, 3, 2, 2));
        c222.setPreferredSize(new Dimension(360, 65));
        c222.add(lzumba);
        c222.add(tzumba);
        pp.setVisible(false);
        c222.add(pp);
        c222.add(infinitos);
        c22.setLayout(new FlowLayout());
        c22.add(c2);
        c22.add(c222);
        Container c21 = new Container();
        c21.setLayout(new BorderLayout());
        c21.add(c22, BorderLayout.WEST);

        this.setLayout(new BorderLayout());
        this.add(c21, BorderLayout.NORTH);
        this.add(c, BorderLayout.CENTER);
    }

    private void properties() {

        tzumba.setDragEnabled(false);
        editor.setLayout(null);
        editor.setBackground(Color.white);
        editor.add(tzamba);
        tzamba.setPreferredSize(new Dimension(34, 28));
        tzamba.setHorizontalAlignment(JTextField.CENTER);
        tzamba.setVisible(false);
        editor.reset(barraH.getValue(), barraV.getValue());
        
        barraH.addAdjustmentListener(new LisMundo(0));
        barraV.addAdjustmentListener(new LisMundo(1));
        nuevo.addMouseListener(new LisMundo(2));
        abrir.addMouseListener(new LisMundo(3));
        guardar.addMouseListener(new LisMundo(4));
        guardarcomo.addMouseListener(new LisMundo(5));
        infinitos.addMouseListener(new LisMundo(6));
        editor.addMouseWheelListener(new LisMundo(7));
        editor.addMouseListener(new LisMundo(7));
        tzamba.addKeyListener(new LisMundo(8));
    
    }
    
}