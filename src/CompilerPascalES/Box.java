package CompilerPascalES;

import java.util.HashMap;
import java.util.Scanner;
import java_cup.runtime.Symbol;

class Box {
    
    private static HashMap<String,Integer> KeysTable;
    
    private static final KeyWord[] KeysArray = Global.KeysArray;

    public Box(KeyWord k){
        KeysTable.put(k.getKey(), k.getObj());
    }
    
    static {
        KeysTable = new HashMap();
            for(KeyWord k : KeysArray)
                new Box(k);
    }
    
    public static Integer gets(String str){
        return KeysTable.get(str.toLowerCase());
    }
        
    public static String getType(Integer i){
        for(KeyWord k : KeysArray){
            Integer in = gets(k.getKey());
            if(in == i)
                return k.getKey();
        }
        return null;
    }
    public static void main(String arg[]){
        while(true){
            Scanner s = new Scanner(System.in);
            Integer i = s.nextInt();
            System.out.println(getType(i));
        }
    }
}