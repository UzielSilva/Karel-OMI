package main.Views;

import javax.swing.*;

/**
 * @author German Gonzalez
 */
public class Ventana2 extends JTabbedPane{
    public static TabMundo mundo=new TabMundo();
    public Ventana2(){
        this.setFocusable(false);
        this.addTab("Mundo", mundo);
        this.addTab("Programa", new TabPrograma());
        this.addTab("Ejecutar", new TabEjecutar());
    }
}
