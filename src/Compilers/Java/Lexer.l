package Compilers.Java;

import java_cup.runtime.*;
import Compilers.Box;
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
        public void setLanguage(Box.Language lang) {
                Language = new Box(lang);
  	}
        public Box getBox() {
                return Language;
  	}
%}

%init{
        Language = new Box(Box.JAVAEN);
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