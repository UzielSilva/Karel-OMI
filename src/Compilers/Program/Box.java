package Compilers.Program;

import Compilers.KeyWord;
import java.util.HashMap;

public class Box {
    
    private static HashMap<String,Integer> KeysTable;
    private static HashMap<String,Integer> ActionsTable;
    private static HashMap<String,Integer> ConditionsTable;
    
    private static final KeyWord[] KeysArray = Data.KeysArray;
    private static final KeyWord[] ActionsArray = Data.ActionsArray;
    private static final KeyWord[] ConditionsArray = Data.ConditionsArray;

    public Box(KeyWord k, Integer t){
        switch(t){
            
            case 0: KeysTable.put(k.getKey(), k.getObj()); break;
            case 1: ActionsTable.put(k.getKey(), k.getObj()); break;
            case 2: ConditionsTable.put(k.getKey(), k.getObj()); break;

        }
    }
    
    static {
        KeysTable = new HashMap();
            for(KeyWord k : KeysArray)
                new Box(k,0);
        ActionsTable = new HashMap();
            for(KeyWord k : ActionsArray)
                new Box(k,1);
        ConditionsTable = new HashMap();
            for(KeyWord k : ConditionsArray)
                new Box(k,2);
    }
    
    public static Integer gets(String str){
        Integer i = KeysTable.get(str);
        if(i == null){
            Integer j = ActionsTable.get(str);
            if(j == null)
                return ConditionsTable.get(str);
            return j;
        }
        return i;
    }
        
    public static String getType(Integer i){
        for(KeyWord k : KeysArray){
            Integer in = gets(k.getKey());
            if(in == i)
                return k.getKey();
        }
        for(KeyWord k : ActionsArray){
            Integer in = gets(k.getKey());
            if(in == i)
                return k.getKey();
        }
        for(KeyWord k : ConditionsArray){
            Integer in = gets(k.getKey());
            if(in == i)
                return k.getKey();
        }
        return null;
    }
    
    public static boolean isKey(Integer i){
        return KeysTable.containsValue(i);
    }
    
    public static boolean isAction(Integer i){
        return ActionsTable.containsValue(i);
    }
    
    public static boolean isCondition(Integer i){
        return ConditionsTable.containsValue(i);
    }
}