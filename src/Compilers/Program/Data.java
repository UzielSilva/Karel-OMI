/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Compilers.Program;

import Compilers.KeyWord;

/**
 *
 * @author uziel
 */
class Data {
    
    protected static final KeyWord[] KeysArray = {

        /* Define key words as needed. */
        
        new KeyWord( "PARAM"                        , Symbols.PARAM ),
        new KeyWord( "CALL"                         , Symbols.CALL ),
        new KeyWord( "PARAMCALL"                    , Symbols.PARAMCALL ),
        new KeyWord( "CONDBRANCH"                   , Symbols.CONDBRANCH ),
        new KeyWord( "ITERATE"                      , Symbols.ITERATE ),
        new KeyWord( "BRANCH"                       , Symbols.BRANCH ),
        new KeyWord( "PRED"                         , Symbols.PRED ),
        new KeyWord( "SUCC"                         , Symbols.SUCC ),
        new KeyWord( "AND"                          , Symbols.AND ),
        new KeyWord( "OR"                           , Symbols.OR ),
        new KeyWord( "NOT"                          , Symbols.NOT ),
        new KeyWord( "RETURN"                       , Symbols.RETURN ),


    };
    
    protected static final KeyWord[] ActionsArray = {
        
    	/* Define action words as needed. */

        new KeyWord( "MOVE"                         , Symbols.MOVE),
        new KeyWord( "PICKBPR"                      , Symbols.PICKBPR),
        new KeyWord( "PUTBPR"                       , Symbols.PUTBPR),
        new KeyWord( "TURNLEFT"                     , Symbols.TURNLEFT),
        new KeyWord( "TURNOFF"                      , Symbols.TURNOFF),
    };
    
    protected static final KeyWord[] ConditionsArray = {
        
        /* Define conditional words as needed. */
        
        new KeyWord( "CFCLEAR"                      , Symbols.CFCLEAR ),
        new KeyWord( "CFBLOCK"                      , Symbols.CFBLOCK ),
        new KeyWord( "CLCLEAR"                      , Symbols.CLCLEAR ),
        new KeyWord( "CLBLOCK"                      , Symbols.CLBLOCK ),
        new KeyWord( "CRCLEAR"                      , Symbols.CRCLEAR ),
        new KeyWord( "CRBLOCK"                      , Symbols.CRBLOCK ),
        new KeyWord( "NEXTB"                        , Symbols.NEXTB ),
        new KeyWord( "NONEXTB"                      , Symbols.NONEXTB ),
        new KeyWord( "ANYBBAG"                      , Symbols.ANYBBAG ),
        new KeyWord( "NOBBAG"                       , Symbols.NOBBAG ),
        new KeyWord( "FACINGN"                      , Symbols.FACINGN ),
        new KeyWord( "FACINGS"                      , Symbols.FACINGS ),
        new KeyWord( "FACINGE"                      , Symbols.FACINGE ),
        new KeyWord( "FACINGW"                      , Symbols.FACINGW ),
        new KeyWord( "NOFACINGN"                    , Symbols.NOFACINGN ),
        new KeyWord( "NOFACINGS"                    , Symbols.NOFACINGS ),
        new KeyWord( "NOFACINGE"                    , Symbols.NOFACINGE ),
        new KeyWord( "NOFACINGW"                    , Symbols.NOFACINGW ),
        new KeyWord( "IFZERO"                       , Symbols.IFZERO ),

    };
        
}
