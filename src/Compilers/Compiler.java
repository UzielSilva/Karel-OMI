/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Compilers;
import java.util.HashMap;
import java_cup.runtime.lr_parser;
import java_cup.runtime.Scanner;

/**
 *
 * @author Uziel
 */
public class Compiler{
    private String[] strAry = {null,null};
    
    private HashMap<String, String> map = new HashMap();
    
    public Compiler() {
        map.put("UsrLang", strAry[0]);
        map.put("ProgLang", strAry[1]);
    }
    public void setParam(String index, String prop){
        map.put(index, prop);
    }
    public void getParam(String index){
        map.get(index);
    }
    public Scanner getLexer(java.io.Reader i) throws Exception{
        Scanner s = null;
        if(map.get("ProgLang") == null)
            throw new Exception("Please set programming language first.");
        if(map.get("ProgLang").compareTo("Pascal") == 0)
            s =  new Compilers.Pascal.Lexer(i);
        if(map.get("ProgLang").compareTo("Java") == 0)
            s =  new Compilers.Java.Lexer(i);
        if(s == null)
            throw new Exception("Cannot find programming language: "+strAry[0]);
        if(map.get("UsrLang") == null)
            throw new Exception("Please set user language first.");
        s.setLanguage(map.get("UsrLang"));
        return s;
    }
    public lr_parser getParser(java.io.Reader i) throws Exception{
        Scanner s = this.getLexer(i);
        lr_parser p = null;
        if(map.get("ProgLang").compareTo("Pascal") == 0)
            p =  new Compilers.Pascal.Parser(s);
        if(map.get("ProgLang").compareTo("Java") == 0)
            p =  new Compilers.Java.Parser(s);
        return p;
    }
    
}
