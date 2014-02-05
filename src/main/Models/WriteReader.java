package main.Models;

import main.Models.Lib;
import main.Models.EditorMapas;
import java.io.*;
import java.nio.ByteBuffer;
import java.util.logging.*;
import main.Views.TabMundo;
import main.Views.Ventana2;

/**
 * @author German gonzalez
 */
public class WriteReader {

    FileOutputStream arc;
    FileInputStream read;

    public void writes(byte x, byte y, byte d, int z, byte map[][], short zumba[][]) {
        try {
            arc = Lib.write(Karelotitlan.world);
            byte[] bytes = ByteBuffer.allocate(4).putInt(z).array();
            arc.write(new byte[]{0x4B, 0x41, 0x52, 0x45, 0x4C, 0x20, 0x4F, 0x4D, 0x49, 0x2E, 0x01, 0x00, 0x64, 0x00, 0x64, 0x00,
                                 bytes[3], bytes[2],x, 0x00, y, 0x00, d, 0x00});
            walls(map, TabMundo.editor.getzumbamap());
            arc.flush();
            arc.close();
            arc=null;
        } catch (IOException ex) {
            Logger.getLogger(WriteReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void walls(byte wlls[][], short zmbdr[][]) throws IOException {//xx 00 yy 00 dd 00
        int wls = 0;
        int zmb = 0;
        for (byte xxx = 0; xxx < 101; xxx++) 
            for (byte yyy = 0; yyy < 101; yyy++) {
                if (wlls[xxx+1][yyy] != 0)  wls++;
                if (zmbdr[xxx+1][yyy] != 0) zmb++;
            }
        byte[] z = ByteBuffer.allocate(4).putInt(zmb).array();
        byte[] w = ByteBuffer.allocate(4).putInt(wls).array();
        arc.write(new byte[]{w[3], w[2], z[3], z[2], 0x03, 0x00});
        for (byte xxx = 0; xxx < 101; xxx++) 
            for (byte yyy = 0; yyy < 101; yyy++) 
                if (wlls[xxx + 1][yyy] != 0) 
                    arc.write(new byte[]{xxx, 0x00, yyy, 0x00, (byte) wlls[xxx + 1][yyy], 0x00});
        arc.flush();
        for (byte xxx = 0; xxx < 101; xxx++) 
            for (byte yyy = 0; yyy < 101; yyy++) 
                if (zmbdr[xxx + 1][yyy] != 0) {
                    arc.write(new byte[]{xxx, 0, yyy, 0, (byte)zmbdr[xxx + 1][yyy], 0});
                }
        arc.flush();
    }

    public void reads(File s) {
        try {
            TabMundo.editor.zumbador = new short[103][103];
            EditorMapas.walls = new byte[103][103];
            read = Lib.read(s);
            read.skip(16);//head
            karelDataW();
            wallszumba();
            read.close();
            read=null;
        } catch (IOException ex) {
            Logger.getLogger(WriteReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void karelDataW() throws IOException {
        TabMundo.editor.z = rd();
        TabMundo.editor.flechax = (byte) rd();
        TabMundo.editor.flechay = (byte) rd();
        TabMundo.editor.flechad = (byte) (rd() - 1);

    }

    private void wallszumba() throws IOException {
        int paredes = rd();
        int zumba = rd();
//        System.out.println(zumba);
        rd();
        for (int i = 0; i < paredes; i++){
            EditorMapas.walls[rd()+1][rd()] = (byte) rd();
        }
        for (int i = 0; i < zumba; i++){
            TabMundo.editor.zumbador[rd()+1][rd()] = (short) rd();
        }
    }
    private int rd() throws IOException {
        return (read.read() + (read.read() << 8));
    }
}
