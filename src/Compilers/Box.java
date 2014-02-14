package Compilers;

import java.util.HashMap;

public class Box {
    
    private static HashMap<String,Integer> KeysTable;
    
    private KeysDictionary Keys;

    public Box(KeysDictionary keys){
        Keys = keys;
        KeysTable = new HashMap();
        for(KeyWord k : Keys.getKeysArray())
            KeysTable.put(k.getKey(), k.getObj());
    }
    
    public Integer gets(String str){
        return KeysTable.get(str);
    }
        
    public String getType(Integer i){
        
        if (i == Keys.getError())
            return "error";
            
        if (i == Keys.getName())
            return "name";
            
        if (i == Keys.getNumber())
            return "number";
        
        for(KeyWord k : Keys.getKeysArray()){
            Integer in = gets(k.getKey());
            if(in == i)
                return k.getKey();
        }
        return null;
    }
}