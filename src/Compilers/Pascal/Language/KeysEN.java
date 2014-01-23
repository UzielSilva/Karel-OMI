package Compilers.Pascal.Language;

import Compilers.KeyWord;
import Compilers.Pascal.Symbols;

public class KeysEN{
	
    public static final KeyWord[] KeysArray = {

        new KeyWord( "error"                        , Symbols.error),
        new KeyWord( ";"                            , Symbols.SEMICOL),
        new KeyWord( "("                            , Symbols.LPAREN),
    	new KeyWord( ")"                            , Symbols.RPAREN),

        /* Define key words as needed. */

        new KeyWord( "as"                           , Symbols.AS ),
        new KeyWord( "beginning-of-execution"       , Symbols.BEGEXEC ),
        new KeyWord( "begin"                        , Symbols.BEGIN ),
        new KeyWord( "beginning-of-program"         , Symbols.BEGPROG ),
        new KeyWord( "define-new-instruction"       , Symbols.DEFINST ),
        new KeyWord( "do"                           , Symbols.DO ),
        new KeyWord( "else"                         , Symbols.ELSE ),
        new KeyWord( "end"                          , Symbols.END ),
        new KeyWord( "end-of-execution"             , Symbols.ENDEXEC ),
        new KeyWord( "end-of-program"               , Symbols.ENDPROG ),
        new KeyWord( "if"                           , Symbols.IF ),
        new KeyWord( "iterate"                      , Symbols.ITERATE ),
        new KeyWord( "then"                         , Symbols.THEN ),
        new KeyWord( "times"                        , Symbols.TIMES ),
        new KeyWord( "while"                        , Symbols.WHILE ),
        new KeyWord( "pred"                         , Symbols.PRED ),
        new KeyWord( "succ"                         , Symbols.SUCC ),
        new KeyWord( "and"                          , Symbols.AND ),
        new KeyWord( "or"                           , Symbols.OR ),
        new KeyWord( "not"                          , Symbols.NOT ),
        new KeyWord( "return"                       , Symbols.EXITINST ),


    	/* Define action words as needed. */

        new KeyWord( "move"                         , Symbols.MOVE),
        new KeyWord( "pickbeeper"                   , Symbols.PICKBPR),
        new KeyWord( "putbeeper"                    , Symbols.PUTBPR),
        new KeyWord( "turnleft"                     , Symbols.TURNLEFT),
        new KeyWord( "turnoff"                      , Symbols.TURNOFF),
        
        /* Define conditional words as needed. */
        
        new KeyWord( "front-is-clear"               , Symbols.CFCLEAR ),
        new KeyWord( "front-is-blocked"             , Symbols.CFBLOCK ),
        new KeyWord( "left-is-clear"                , Symbols.CLCLEAR ),
        new KeyWord( "left-is-blocked"              , Symbols.CLBLOCK ),
        new KeyWord( "right-is-clear"               , Symbols.CRCLEAR ),
        new KeyWord( "right-is-blocked"             , Symbols.CRBLOCK ),
        new KeyWord( "next-to-a-beeper"             , Symbols.NEXTB ),
        new KeyWord( "not-next-to-a-beeper"         , Symbols.NONEXTB ),
        new KeyWord( "any-beepers-in-beeper-bag"    , Symbols.ANYBBAG ),
        new KeyWord( "no-beepers-in-beeper-bag"     , Symbols.NOBBAG ),
        new KeyWord( "facing-north"                 , Symbols.FACINGN ),
        new KeyWord( "facing-south"                 , Symbols.FACINGS ),
        new KeyWord( "facing-east"                  , Symbols.FACINGE ),
        new KeyWord( "facing-west"                  , Symbols.FACINGW ),
        new KeyWord( "not-facing-north"             , Symbols.NOFACINGN ),
        new KeyWord( "not-facing-south"             , Symbols.NOFACINGS ),
        new KeyWord( "not-facing-east"              , Symbols.NOFACINGE ),
        new KeyWord( "not-facing-west"              , Symbols.NOFACINGW ),
        new KeyWord( "iszero"                       , Symbols.IFZERO),

        new KeyWord( "name"                         , Symbols.NAME ),
        new KeyWord( "number"                       , Symbols.NUMBER ),
    };
}