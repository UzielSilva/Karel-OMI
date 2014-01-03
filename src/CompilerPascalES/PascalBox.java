package CompilerPascalES;

import java.util.HashMap;

public class PascalBox {
    
    private static HashMap<String,Integer> KeysTable;
    
    private static final KeyWord[] KeysArray = PascalData.KeysArray;

    public PascalBox(KeyWord k){
        KeysTable.put(k.getKey(), k.getObj());
    }
    
    static {
        KeysTable = new HashMap();
            for(KeyWord k : KeysArray)
                new PascalBox(k);
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
}