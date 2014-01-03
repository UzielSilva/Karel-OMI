package main;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 * @author German gonzalez, Uziel Silva
 */
public class Mapas extends JPanel {
int x=150;
int y=150;
public int x2=15;
public int y2=15;
int w=20;
int h=30;
int w2=10;
int h2=15;
    public static BufferedImage flecha[] = new BufferedImage[4];
    public static byte walls[][] = new byte[103][103];
    public short zumbador[][] = new short[103][103];
    public byte flechax = 1;
    public byte flechay = 1;
    public byte flechad = 0;
    int ScrlV=95;
    int ScrlH=0;
    public int z = 0;
    public short[][] getzumbamap(){
        return zumbador;
    }
    public void setzumbamap(short[][] zamba){
        zumbador=zamba;
    }
    public void refresh(int ScrlH, int ScrlV) {
        this.ScrlH = ScrlH;
        this.ScrlV = ScrlV;
        this.repaint();
    }
    public Mapas() {
        for (int i = 0; i<4;i++){
            flecha[i] = new BufferedImage(20, 20, BufferedImage.TYPE_4BYTE_ABGR);
            Graphics2D g = flecha[i].createGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(Color.BLUE);
            if(i%2==0)
            g.fillPolygon(new int[]{0, 10, 20}, new int[]{10, (i/2)*20, 10}, 3);
            else
            g.fillPolygon(new int[]{10, ((4-i)/2)*20, 10}, new int[]{0, 10, 20}, 3);
            g.fillRect((6+((i%2)*4))*(1-(((4-i)/2)*(i%2))), (10-((i%2)*4))*(1-((i/2)*((i+1)%2))),
                    8+((i%2)*2), 10-((i%2)*2));
            g.dispose();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int offsetX = 0;
        int offsetY = 0;
        int windowHeight = this.getHeight() / 32;
        int windowWidth = this.getWidth() / 32;
        int windowOffset = this.getHeight() % 32;
        int windowDif;
        if (0 == ScrlH) {///border left
            offsetX = 1;
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(0, 0, 32, this.getHeight());
            g.setColor(Color.black);
            for (int yy = windowHeight; yy > -2; yy--) {
                g.drawString((windowHeight - yy + 95 - ScrlV) + "", 5, yy * 32 + windowOffset - 19);
            }

        }
        if (95 == ScrlV) { // border bottom
            offsetY = 1;
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(0, this.getHeight() - 32, this.getWidth(), 32);
            g.setColor(Color.black);
            for (int xx = 0; xx < windowWidth + 1; xx++) {
                g.drawString((ScrlH + xx) + "", xx * 32 + 16, this.getHeight() - 8);
            }

        }
        windowDif = windowHeight - offsetY;
        g.setColor(Color.GRAY);///gray squares
        for (int xx = offsetX; xx < windowWidth + 1; xx++) {
            for (int yy = windowDif; yy > -1; yy--) {
                g.fillRect(xx * 32, yy * 32 + windowOffset - 16, 16, 16);
            }
        }

        g.drawImage(flecha[flechad], (flechax - ScrlH) * 32 + 14, this.getHeight() - ((flechay - (94 - ScrlV)) * 32) - 2, null);

        for (int xx = offsetX; xx <= windowWidth + 1; xx++) {
            for (int yy = windowHeight - offsetY +1; yy >= -1; yy--) {
                if (xx + ScrlH < 102 && windowDif - ScrlV - yy + 95 < 101 && windowDif - ScrlV - yy + 95 > -1) {
                    g.setColor(Color.red);///zumbadores
                    int val = zumbador[xx + ScrlH][windowDif - ScrlV - yy + 95];
                    if (val != 0) {
                        g.drawString(val + "", xx * 32 - 8 - ((val + "").length() * 4), (yy + offsetY) * 32 + 14 - (32 - windowOffset));
                    }
                    g.setColor(Color.black);///paredes
                    val = walls[xx + ScrlH][windowDif - ScrlV - yy + 95];
                    if (val != 0) {
                        boolean bo[] = Lib.byteBol((byte) val);
                        int xsx=xx * 32;
                        int ysy=(offsetY + yy) * 32 + windowOffset;
                        if (bo[0]) g.fillRect(xsx - 24, ysy - 42, 32, 4);
                        if (bo[2]) g.fillRect(xsx - 24, ysy - 10, 32, 4);
                        if (bo[1]) g.fillRect(xsx +  6, ysy - 40, 4, 32);
                        if (bo[3]) g.fillRect(xsx - 26, ysy - 40, 4, 32);
                    }
                }
            }
        }
    }

}