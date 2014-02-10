package main.Views;

import java.io.IOException;
import javax.swing.*;

/**
 * @author German Gonzalez
 */
public class Ventana2 extends JTabbedPane{
    public static TabMundo mundo=new TabMundo();
    public static TabPrograma programa=new TabPrograma();
    public static PopUp popup=new PopUp();
    private JEditorPane help= new JEditorPane("text/html",""); 
    public Ventana2(boolean hel){
        this.setFocusable(false);
        this.addTab("Mundo", mundo);
        this.addTab("Programa", programa);
        this.addTab("Ejecutar", new TabEjecutar());
        if(hel){
        this.addTab("Ayuda", new JScrollPane(help));
        help.setEditable(false);
        try {
            help.setPage(getClass().getResource("../HelpDoc/KarelSyntax_es.html"));
        } catch (IOException ex) {}
    }}
}
