/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CompilerPascalES;

/**
 *
 * @author uziel
 */
class ProgramData {
    
    protected static final KeyWord[] KeysArray = {

        /* Define key words as needed. */
        
        new KeyWord( "PARAM"                        , SymbolsProgram.PARAM ),
        new KeyWord( "CALL"                         , SymbolsProgram.CALL ),
        new KeyWord( "PARAMCALL"                    , SymbolsProgram.PARAMCALL ),
        new KeyWord( "CONDBRANCH"                   , SymbolsProgram.CONDBRANCH ),
        new KeyWord( "ITERATE"                      , SymbolsProgram.ITERATE ),
        new KeyWord( "BRANCH"                       , SymbolsProgram.BRANCH ),
        new KeyWord( "PRED"                         , SymbolsProgram.PRED ),
        new KeyWord( "SUCC"                         , SymbolsProgram.SUCC ),
        new KeyWord( "AND"                          , SymbolsProgram.AND ),
        new KeyWord( "OR"                           , SymbolsProgram.OR ),
        new KeyWord( "NOT"                          , SymbolsProgram.NOT ),
        new KeyWord( "RETURN"                       , SymbolsProgram.RETURN ),


    };
    
    protected static final KeyWord[] ActionsArray = {
        
    	/* Define action words as needed. */

        new KeyWord( "MOVE"                         , SymbolsPascal.MOVE),
        new KeyWord( "PICKBPR"                      , SymbolsPascal.PICKBPR),
        new KeyWord( "PUTBPR"                       , SymbolsPascal.PUTBPR),
        new KeyWord( "TURNLEFT"                     , SymbolsPascal.TURNLEFT),
        new KeyWord( "TURNOFF"                      , SymbolsPascal.TURNOFF),
    };
    
    protected static final KeyWord[] ConditionsArray = {
        
        /* Define conditional words as needed. */
        
        new KeyWord( "CFCLEAR"                      , SymbolsPascal.CFCLEAR ),
        new KeyWord( "CFBLOCK"                      , SymbolsPascal.CFBLOCK ),
        new KeyWord( "CLCLEAR"                      , SymbolsPascal.CLCLEAR ),
        new KeyWord( "CLBLOCK"                      , SymbolsPascal.CLBLOCK ),
        new KeyWord( "CRCLEAR"                      , SymbolsPascal.CRCLEAR ),
        new KeyWord( "CRBLOCK"                      , SymbolsPascal.CRBLOCK ),
        new KeyWord( "NEXTB"                        , SymbolsPascal.NEXTB ),
        new KeyWord( "NONEXTB"                      , SymbolsPascal.NONEXTB ),
        new KeyWord( "ANYBBAG"                      , SymbolsPascal.ANYBBAG ),
        new KeyWord( "NOBBAG"                       , SymbolsPascal.NOBBAG ),
        new KeyWord( "FACINGN"                      , SymbolsPascal.FACINGN ),
        new KeyWord( "FACINGS"                      , SymbolsPascal.FACINGS ),
        new KeyWord( "FACINGE"                      , SymbolsPascal.FACINGE ),
        new KeyWord( "FACINGW"                      , SymbolsPascal.FACINGW ),
        new KeyWord( "NOFACINGN"                    , SymbolsPascal.NOFACINGN ),
        new KeyWord( "NOFACINGS"                    , SymbolsPascal.NOFACINGS ),
        new KeyWord( "NOFACINGE"                    , SymbolsPascal.NOFACINGE ),
        new KeyWord( "NOFACINGW"                    , SymbolsPascal.NOFACINGW ),
        new KeyWord( "IFZERO"                       , SymbolsPascal.IFZERO ),

    };
        
}
