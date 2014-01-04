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

        new KeyWord( "MOVE"                         , SymbolsProgram.MOVE),
        new KeyWord( "PICKBPR"                      , SymbolsProgram.PICKBPR),
        new KeyWord( "PUTBPR"                       , SymbolsProgram.PUTBPR),
        new KeyWord( "TURNLEFT"                     , SymbolsProgram.TURNLEFT),
        new KeyWord( "TURNOFF"                      , SymbolsProgram.TURNOFF),
    };
    
    protected static final KeyWord[] ConditionsArray = {
        
        /* Define conditional words as needed. */
        
        new KeyWord( "CFCLEAR"                      , SymbolsProgram.CFCLEAR ),
        new KeyWord( "CFBLOCK"                      , SymbolsProgram.CFBLOCK ),
        new KeyWord( "CLCLEAR"                      , SymbolsProgram.CLCLEAR ),
        new KeyWord( "CLBLOCK"                      , SymbolsProgram.CLBLOCK ),
        new KeyWord( "CRCLEAR"                      , SymbolsProgram.CRCLEAR ),
        new KeyWord( "CRBLOCK"                      , SymbolsProgram.CRBLOCK ),
        new KeyWord( "NEXTB"                        , SymbolsProgram.NEXTB ),
        new KeyWord( "NONEXTB"                      , SymbolsProgram.NONEXTB ),
        new KeyWord( "ANYBBAG"                      , SymbolsProgram.ANYBBAG ),
        new KeyWord( "NOBBAG"                       , SymbolsProgram.NOBBAG ),
        new KeyWord( "FACINGN"                      , SymbolsProgram.FACINGN ),
        new KeyWord( "FACINGS"                      , SymbolsProgram.FACINGS ),
        new KeyWord( "FACINGE"                      , SymbolsProgram.FACINGE ),
        new KeyWord( "FACINGW"                      , SymbolsProgram.FACINGW ),
        new KeyWord( "NOFACINGN"                    , SymbolsProgram.NOFACINGN ),
        new KeyWord( "NOFACINGS"                    , SymbolsProgram.NOFACINGS ),
        new KeyWord( "NOFACINGE"                    , SymbolsProgram.NOFACINGE ),
        new KeyWord( "NOFACINGW"                    , SymbolsProgram.NOFACINGW ),
        new KeyWord( "IFZERO"                       , SymbolsProgram.IFZERO ),

    };
        
}
