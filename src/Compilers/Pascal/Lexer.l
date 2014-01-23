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
    public void setLanguage(Box.Language lang) {
                Language = new Box(lang);
  	}
    private Box Language;

    public static void main(String[] args) {
    	Scanner in = net Scanner(System.in);
    	Lexer l =
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

%state COMMENT

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

. {
	throw new java.lang.Exception("Invalid token: \"" + yytext() + "\" at line: " + (yyline + 1));
}

