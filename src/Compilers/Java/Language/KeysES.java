package Compilers.Java.Language;

import Compilers.KeyWord;
import Compilers.Java.Symbols;

public class KeysES{
	
    public static final KeyWord[] KeysArray = {

        new KeyWord( "error"                        , Symbols.error),
        new KeyWord( ";"                            , Symbols.SEMICOL),
        new KeyWord( "("                            , Symbols.LPAREN),
    	new KeyWord( ")"                            , Symbols.RPAREN),
        new KeyWord( "{"                            , Symbols.LKEY),
    	new KeyWord( "}"                            , Symbols.RKEY),

        /* Define key words as needed. */

        new KeyWord( "clase"                        , Symbols.CLASS ),
        new KeyWord( "void"                         , Symbols.VOID ),
        new KeyWord( "define"                       , Symbols.VOID ),
        new KeyWord( "sino"                         , Symbols.ELSE ),
        new KeyWord( "si"                           , Symbols.IF ),
        new KeyWord( "repetir"                      , Symbols.ITERATE ),
        new KeyWord( "mientras"                     , Symbols.WHILE ),
        new KeyWord( "&&"                           , Symbols.AND ),
        new KeyWord( "||"                           , Symbols.OR ),
        new KeyWord( "!"                            , Symbols.NOT ),
        new KeyWord( "precede"                      , Symbols.PRED ),
        new KeyWord( "sucede"                       , Symbols.SUCC ),
        new KeyWord( "regresa"                      , Symbols.EXITINST ),


    	/* Define action words as needed. */

        new KeyWord( "avanza"                       , Symbols.MOVE),
        new KeyWord( "cogeZumbador"                 , Symbols.PICKBPR),
        new KeyWord( "dejaZumbador"                 , Symbols.PUTBPR),
        new KeyWord( "giraIzquierda"                , Symbols.TURNLEFT),
        new KeyWord( "apagate"                      , Symbols.TURNOFF),
        
        /* Define conditional words as needed. */
        
        new KeyWord( "frenteLibre"                  , Symbols.CFCLEAR ),
        new KeyWord( "frenteBloqueado"              , Symbols.CFBLOCK ),
        new KeyWord( "izquierdaLibre"               , Symbols.CLCLEAR ),
        new KeyWord( "izquierdaBloqueada"           , Symbols.CLBLOCK ),
        new KeyWord( "derechaLibre"                 , Symbols.CRCLEAR ),
        new KeyWord( "derechaBloqueada"             , Symbols.CRBLOCK ),
        new KeyWord( "juntoAZumbador"               , Symbols.NEXTB ),
        new KeyWord( "noJuntoAZumbador"             , Symbols.NONEXTB ),
        new KeyWord( "algunZumbadorEnLaMochila"     , Symbols.ANYBBAG ),
        new KeyWord( "ningunZumbadorEnLaMochila"    , Symbols.NOBBAG ),
        new KeyWord( "orientadoAlNorte"             , Symbols.FACINGN ),
        new KeyWord( "orientadoAlSur"               , Symbols.FACINGS ),
        new KeyWord( "orientadoAlEste"              , Symbols.FACINGE ),
        new KeyWord( "orientadoAlOeste"             , Symbols.FACINGW ),
        new KeyWord( "noOrientadoAlNorte"           , Symbols.NOFACINGN ),
        new KeyWord( "noOrientadoAlSur"             , Symbols.NOFACINGS ),
        new KeyWord( "noOrientadoAlEste"            , Symbols.NOFACINGE ),
        new KeyWord( "noOrientadoAlOeste"           , Symbols.NOFACINGW ),
        new KeyWord( "siEsCero"                     , Symbols.IFZERO),

        new KeyWord( "name"                         , Symbols.NAME ),
        new KeyWord( "number"                       , Symbols.NUMBER ),
    };
}