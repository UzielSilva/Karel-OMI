package Compilers.Java;

import java_cup.runtime.*;
import Compilers.Box;
import Compilers.KeyWord;
import Compilers.KeysDictionary;
import java.util.*;
import java.io.*;
import java.awt.Point;

%%
%{
    StringBuffer comment = new StringBuffer();
    
    private Symbol symbol(int type) {
		return new Symbol(type, yyline, yycolumn);
	}
	private Symbol symbol(int type, Object value) {
    	return new Symbol(type, yyline, yycolumn, value);
  	}
        private Box Language;
        private HashMap<String,KeysDictionary> map = new HashMap();
        public void setLanguage(String lang) throws Exception {
            if (lang == null)
                throw new Exception("Please use a valid String");
            KeysDictionary target = map.get(lang);
            if (target == null)
                throw new Exception("Language "+ lang +" is not supported.");
            Language = new Box(target);
  	}
        public String[] getAvaliableLanguages(){
            return map.keySet().toArray(new String[0]);
        }
        public Box getBox() {
                return Language;
  	}
%}

%init{
          map.put("ES", new Compilers.Java.Language.KeysES());
          map.put("EN", new Compilers.Java.Language.KeysEN());
%init}

%class Lexer
%public
%ignorecase
%line
%column
%cup
%char
%scanerror java.lang.Exception

%state LINECOMMENT
%state JAVACOMMENT

/* Instruction names */

Semicolon = 	";"
LeftParent = 	"("
RightParent =	")"
KeyLeft =       "{"
KeyRight =      "}"
Number =		[0-9]+
Word = 			[a-zA-Z][a-zA-Z0-9\-]*
LineComment =   "//"
JLeftComment =  "/*"
JRightComment = "*/"
%%

<YYINITIAL> {
    {LineComment} { 
        comment.setLength(0);
        yybegin(LINECOMMENT);
        return symbol(Symbols.COMMENT);
    }
    
    {JLeftComment} { 
        comment.setLength(0);
        yybegin(JAVACOMMENT);
        return symbol(Symbols.COMMENT);
    }
    
    {Word} {
    	Integer k = (Language.gets(yytext()));
    	if (k != null) 
    		return symbol(k);
    	return symbol(Symbols.NAME,yytext());
    }
    
    {Number} {
    	return symbol(Symbols.NUMBER,Integer.parseInt(yytext()));
    }
    
    {Semicolon} {
    	return symbol(Symbols.SEMICOL);
    }
    
    {LeftParent} {
    	return symbol(Symbols.LPAREN);
    }
    
    {RightParent} {
    	return symbol(Symbols.RPAREN);
    }
    
    {KeyLeft} {
    	return symbol(Symbols.LKEY);
    }
    
    {KeyRight} {
    	return symbol(Symbols.RKEY);
    }
    
    [\t\n ] {}
}

<LINECOMMENT> {
    . {
        comment.append( yytext() );
    }
    <<EOF>> {
        yybegin(YYINITIAL);
        return symbol(Symbols.COMMENT, comment.toString());
    }
    \n {
        yybegin(YYINITIAL);
        return symbol(Symbols.COMMENT, comment.toString());
    }
    
}

<JAVACOMMENT> {
    {JRightComment} {
        yybegin(YYINITIAL);
        return symbol(Symbols.COMMENT, (comment.toString() + yytext()));
    }
    (.|\n) {
        comment.append( yytext() );
    }
    <<EOF>> {
        throw new java.lang.Exception("Unclosed comment. Unexpected EOF.");
    }
}

. {
	throw new java.lang.Exception("Invalid token: \"" + yytext() + "\" at line: " + (yyline + 1));
}

<<EOF>> {
    return new Symbol(Symbols.EOF);
}