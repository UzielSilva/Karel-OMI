package main;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;

/**
 * @author German gonzalez, Uziel Silva
 */
public class VisorCodigo extends JTextPane  {

    public static Style style[]=new Style[7];
    public boolean canremember = true;
    public StyledDocument doc;
    public static int h = 0;
    public String content;
    public int start;
    public int end;
    
    private void style(int i,String st,Color c){
        style[i] = this.addStyle(st, null);
        StyleConstants.setForeground(style[i], c);
        StyleConstants.setBold(style[i], true);
    }
    public VisorCodigo() {
        this.setEditorKit(new VisorCodigo.NumberedEditorKits());
        this.setText(" ");
        this.setForeground(new Color(255,255,50));
        doc = this.getStyledDocument();
        style(0,"red",Color.red);
        style(1,"Black",new Color(255,255,50));
        style(2,"orange",new Color(255, 210, 0));
        style(3,"cyan",new Color(0, 255, 255));
        style(4,"green",new Color(0, 255, 0));
        style[6] = this.addStyle("high", null);
        StyleConstants.setLeftIndent(style[6], 37f);
        this.setParagraphAttributes(style[6], false);
        doc.setCharacterAttributes(0, 1, this.getStyle("Black"), true);
    }



    public void search(boolean full) {
        canremember = false;
        this.setParagraphAttributes(style[6], false);
        String contents[] = new String[0];
        if (full) {
            start = 0;
            end = doc.getLength();
            try {
                contents = doc.getText(start, end - start).toLowerCase().split("\n");
            } catch (BadLocationException e) {
            }
        } else {
            contents = content.split("\n");
        }
        for (String curline : contents) {
            doc.setCharacterAttributes(start, curline.length(), this.getStyle("Black"), true);
            String word[] = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "+", "-", "n"};
            int lastIndex = 0;
            String lol = " " + curline + " ";
            for (String w : word) {
                while ((lastIndex = lol.indexOf(w, lastIndex)) != -1) {
                    int endIndex = lastIndex + w.length();
                    int startt;
                    int endd;
                    int i = 0;
                    while ("0123456789+-(n)".contains(lol.substring(i + lastIndex, i + lastIndex + 1))) {
                        i--;
                    }
                    startt = lastIndex + i;
                    i++;
                    while ("0123456789+-(n)".contains(lol.substring(i + lastIndex, i + lastIndex + 1))) {
                        i++;
                    }
                    endd = lastIndex + i;
                    if ((' ' == lol.charAt(startt) || '\n' == lol.charAt(startt) || '|' == lol.charAt(startt))
                            && (' ' == lol.charAt(endd) || '\n' == lol.charAt(endd) || '|' == lol.charAt(endd))) {
                        doc.setCharacterAttributes(start + startt, endd - startt, this.getStyle("red"), true);
                    }
                    lastIndex = endIndex;
                }
            }
            look(new String[]{"iniciar-programa", "inicia-ejecucion", "termina-ejecucion", "finalizar-programa",
                        "define-nueva-instruccion", "como", "inicio", "fin", "sino", "si", "entonces", "mientras", "hacer", "repetir", "veces",
                        "o", "y", "no", "precede", "sucede"}, curline, this.getStyle("cyan"), start);
            look(new String[]{"apagate", "gira-izquierda", "avanza", "coge-zumbador", "deja-zumbador", "sal-de-instruccion"}, curline, this.getStyle("green"), start);
            look(new String[]{"frente-libre", "frente-bloqueado", "izquierda-libre", "izquierda-bloqueada", "derecha-libre",
                        "derecha-bloqueada", "junto-a-zumbador", "no-junto-a-zumbador", "algun-zumbador-en-la-mochila",
                        "ningun-zumbador-en-la-mochila", "orientado-al-norte", "orientado-al-sur",
                        "orientado-al-este", "orientado-al-oeste", "no-orientado-al-norte", "no-orientado-al-sur",
                        "no-orientado-al-este", "no-orientado-al-oeste", "si-es-cero"}, curline, this.getStyle("orange"), start);
            start += curline.length() + 1;
        }
        canremember = true;
    }

    private void look(String word[], String content, AttributeSet color, int start) {
        int lastIndex = 0;
        for (String w : word) {
            while ((lastIndex = content.indexOf(w, lastIndex)) != -1) {
                int endIndex = lastIndex + w.length();
                char starts = (" " + content + " ").charAt(lastIndex);
                char ends = (" " + content + " ").charAt(endIndex + 1);
                if (starts == ' ' || starts == ';' || starts == '(' || starts == '\n' || starts == '|') {
                    if (ends == ' ' || ends == '(' || ends == ')' || ends == ';' || ends == '\n' || ends == '|') {
                        doc.setCharacterAttributes(start + lastIndex, w.length(), color, true);
                    }
                }
                lastIndex = endIndex;
            }
        }
    }
    class NumberedEditorKits extends StyledEditorKit {

        @Override
        public ViewFactory getViewFactory() {
            return new VisorCodigo.NumberedViewFactorys();
        }
    }

    class NumberedViewFactorys implements ViewFactory {

        @Override
        public View create(Element elem) {
            String kind = elem.getName();
            if (kind != null) {
                if (kind.equals(AbstractDocument.ContentElementName)) {
                    return new LabelView(elem);
                } else if (kind.equals(AbstractDocument.ParagraphElementName)) {
                    return new VisorCodigo.NumberedParagraphViews(elem);
                } else if (kind.equals(AbstractDocument.SectionElementName)) {
                    return new BoxView(elem, View.Y_AXIS);
                } else if (kind.equals(StyleConstants.ComponentElementName)) {
                    return new ComponentView(elem);
                } else if (kind.equals(StyleConstants.IconElementName)) {
                    return new IconView(elem);
                }
            }
            // default to text display
            return new LabelView(elem);
        }
    }

    final class NumberedParagraphViews extends ParagraphView {

        public final byte NUMBERS_WIDTH = 37;

        public NumberedParagraphViews(Element e) {
            super(e);
            short top = 0;
            short left = 0;
            short bottom = 0;
            short right = 0;
            this.setInsets(top, left, bottom, right);
        }

        @Override
        protected void setInsets(short top, short left, short bottom,
                short right) {
            super.setInsets(top, (short) (left + NUMBERS_WIDTH),
                    bottom, right);
        }

        @Override
        public void paintChild(Graphics g, Rectangle r, int n) {
            Graphics2D g2=(Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, // Anti-alias!
        RenderingHints.VALUE_ANTIALIAS_ON);
            VisorCodigo.h = r.height;
            super.paintChild(g, r, n);
            int previousLineCount = getPreviousLineCount();
            int numberX = r.x - getLeftInset();
            int numberY = r.y + r.height - 5;
            g2.setColor(new Color(210, 240, 210));
            g2.fillRect(numberX, numberY - 12, NUMBERS_WIDTH - 2, r.height + 1);
            g2.setColor(Color.LIGHT_GRAY);
            g2.drawLine(numberX + NUMBERS_WIDTH - 2, numberY - 12, numberX + NUMBERS_WIDTH - 2, numberY - 12 + r.height + 1);
            g2.setColor(Color.black);
            g2.setFont(new Font("monospaced", 12, 12));
            g2.drawString((previousLineCount + n + 1) + "", numberX + 30 - (((previousLineCount + n + 1) + "").length() * 7), numberY);
        }

        public int getPreviousLineCount() {
            int lineCount = 0;
            View parent = this.getParent();
            int count = parent.getViewCount();
            for (int i = 0; i < count; i++) {
                if (parent.getView(i) == this) {
                    break;
                } else {
                    lineCount += parent.getView(i).getViewCount();
                }
            }
            return lineCount;
        }
    }
}