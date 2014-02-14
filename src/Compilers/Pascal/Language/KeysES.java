package Compilers.Pascal.Language;

import Compilers.KeyWord;
import Compilers.KeysDictionary;
import Compilers.Pascal.Symbols;

public class KeysES implements KeysDictionary {
	
	public KeyWord[] getKeysArray(){
	    return KeysArray;
	}
	
	public Integer getError(){
	    return Symbols.error;
	}
	
	public Integer getName(){
	    return Symbols.NAME;
	}
	
	public Integer getNumber(){
	    return Symbols.NUMBER;
	}
	
    public static final KeyWord[] KeysArray = {

        new KeyWord( ";"                            , Symbols.SEMICOL),
        new KeyWord( "("                            , Symbols.LPAREN),
    	new KeyWord( ")"                            , Symbols.RPAREN),

        /* Define key words as needed. */

        new KeyWord( "como"                         , Symbols.AS ),
        new KeyWord( "inicia-ejecucion"             , Symbols.BEGEXEC ),
        new KeyWord( "inicio"                       , Symbols.BEGIN ),
        new KeyWord( "iniciar-programa"             , Symbols.BEGPROG ),
        new KeyWord( "define-nueva-instruccion"     , Symbols.DEFINST ),
        new KeyWord( "hacer"                        , Symbols.DO ),
        new KeyWord( "sino"                         , Symbols.ELSE ),
        new KeyWord( "fin"                          , Symbols.END ),
        new KeyWord( "termina-ejecucion"            , Symbols.ENDEXEC ),
        new KeyWord( "finalizar-programa"           , Symbols.ENDPROG ),
        new KeyWord( "si"                           , Symbols.IF ),
        new KeyWord( "repetir"                      , Symbols.ITERATE ),
        new KeyWord( "entonces"                     , Symbols.THEN ),
        new KeyWord( "veces"                        , Symbols.TIMES ),
        new KeyWord( "mientras"                     , Symbols.WHILE ),
        new KeyWord( "precede"                      , Symbols.PRED ),
        new KeyWord( "sucede"                       , Symbols.SUCC ),
        new KeyWord( "y"                            , Symbols.AND ),
        new KeyWord( "o"                            , Symbols.OR ),
        new KeyWord( "no"                           , Symbols.NOT ),
        new KeyWord( "sal-de-instruccion"           , Symbols.EXITINST ),


    	/* Define action words as needed. */

        new KeyWord( "avanza"                       , Symbols.MOVE),
        new KeyWord( "coge-zumbador"                , Symbols.PICKBPR),
        new KeyWord( "deja-zumbador"                , Symbols.PUTBPR),
        new KeyWord( "gira-izquierda"               , Symbols.TURNLEFT),
        new KeyWord( "apagate"                      , Symbols.TURNOFF),
        
        /* Define conditional words as needed. */
        
        new KeyWord( "frente-libre"                 , Symbols.CFCLEAR ),
        new KeyWord( "frente-bloqueado"             , Symbols.CFBLOCK ),
        new KeyWord( "izquierda-libre"              , Symbols.CLCLEAR ),
        new KeyWord( "izquierda-bloqueada"          , Symbols.CLBLOCK ),
        new KeyWord( "derecha-libre"                , Symbols.CRCLEAR ),
        new KeyWord( "derecha-bloqueada"            , Symbols.CRBLOCK ),
        new KeyWord( "junto-a-zumbador"             , Symbols.NEXTB ),
        new KeyWord( "no-junto-a-zumbador"          , Symbols.NONEXTB ),
        new KeyWord( "algun-zumbador-en-la-mochila" , Symbols.ANYBBAG ),
        new KeyWord( "ningun-zumbador-en-la-mochila", Symbols.NOBBAG ),
        new KeyWord( "orientado-al-norte"           , Symbols.FACINGN ),
        new KeyWord( "orientado-al-sur"             , Symbols.FACINGS ),
        new KeyWord( "orientado-al-este"            , Symbols.FACINGE ),
        new KeyWord( "orientado-al-oeste"           , Symbols.FACINGW ),
        new KeyWord( "no-orientado-al-norte"        , Symbols.NOFACINGN ),
        new KeyWord( "no-orientado-al-sur"          , Symbols.NOFACINGS ),
        new KeyWord( "no-orientado-al-este"         , Symbols.NOFACINGE ),
        new KeyWord( "no-orientado-al-oeste"        , Symbols.NOFACINGW ),
        new KeyWord( "si-es-cero"                   , Symbols.IFZERO),
    };
}