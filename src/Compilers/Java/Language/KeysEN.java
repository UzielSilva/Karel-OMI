package Compilers.Java.Language;

import Compilers.KeyWord;
import Compilers.Java.Symbols;

public class KeysEN{
	
    public static final KeyWord[] KeysArray = {

        new KeyWord( "error"                        , Symbols.error),
        new KeyWord( ";"                            , Symbols.SEMICOL),
        new KeyWord( "("                            , Symbols.LPAREN),
    	new KeyWord( ")"                            , Symbols.RPAREN),
        new KeyWord( "{"                            , Symbols.LKEY),
    	new KeyWord( "}"                            , Symbols.RKEY),

        /* Define key words as needed. */

        new KeyWord( "class"                        , Symbols.CLASS ),
        new KeyWord( "void"                         , Symbols.VOID ),
        new KeyWord( "define"                       , Symbols.DEFINE ),
        new KeyWord( "else"                         , Symbols.ELSE ),
        new KeyWord( "if"                           , Symbols.IF ),
        new KeyWord( "iterate"                      , Symbols.ITERATE ),
        new KeyWord( "while"                        , Symbols.WHILE ),
        new KeyWord( "&&"                           , Symbols.AND ),
        new KeyWord( "||"                           , Symbols.OR ),
        new KeyWord( "!"                            , Symbols.NOT ),
        new KeyWord( "pred"                         , Symbols.PRED ),
        new KeyWord( "succ"                         , Symbols.SUCC ),
        new KeyWord( "return"                       , Symbols.EXITINST ),


    	/* Define action words as needed. */

        new KeyWord( "move"                         , Symbols.MOVE),
        new KeyWord( "pickbeeper"                   , Symbols.PICKBPR),
        new KeyWord( "putbeeper"                    , Symbols.PUTBPR),
        new KeyWord( "turnleft"                     , Symbols.TURNLEFT),
        new KeyWord( "turnoff"                      , Symbols.TURNOFF),
        
        /* Define conditional words as needed. */
        
        new KeyWord( "frontIsClear"                 , Symbols.CFCLEAR ),
        new KeyWord( "frontIsBlocked"               , Symbols.CFBLOCK ),
        new KeyWord( "leftIsClear"                  , Symbols.CLCLEAR ),
        new KeyWord( "leftIsBlocked"                , Symbols.CLBLOCK ),
        new KeyWord( "rightIsClear"                 , Symbols.CRCLEAR ),
        new KeyWord( "rightIsBlocked"               , Symbols.CRBLOCK ),
        new KeyWord( "nextToABeeper"                , Symbols.NEXTB ),
        new KeyWord( "notNextToABeeper"             , Symbols.NONEXTB ),
        new KeyWord( "anyBeepersInBeeperBag"        , Symbols.ANYBBAG ),
        new KeyWord( "noBeepersInBeeperBag"         , Symbols.NOBBAG ),
        new KeyWord( "facingNorth"                  , Symbols.FACINGN ),
        new KeyWord( "facingSouth"                  , Symbols.FACINGS ),
        new KeyWord( "facingEast"                   , Symbols.FACINGE ),
        new KeyWord( "facingWest"                   , Symbols.FACINGW ),
        new KeyWord( "notFacingNorth"               , Symbols.NOFACINGN ),
        new KeyWord( "notFacingSouth"               , Symbols.NOFACINGS ),
        new KeyWord( "notFacingEast"                , Symbols.NOFACINGE ),
        new KeyWord( "notFacingWest"                , Symbols.NOFACINGW ),
        new KeyWord( "iszero"                       , Symbols.IFZERO),

        new KeyWord( "name"                         , Symbols.NAME ),
        new KeyWord( "number"                       , Symbols.NUMBER ),
    };
}