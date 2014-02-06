/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.Views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;
import main.Models.Karelotitlan;

/**
 *
 * @author macbook
 */
public class PopUp extends JPopupMenu{
    
  private JMenuItem menuItem=null;
  PopUp(){
      
        menuItem = new JMenuItem("Undo");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,
                                KeyEvent.META_MASK));
        this.add(menuItem);
        menuItem.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            TabPrograma.textPane.undoManager.undo();
            TabPrograma.textPane.search(true);
            Karelotitlan.Codedit=true;
        }
        });
        menuItem = new JMenuItem("Redo");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y,
                                KeyEvent.META_MASK));
        this.add(menuItem);
        menuItem.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            TabPrograma.textPane.undoManager.redo();
            TabPrograma.textPane.search(true);
            Karelotitlan.Codedit=true;
        }
        });
                this.addSeparator();
        menuItem = new JMenuItem("Cut");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
                                KeyEvent.META_MASK));
        this.add(menuItem);
        menuItem.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            TabPrograma.textPane.cut();
            Karelotitlan.Codedit=true;
        }
        });
        menuItem = new JMenuItem("Copy");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
                                KeyEvent.META_MASK));
        this.add(menuItem);
        menuItem.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            TabPrograma.textPane.copy();
        }
        });
        menuItem = new JMenuItem("Paste");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
                                KeyEvent.META_MASK));
        this.add(menuItem);
        menuItem.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            TabPrograma.textPane.paste();
        TabPrograma.textPane.search(true);
            Karelotitlan.Codedit=true;
        }
        });
                this.addSeparator();
        menuItem = new JMenuItem("Select All");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
                                KeyEvent.META_MASK));
        this.add(menuItem);
        menuItem.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            TabPrograma.textPane.selectAll();
        }
        });
                    this.addSeparator();
        menuItem = new JMenuItem("Dar Formato");
        this.add(menuItem);
        menuItem.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            TabPrograma.textPane.setText(TabPrograma.textPane.adjust(TabPrograma.textPane.doc));
            TabPrograma.textPane.search(true);
            Karelotitlan.Codedit=true;
        }
        });
  }
}
