package Compilers;

public class KeyWord {

    private final String Key;
    private final Integer Obj;

    public KeyWord(String K, Integer O){
        Key = K;
        Obj = O;
    }

    public String getKey(){
        return Key;
    }

    public Integer getObj(){
        return Obj;
    }
    
}