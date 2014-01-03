package main;

import CompilerPascalES.LexerPascal;
import CompilerPascalES.PascalBox;
import java.awt.*;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java_cup.runtime.Symbol;
import javax.swing.*;
import javax.swing.text.*;

/**
 * @author German gonzalez, Uziel Silva
 */
public class VisorCodigo extends JTextPane  {

    public static Style style[]=new Style[7];
    private LexerPascal lx;
    private Reader read;
    public boolean canremember = true;
    public boolean hightlight = false;
    public StyledDocument doc;
    public static int h = 0;
    public String content;
    public int start;
    public int end;
    public int line;
    private void style(int i,String st,Color c,boolean underline){
        style[i] = this.addStyle(st, null);
        StyleConstants.setForeground(style[i], c);
        StyleConstants.setBold(style[i], true);
        StyleConstants.setUnderline(style[i], underline);
    }
    public VisorCodigo() {
        this.setEditorKit(new VisorCodigo.NumberedEditorKits());
        this.setText(" ");
        this.setForeground(new Color(255,255,50));
        doc = this.getStyledDocument();
        style(0,"red",new Color(255,100,100),false);
        style(1,"Black",new Color(255,255,50),false);
        style(2,"orange",new Color(255, 210, 0),false);
        style(3,"cyan",new Color(0, 255, 255),false);
        style(4,"green",new Color(0, 255, 0),false);
        style(5,"error",new Color(255, 0, 0),true);
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
            try {
                start=0;
                contents = doc.getText(0, doc.getLength()).toLowerCase().split("\n");
            } catch (BadLocationException e) {
            }
        } else {
            contents = content.split("\n");
        }
        for (String curline : contents) {
            read =new StringReader(curline);
        lx=new LexerPascal(read);
            try {
                int n=0;
                Symbol sym;
                    int index=0;
                while((n=(sym=lx.next_token()).sym)!=0){ 
                    if(n>=2&&n<=48){
                    index=curline.indexOf(PascalBox.getType(n),index);
                    if(n==2)
                        doc.setCharacterAttributes(start +index, PascalBox.getType(n).length(), this.getStyle("Black"), true);
                    if(n>=3&&n<=4)
                        doc.setCharacterAttributes(start +index, PascalBox.getType(n).length(), this.getStyle("red"), true);
                    if(n>=5&&n<=25)
                        doc.setCharacterAttributes(start +index, PascalBox.getType(n).length(), this.getStyle("cyan"), true);
                    if(n>=26&&n<=30)
                        doc.setCharacterAttributes(start +index, PascalBox.getType(n).length(), this.getStyle("green"), true);
                    if(n>=31&&n<=48)
                        doc.setCharacterAttributes(start +index, PascalBox.getType(n).length(), this.getStyle("orange"), true);
                    index+=PascalBox.getType(n).length();
                    }
                    if(n==1){
                        doc.setCharacterAttributes(start , curline.length(), this.getStyle("error"), true);
                    }
                    if(n==49||n==50){
                        doc.setCharacterAttributes(start +index, curline.length()-index, this.getStyle("red"), true);
                    }
                }
            } catch (IOException ex) {
            }
            start += curline.length() + 1;
        }
        canremember = true;
    }

    @Override
    public boolean getScrollableTracksViewportWidth() {
    Component parent = getParent();
    return parent != null ? (ui.getPreferredSize(this).width <= parent
        .getSize().width) : true;
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
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
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
            if(hightlight==true && line == previousLineCount + n + 1){
                g2.setColor(Color.yellow);
                g2.setXORMode(Color.black);
                g2.fillRect(numberX + NUMBERS_WIDTH - 2, numberY - 12, this.getWidth(), r.height + 1);
                g.setPaintMode();
            }
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