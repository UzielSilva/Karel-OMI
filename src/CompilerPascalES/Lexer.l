package CompilerPascalES;

import java_cup.runtime.*;
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

%}

%init{

%init}

%class LexerPascal
%public
%ignorecase
%line
%column
%cup
%char

/* Instruction names */

Semicolon = 	";"
LeftParent = 	"("
RightParent =	")"
Number =		[0-9]+
Word = 			[a-zA-Z][a-zA-Z0-9\-]*
%%

{Word} {
	Integer k = (PascalBox.gets(yytext()));
	if (k != null) 
		return symbol(k);
	return symbol(SymbolsPascal.NAME,yytext());
}

{Number} {
	return symbol(SymbolsPascal.NUMBER,Integer.parseInt(yytext()));
}

{Semicolon} {
	return symbol(SymbolsPascal.SEMICOL);
}

{LeftParent} {
	return symbol(SymbolsPascal.LPAREN);
}

{RightParent} {
	return symbol(SymbolsPascal.RPAREN);
}

[\t\n ] {}

. {
	System.err.println("Invalid token: \"" + yytext() + "\" at line: " + yyline);
	return symbol(SymbolsPascal.error,yytext());
	// Manual adjust EOF symbol in generated java
}

