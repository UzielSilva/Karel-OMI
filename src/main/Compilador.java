package main;

import java.util.Stack;
import javax.swing.text.*;

/**
 * @author German Gonzalez
 */
public class Compilador {

    public static Stack<String> stack = new Stack<String>();
    public static byte walls[][] = new byte[103][103];
    public static String definiciones[];
    public static String loopwhile[];
    public static String condition[];
    public static String metodos[][];
    public static boolean r = true;
    public static String loopfor[];
    public static String lines[];
    public static String compi[];
    public static byte x = 1;
    public static byte y = 1;
    public static byte d = 0;
    public static int z = 0;
    public static int e = 0;

    private static void err(int t, int line) {
        switch (t) {
            case 0:
                System.out.println("Intento crusar pared en la linea " + line);
                break;
            case 1:
                System.out.println("Intento coger un zumbador donde no habia en la linea " + line);
                break;
            case 2:
                System.out.println("Intento dejar un zumbador sin zumbadores en la mochila. linea " + line);
                break;
            case 3:
                System.out.println("No es un numero valido en la linea " + line);
                break;
            case 4:
                System.out.println("Se esperaba hacer en la linea " + line);
                break;
            case 5:
                System.out.println("Se esperaba como en la linea " + line);
                break;
            case 6:
                System.out.println("Se esperaba entonces en la linea " + line);
                break;
            case 7:
                System.out.println("Se esperaba veces en la linea " + line);
                break;
        }
        r = false;
    }

    public static String adjust(Document doc) {
        try {
            String document = doc.getText(0, doc.getLength()).toLowerCase();
            while (document.contains("( ")) {
                document = document.replace("( ", "(");
            }
                document = document.replace("(", " (");
            while (document.contains(" )")) {
                document = document.replace(" )", ")");
            }
            for (String s : new String[]{"\n", "|", "  "})
                while (document.contains(s)) {
                    document = document.replace(s, " ");
                }
            while (document.contains(";;")) {
                document = document.replace(";;", ";");
            }
            for (String s : new String[]{";", "iniciar-programa", "termina-ejecucion", "inicia-ejecucion", "finalizar-programa"}) {
                document = document.replace(s + " ", s);
                document = document.replace(s, s + "\n");
            }
            for (String s : new String[]{"hacer", "como", "entonces", "veces", "inicio"}) {
                document = document.replace(s + " ", s + "\n");
                document = document.replace(s + "\ninicio ", s + "\ninicio");
                document = document.replace(s + "\ninicio", s + " inicio\n");
            }

            lines = document.split("\n");
            compi = lines;

            marca(0, 0);
            document = "";
            for (String s : lines)
                document += s + "\n";
            return document;
        } catch (BadLocationException ex) {}
        return null;
    }

    private static String spaces(int e) {
        String str = "";
        for (int i = 0; i < e; i++)
            str += " | ";
        return str;
    }

    private static int submarca(int line, int e) {
        if (lines[line].endsWith("fin sino inicio"))
            lines[line] = spaces(e - 1) + lines[line++];
        lines[line] = spaces(e) + lines[line];
        if (lines[line].endsWith("inicio") || lines[line].endsWith("iniciar-programa") || lines[line].endsWith("inicia-ejecucion")) {
            line = marca(line + 1, e + 1);
        } else if (lines[line].endsWith("veces") || lines[line].endsWith("hacer") || lines[line].endsWith("como")
                || lines[line].endsWith("entonces")) {
            line = submarca(line + 1, e + 1);
        } else line++;
        return line;
    }

    private static int marca(int line, int e) {
        while (line < lines.length) {
            if (lines[line].contains("fin;") || lines[line].contains("termina-ejecucion") || lines[line].contains("finalizar-programa"))
                break;
            line = submarca(line, e);
        }
        if (line < lines.length)
            lines[line] = spaces(e - 1) + lines[line];
        return line + 1;
    }
    private static int first(int line){
        if (line == -1) {
            for (int q = 0; q < compi.length; q++)
                if ("inicia-ejecucion".startsWith(compi[q]))
                    return q+1;
            return -2;
        }
        return line;
    }
    private static int pila(int line){
        if (!stack.isEmpty()) {
            String sta = stack.peek();
            if (sta.startsWith("DfN")) {
                String split[]=sta.split("µ");
                if ((line + "").equals(split[4])) {
                    stack.pop();
                    line= Integer.decode(split[1]) + 1;
                }
            }else if (sta.startsWith("MmM")) {
                String split[]=sta.split("µ");
                if ((line + "").equals(split[3])) 
                    if (conditions(split[2], line)) {
                        return Integer.decode(split[1]) + 1;
                    } else stack.pop();
            } else if (sta.startsWith("CnD")) {
                if ((line + "").equals(sta.split("µ")[3])) {
                    stack.pop();
                    return Integer.decode(sta.split("µ")[4]) + 1;
                }
            } else if (sta.startsWith("RpT")) {
                String split[]=sta.split("µ");
                if ((line + "").equals(split[3])) 
                    if (Integer.decode(split[2]) > 1) {
                        stack.pop();
                        stack.add("RpTµ" + split[1] + "µ"
                                + (Integer.decode(split[2]) - 1) + "µ"
                                + split[3]);
                        return Integer.decode(split[1]) + 1;
                    } else stack.pop();
            }
        }
        return line;
    } 
    private static void pilerio(){
        Ventana2.edbug.setText("");
        String txt="";
        for(int t=0;t<stack.size();t++)
            txt+=stack.get(t)+"\n";
        Ventana2.edbug.setText(txt);
            
    }
    public static int leer(int line) {
        if(r==false || line==-2 || line >=compi.length){
            r=true;
            return -2;
        }
        pilerio();
        line=first(line);
        int p=pila(line);
        if(line!= p)
            return p;
        if (compi[line].startsWith("mientras")) {
            String values = "";
            for (int q = 0; q < loopwhile.length; q++)
                if ((line + "").equals(loopwhile[q].split("µ")[0]))
                    values = loopwhile[q];
            if (conditions(values.split("µ")[1], line)) {
                stack.add("MmMµ" + values);
                return line+1;
            } else return Integer.decode(values.split("µ")[2]);
        } else if (compi[line].startsWith("repetir")) {
            String values = "";
            for (int q = 0; q < loopfor.length; q++)
                if ((line + "").equals(loopfor[q].split("µ")[0]))
                    values = loopfor[q];
            if (especiales(values.split("µ")[1], line) > 0) {
                stack.add("RpTµ" + values);
            } else return Integer.decode(values.split("µ")[2]);
        } else if (compi[line].startsWith("si")) {
            String values = "";
            for (int q = 0; q < condition.length; q++)
                if ((line + "").equals(condition[q].split("µ")[0]))
                    values = condition[q];
            if (conditions(values.split("µ")[1], line)) {
                stack.add("CnDµ" + values);
            } else return Integer.decode(values.split("µ")[2]);
        } else {
            for (int q = 0; q < definiciones.length; q++)
                if (compi[line].startsWith(definiciones[q].split("µ")[0])) {
                    stack.add("DfNµ"+line+"µ"+definiciones[q]);
                    return Integer.decode(definiciones[q].split("µ")[1]);
                }
            acciones(compi[line], line);
        }
        return ++line;
    }
    public static int acciones(String s, int line) {
        if (s.equals("avanza;")) {
            switch (d) {
                case 0:
                    if (Lib.byteBol(walls[x + 1][y])[0]) {
                        err(0, line);
                    } else {
                        y++;
                        if(Ventana2.Mpanel2.getHeight() - ((Ventana2.Mpanel2.flechay - (94 - Ventana2.Mpanel2.ScrlV)) * 32) - 2 < 30)
                            Ventana2.ebarraV.setValue(Ventana2.ebarraV.getValue()-1);
                    }
                    break;
                case 1:
                    if (Lib.byteBol(walls[x + 1][y])[1]) {
                        err(0, line);
                    } else {
                        x++;
                        if( (Ventana2.Mpanel2.flechax - Ventana2.Mpanel2.ScrlH) * 32 + 14>Ventana2.Mpanel2.getWidth() -30)
                            Ventana2.ebarraH.setValue(Ventana2.ebarraH.getValue()+1);
                    }
                    break;
                case 2:
                    if (Lib.byteBol(walls[x + 1][y])[2]) {
                        err(0, line);
                    } else {
                        y--;
                        if( ((Ventana2.Mpanel2.flechay - (94 - Ventana2.Mpanel2.ScrlV)) * 32) - 2 < 35)
                            Ventana2.ebarraV.setValue(Ventana2.ebarraV.getValue()+1);
                    }
                    break;
                case 3:
                    if (Lib.byteBol(walls[x + 1][y])[3]) {
                        err(0, line);
                    } else {
                        x--;
                        if( (Ventana2.Mpanel2.flechax - Ventana2.Mpanel2.ScrlH) * 32 + 14< 30)
                            Ventana2.ebarraH.setValue(Ventana2.ebarraH.getValue()-1);
                    }
                    break;
            }
        } else if (s.equals("coge-zumbador;")) {
            if (Ventana2.Mpanel2.getzumbamap()[x + 1][y] == 0) {
                err(1, line);
            } else {
                z++;
                Ventana2.Mpanel2.zumbador[x + 1][y]--;
            }
        } else if (s.equals("deja-zumbador;")) {
            if (z == 0) {
                err(2, line);
            } else {
                z--;
                Ventana2.Mpanel2.zumbador[x + 1][y]++;
            }
        } else if (s.equals("gira-izquierda;")) {
            d = (byte) ((d + 3) % 4);
        } else if (s.equals("apagate;")) {
            r = false;
        } else if (s.equals("sal-de-instruccion;")) {
//            return stack.;
        }
        return -1;
    }

    private static int especiales(String s, int line) {
        if (s.startsWith("precede")) {
            s = s.replaceFirst("precede ", "");
            return especiales(s, line) - 1;
        } else if (s.startsWith("sucede")) {
            s = s.replaceFirst("sucede ", "");
            return especiales(s, line) + 1;
        } else if (s.equals("(n)")) {
//            return c;
        } else if (s.charAt(0) >= '0' && s.charAt(0) <= '9') {
            s = s.replace(" ", "");
            try {
                return Integer.parseInt(s);
            } catch (Exception exc) {err(3, line);}
        }
        return -1;
    }
    
    private static boolean conditions(String s, int line) {
        if (s.equals("frente-libre")) {
            return !Lib.byteBol(walls[x + 1][y])[d];
        } else if (s.equals("frente-bloqueado")) {
            return Lib.byteBol(walls[x + 1][y])[d];
        } else if (s.equals("izquierda-libre")) {
            return !Lib.byteBol(walls[x + 1][y])[(d + 3) % 4];
        } else if (s.equals("izquierda-bloqueada")) {
            return Lib.byteBol(walls[x + 1][y])[(d + 3) % 4];
        } else if (s.equals("derecha-libre")) {
            return !Lib.byteBol(walls[x + 1][y])[(d + 1) % 4];
        } else if (s.equals("derecha-bloqueada")) {
            return Lib.byteBol(walls[x + 1][y])[(d + 1) % 4];
        } else if (s.equals("junto-a-zumbador")) {
            return Ventana2.Mpanel2.getzumbamap()[x + 1][y] != 0;
        } else if (s.equals("no-junto-a-zumbador")) {
            return Ventana2.Mpanel2.getzumbamap()[x + 1][y] == 0;
        } else if (s.equals("algun-zumbador-en-la-mochila")) {
            return z != 0;
        } else if (s.equals("ningun-zumador-en-la-mochila")) {
            return z == 0;
        } else if (s.equals("orientado-al-norte")) {
            return d == 0;
        } else if (s.equals("orientado-al-sur")) {
            return d == 2;
        } else if (s.equals("orientado-al-este")) {
            return d == 1;
        } else if (s.equals("orientado-al-oeste")) {
            return d == 3;
        } else if (s.equals("no-orientado-al-norte")) {
            return d != 0;
        } else if (s.equals("no-orientado-al-sur")) {
            return d != 2;
        } else if (s.equals("no-orientado-al-este")) {
            return d != 1;
        } else if (s.equals("no-orientado-al-oeste")) {
            return d != 3;
        } else if (s.startsWith("si-es-cero")) {
            s = s.replaceFirst("si-es-cero ", "");
            return especiales(s, line) == 0;
        } else {
            return false;
        }
    }

    private static void defines(String program, String documento[]) {
        definiciones = new String[program.split("define-nueva-instruccion ").length - 1];
        int last = 0;
        int end = 0;

        for (int ss = 0; ss < definiciones.length; ss++) {
            for (int xx = last; xx < documento.length; xx++)
                if (documento[xx].startsWith("define-nueva-instruccion ")) {
                    last = xx;
                    xx = documento.length;
                }
            String line = documento[last].replace("define-nueva-instruccion ", "");

            if (line.endsWith(" inicio")) {
                int indexor = program.split("\n")[last].replace(" | ", "π").split("π").length;
                for (int xx = last + 1; xx < documento.length; xx++)
                    if (program.split("\n")[xx].replace(" | ", "π").split("π").length == indexor) {
                        end = xx;
                        xx = documento.length;
                    }
            } else end = last + 1;
            definiciones[ss] = line.substring(0, line.indexOf(" ")) + "µ" + last + "µ" + end;
            System.out.println(definiciones[ss]);
            last = end;
        }
    }

    private static void loopfor(String program, String documento[]) {
        loopfor = new String[program.split("repetir ").length - 1];
        int last = 0;
        int end = 0;

        for (int ss = 0; ss < loopfor.length; ss++) {
            for (int xx = last; xx < documento.length; xx++)
                if (documento[xx].startsWith("repetir ")) {
                    last = xx;
                    xx = documento.length;
                }
            String line = documento[last].replace("repetir ", "");

            if (line.endsWith(" inicio")) {
                int indexor = program.split("\n")[last].replace(" | ", "π").split("π").length;
                for (int xx = last + 1; xx < documento.length; xx++)
                    if (program.split("\n")[xx].replace(" | ", "π").split("π").length == indexor) {
                        end = xx;
                        xx = documento.length;
                    }
            } else end = last + 1;
            loopfor[ss] = last + "µ" + line.substring(0, line.indexOf(" ")) + "µ" + end;
            System.out.println(loopfor[ss]);

            last++;
        }
    }

    private static void loopwhile(String program, String documento[]) {
        loopwhile = new String[program.split("mientras ").length - 1];
        int last = 0;
        int end = 0;

        for (int ss = 0; ss < loopwhile.length; ss++) {
            for (int xx = last; xx < documento.length; xx++)
                if (documento[xx].startsWith("mientras ")) {
                    last = xx;
                    xx = documento.length;
                }
            String line = documento[last].replace("mientras ", "");

            if (line.endsWith(" inicio")) {
                int indexor = program.split("\n")[last].replace(" | ", "π").split("π").length;
                for (int xx = last + 1; xx < documento.length; xx++)
                    if (program.split("\n")[xx].replace(" | ", "π").split("π").length == indexor) {
                        end = xx;
                        xx = documento.length;
                    }
            } else end = last + 1;
            loopwhile[ss] = last + "µ" + line.substring(0, line.indexOf(" ")) + "µ" + end;
            System.out.println(loopwhile[ss]);

            last++;
        }
    }

    private static void condition(String program, String documento[]) {
        condition = new String[program.split("si ").length - 1];
        int last = 0;
        for (int ss = 0; ss < condition.length; ss++) {
            for (int xx = last; xx < documento.length; xx++)
                if (documento[xx].startsWith("si ")) {
                    last = xx;
                    xx = documento.length;
                }
            String line = documento[last].replace("si ", "");
            int ender = -1;
            int end = 0;
            if (line.endsWith(" inicio")) {
                int indexor = program.split("\n")[last].replace(" | ", "π").split("π").length;
                for (int xx = last + 1; xx < documento.length; xx++)
                    if (program.split("\n")[xx].replace(" | ", "π").split("π").length == indexor)
                        if (end == 0) {
                            end = xx;
                            ender = xx;
                            if (!program.split("\n")[xx].contains("fin sino inicio"))
                                xx = documento.length;
                        } else {
                            ender = xx;
                            xx = documento.length;
                        }
                condition[ss] = last + "µ" + line.substring(0, line.indexOf(" ")) + "µ" + end + "µ" + ender;
            } else {
                end = last + 1;
                condition[ss] = last + "µ" + line.substring(0, line.indexOf(" ")) + "µ" + end + "µ" + end;
            }
            System.out.println(condition[ss]);
            last++;
        }
    }

    public static void built(String program) {
        program = program.toLowerCase();
        String documento[] = program.replace(" | ", "").split("\n");

        compi = documento;
        ///checks errors
//        if(program.split(" inicio\n").length-1==program.split("\nfin ").length +program.split("\nfin;").length -2)
//        if(program.split(" mientras").length-1==program.split(" hacer ").length +program.split(" hacer\n").length -2)
//        if(program.split(" repetir").length-1==program.split(" veces ").length +program.split(" veces\n").length -2)
//        if(program.split(" si").length-1==program.split(" entonces ").length +program.split(" entonces\n").length -2)
//            System.out.println("caligrafia correcta");
        defines(program, documento);
        loopfor(program, documento);
        loopwhile(program, documento);
        condition(program, documento);
    }
}
