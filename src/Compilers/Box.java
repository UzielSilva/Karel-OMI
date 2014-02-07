package Compilers;

import java.util.HashMap;

public class Box {
    
    private static HashMap<String,Integer> KeysTable;
    
    private static KeyWord[] KeysArray;

    public Box(KeyWord[] lang){
        KeysArray = lang;
        KeysTable = new HashMap();
        for(KeyWord k : KeysArray)
            KeysTable.put(k.getKey(), k.getObj());
    }
    
    public Integer gets(String str){
        return KeysTable.get(str);
    }
        
    public String getType(Integer i){
        for(KeyWord k : KeysArray){
            Integer in = gets(k.getKey());
            if(in == i)
                return k.getKey();
        }
        return null;
    }
}