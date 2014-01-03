package CompilerPascalES;

import java.util.HashMap;

class Global{
	
    protected static final KeyWord[] KeysArray = {

        new KeyWord( "error"                        , SymbolsPascal.error),
        new KeyWord( ";"                            , SymbolsPascal.SEMICOL),
        new KeyWord( "("                            , SymbolsPascal.LPAREN),
    	new KeyWord( ")"                            , SymbolsPascal.RPAREN),

        /* Define key words as needed. */

        new KeyWord( "como"                         , SymbolsPascal.AS ),
        new KeyWord( "inicia-ejecucion"             , SymbolsPascal.BEGEXEC ),
        new KeyWord( "inicio"                       , SymbolsPascal.BEGIN ),
        new KeyWord( "iniciar-programa"             , SymbolsPascal.BEGPROG ),
        new KeyWord( "define-nueva-instruccion"     , SymbolsPascal.DEFINST ),
        new KeyWord( "hacer"                        , SymbolsPascal.DO ),
        new KeyWord( "sino"                         , SymbolsPascal.ELSE ),
        new KeyWord( "fin"                          , SymbolsPascal.END ),
        new KeyWord( "termina-ejecucion"            , SymbolsPascal.ENDEXEC ),
        new KeyWord( "finalizar-programa"           , SymbolsPascal.ENDPROG ),
        new KeyWord( "si"                           , SymbolsPascal.IF ),
        new KeyWord( "repetir"                      , SymbolsPascal.ITERATE ),
        new KeyWord( "entonces"                     , SymbolsPascal.THEN ),
        new KeyWord( "veces"                        , SymbolsPascal.TIMES ),
        new KeyWord( "mientras"                     , SymbolsPascal.WHILE ),
        new KeyWord( "precede"                      , SymbolsPascal.PRED ),
        new KeyWord( "sucede"                       , SymbolsPascal.SUCC ),
        new KeyWord( "y"                            , SymbolsPascal.AND ),
        new KeyWord( "o"                            , SymbolsPascal.OR ),
        new KeyWord( "no"                           , SymbolsPascal.OR ),
        new KeyWord( "sal-de-instruccion"           , SymbolsPascal.EXITINST ),


    	/* Define action words as needed. */

        new KeyWord( "avanza"                       , SymbolsPascal.MOVE),
        new KeyWord( "coge-zumbador"                , SymbolsPascal.PICKBPR),
        new KeyWord( "deja-zumbador"                , SymbolsPascal.PUTBPR),
        new KeyWord( "gira-izquierda"               , SymbolsPascal.TURNLEFT),
        new KeyWord( "apagate"                      , SymbolsPascal.TURNOFF),
        
        /* Define conditional words as needed. */
        
        new KeyWord( "frente-libre"                 , SymbolsPascal.CFCLEAR ),
        new KeyWord( "frente-bloqueado"             , SymbolsPascal.CFBLOCK ),
        new KeyWord( "izquierda-libre"              , SymbolsPascal.CLCLEAR ),
        new KeyWord( "izquierda-bloqueada"          , SymbolsPascal.CLBLOCK ),
        new KeyWord( "derecha-libre"                , SymbolsPascal.CRCLEAR ),
        new KeyWord( "detecha-bloqueada"            , SymbolsPascal.CRBLOCK ),
        new KeyWord( "junto-a-zumbador"             , SymbolsPascal.NEXTB ),
        new KeyWord( "no-junto-a-zumbador"          , SymbolsPascal.NONEXTB ),
        new KeyWord( "algun-zumbador-en-la-mochila" , SymbolsPascal.ANYBBAG ),
        new KeyWord( "ningun-zumbador-en-la-mochila", SymbolsPascal.NOBBAG ),
        new KeyWord( "orientado-al-norte"           , SymbolsPascal.FACINGN ),
        new KeyWord( "orientado-al-sur"             , SymbolsPascal.FACINGS ),
        new KeyWord( "orientado-al-este"            , SymbolsPascal.FACINGE ),
        new KeyWord( "orientado-al-oeste"           , SymbolsPascal.FACINGW ),
        new KeyWord( "no-orientado-al-norte"        , SymbolsPascal.NOFACINGN ),
        new KeyWord( "no-orientado-al-sur"          , SymbolsPascal.NOFACINGS ),
        new KeyWord( "no-orientado-al-este"         , SymbolsPascal.NOFACINGE ),
        new KeyWord( "no-orientado-al-oeste"        , SymbolsPascal.NOFACINGW ),

        new KeyWord( "name"                         , SymbolsPascal.NAME ),
        new KeyWord( "number"                       , SymbolsPascal.NUMBER ),
    };
}