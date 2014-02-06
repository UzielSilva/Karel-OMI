package main.Models;

import java.io.*;

/**
 * @author German gonzalez, Uziel Silva
 */
public class Lib {

    public static boolean[] byteBol(byte x) {
        return new boolean[]{(x & 0x01) != 0,(x & 0x02) != 0,(x & 0x04) != 0,(x & 0x08) != 0};
    }

    public static byte Bolbyte(boolean[] x) {
        return (byte)((x[0]?1:0) + (x[1]?2:0)+ (x[2]?4:0)+ (x[3]?8:0));
    }

    public static BufferedWriter writes(File s) throws IOException {
        return new BufferedWriter(new FileWriter(s));
    }

    public static void pause(int time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {}
    }

    public static FileOutputStream write(File s) throws FileNotFoundException {
            return new FileOutputStream(s);
    }
    public static FileInputStream read(File s) throws FileNotFoundException {
            return new FileInputStream(s);
    }
    public static BufferedReader readF(File s) throws FileNotFoundException {
            return new BufferedReader(new FileReader(s));
    }
    
    
}
