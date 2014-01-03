package main;

import java.awt.event.MouseEvent;
import java.io.File;

/**
 * @author German gonzalez, Uziel Silva
 */
public class EditorMapas extends Mapas{
    WriteReader d = new WriteReader();
    byte x = -1;
    byte y = -1;
    
    public void open(File s) {
        d.reads(s);
        Ventana2.mtzumba.setText(z + "");
        if (z == 0xFFFF) {
            Ventana2.mtzumba.setText("INFINITO");
        }
    }

    private byte getzumba() {
        if ("".equals(Ventana2.mtzumba.getText())) {
            Ventana2.mtzumba.setText("0");
        }
        if ("INFINITO".equals(Ventana2.mtzumba.getText())) {
            return (byte) 0xFFFF;
        }
        try {
            return (byte) Integer.parseInt(Ventana2.mtzumba.getText());
        } catch (Exception e) {
            return 0;
        }
    }

    public void save() {
        d.writes( flechax, flechay, (byte) (flechad + 1), getzumba(), walls,  Ventana2.Mpanel.getzumbamap());
    }

    public void savez(int ScrlH, int ScrlV) {
        int xx = (x / 2) + ScrlH;
        int yy = (y / 2) + (94 - ScrlV);
        if (x > 0 && y > 0 && xx < 102 && yy < 100) {
            if ("".equals(Ventana2.mtzamba.getText())) {
                Ventana2.mtzamba.setText("0");
            }
            try {
                if (Ventana2.mtzamba.getText().length() > 3) {
                    Ventana2.mtzamba.setText(Ventana2.mtzamba.getText().substring(0, 3));
                }
                Ventana2.Mpanel.zumbador[xx + 1][yy + 1] = (short) Integer.parseInt(Ventana2.mtzamba.getText());
            } catch (Exception e) {
            }
        }
        Ventana2.mtzamba.setText("");
        refresh(ScrlH, ScrlV);
        x = -1;
        y = -1;
        Ventana2.mtzamba.setVisible(false);
    }
    public final void reset(int ScrlH, int ScrlV) {
        x = -1;
        y = -1;
        flechax = 1;
        flechay = 1;
        flechad = 0;
        zumbador = new short[103][103];
        walls = new byte[103][103];
        for (int i = 1; i < 101; i++) {
            for (int j = 2; j < 102; j++) {
                if (j == 2) {
                    walls[j][i] += 8;
                }
                if (j == 101) {
                    walls[j][i] += 2;
                }
                if (i == 1) {
                    walls[j][i] += 4;
                }
                if (i == 100) {
                    walls[j][i] += 1;
                }
            }
        }
        savez(ScrlH, ScrlV);
    }

    public void select(MouseEvent evt, int ScrlH, int ScrlV) {
        savez(ScrlH, ScrlV);
        x = (byte) (evt.getX() / 16);
        y = (byte) ((this.getHeight() - evt.getY()) / 16);
        byte xx = (byte) ((x / 2) + (ScrlH));
        byte yy = (byte) ((y / 2) + (94 - ScrlV));
        
        if (xx > 0 && yy >= 0 && xx < 101 && yy < 100) {
                if (evt.getButton() == 1) {
            if ((x % 2 == 0 || y % 2 == 0) ) {
                if (x % 2 == 0 && y % 2 == 1) {
                    boolean wls[] = Lib.byteBol(walls[xx + 1][yy + 1]);
                    boolean wls2[] = Lib.byteBol(walls[xx][yy + 1]);
                    wls2[1] = !wls2[1];
                    wls[3] = !wls[3];
                    walls[xx + 1][yy + 1] = Lib.Bolbyte(wls);
                    walls[xx][yy + 1] = Lib.Bolbyte(wls2);
                } else {
                    boolean wls[] = Lib.byteBol(walls[xx + 1][yy + 1]);
                    boolean wls2[] = Lib.byteBol(walls[xx + 1][yy]);
                    if(yy!=0){
                    wls2[0] = !wls2[0];
                    wls[2] = !wls[2];
                    }
                    walls[xx + 1][yy + 1] = Lib.Bolbyte(wls);
                    walls[xx + 1][yy] = Lib.Bolbyte(wls2);
                }
                x = -1;
                y = -1;
            } else {
                    Ventana2.mtzamba.setVisible(true);
                    Ventana2.mtzamba.setText(zumbador[xx + 1][yy + 1] + "");
                    Ventana2.mtzamba.select(0, Ventana2.mtzamba.getText().length());
                    Ventana2.mtzamba.setBounds(x * 16 - 8, this.getHeight() - (y * 16) - 21, 32, 24);
                    Ventana2.mtzamba.requestFocus();
                
            }}else {
                    yy = (byte) ((y / 2) + (95 - ScrlV));
                    if (xx != flechax || yy != flechay) {
                        flechax = xx;
                        flechay = yy;
                    } else {
                        flechad = (byte) ((flechad + 3) % 4);
                    }
                x = -1;
                y = -1;
                }
        }
            Ventana2.Mpanel2.reset();
    }
}