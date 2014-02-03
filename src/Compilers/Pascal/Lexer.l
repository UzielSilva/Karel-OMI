package Compilers.Pascal;

import java_cup.runtime.*;
import Compilers.Box;
import java.util.*;
import java.io.*;
import java.awt.Point;

%%
%{
    private Symbol symbol(int type) {
		return new Symbol(type, yyline, yycolumn);
	}
	private Symbol symbol(int type, Object value) {
    	return new Symbol(type, yyline, yycolumn, value);
  	}
        private Box Language;
        public void setLanguage(Box.Language lang) {
                Language = new Box(lang);
  	}
        public Box getBox() {
                return Language;
  	}
%}

%init{
        Language = new Box(Box.ES);
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
%state PASCALCOMMENT
%state JAVACOMMENT
%state KEYCOMMENT

/* Instruction names */

Semicolon = 	";"
LeftParent = 	"("
RightParent =	")"
Number =		[0-9]+
Word = 			[a-zA-Z][a-zA-Z0-9\-]*
LineComment = "//"
PLeftComment =  "(*"
PRightComment = "*)"
JLeftComment = "/*"
JRightComment = "*/"
KLeftComment =  "{"
KRightComment = "}"
%%

<YYINITIAL> {
    {LineComment} { 
        yybegin(LINECOMMENT);
        return symbol(Symbols.COMMENT);
    }
    
    {PLeftComment} { 
        yybegin(PASCALCOMMENT);
        return symbol(Symbols.COMMENT);
    }
    
    {JLeftComment} { 
        yybegin(JAVACOMMENT);
        return symbol(Symbols.COMMENT);
    }
    
    {KLeftComment} { 
        yybegin(KEYCOMMENT);
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
    
    [\t\n ] {}
}

<LINECOMMENT> {
    .* {
        return symbol(Symbols.COMMENT);
    }
    <<EOF>> {
        yybegin(YYINITIAL);
    }
    \n {
        yybegin(YYINITIAL);
    }
    
}

<PASCALCOMMENT> {
    .*{PRightComment} {
        yybegin(YYINITIAL);
        return symbol(Symbols.COMMENT);
    }
    .*\n {
        return symbol(Symbols.COMMENT);
    }
    <<EOF>> {
        throw new java.lang.Exception("Unclosed comment. Unexpected EOF.");
    }
    .* {
        return symbol(Symbols.COMMENT);
    }
    
}

<JAVACOMMENT> {
    .*{JRightComment} {
        yybegin(YYINITIAL);
        return symbol(Symbols.COMMENT);
    }
    .*\n {
        return symbol(Symbols.COMMENT);
    }
    <<EOF>> {
        throw new java.lang.Exception("Unclosed comment. Unexpected EOF.");
    }
    .* {
        return symbol(Symbols.COMMENT);
    }
    
}

<KEYCOMMENT> {
    .*{KRightComment} {
        yybegin(YYINITIAL);
        return symbol(Symbols.COMMENT);
    }
    .*\n {
        return symbol(Symbols.COMMENT);
    }
    <<EOF>> {
        throw new java.lang.Exception("Unclosed comment. Unexpected EOF.");
    }
    .* {
        return symbol(Symbols.COMMENT);
    }
    
}

. {
	throw new java.lang.Exception("Invalid token: \"" + yytext() + "\" at line: " + (yyline + 1));
}

<<EOF>> {
    return new Symbol(Symbols.EOF);
}