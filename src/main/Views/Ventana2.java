package main.Views;

import javax.swing.*;

/**
 * @author German Gonzalez
 */
public class Ventana2 extends JTabbedPane{
    public static TabMundo mundo=new TabMundo();
    public static TabPrograma programa=new TabPrograma();
    public static PopUp popup=new PopUp();
    public Ventana2(){
        this.setFocusable(false);
        this.addTab("Mundo", mundo);
        this.addTab("Programa", programa);
        this.addTab("Ejecutar", new TabEjecutar());
    }
}
