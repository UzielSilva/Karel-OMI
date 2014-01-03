/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CompilerPascalES;

import java.util.Stack;

/**
 *
 * @author uziel
 */
public class Program {
    
    private String[] CommandList;
    
    private Integer[] LineList;
    
    private Integer Counter;
    
    private Stack<Integer> Parameter;
    
    private Stack<Integer> Dirs;
    
    private EnvironmentK Env;
    
    private Boolean TurnedOn;
    
    public Program(String[] s, Integer[] i, EnvironmentK e){
        Dirs = new Stack();
        Parameter = new Stack();
        LineList = i;
        CommandList = s; 
        Counter = LineList[0];
        Env = e;
        TurnedOn = true;
    }
    
    public Program(String[] s, EnvironmentK e){
        Dirs = new Stack();
        Parameter = new Stack();
        CommandList = s;
        Counter = LineList[0];
        Env = e;
        TurnedOn = true;

    }
    
    public Integer getCurrentLine(){
        if(LineList == null) return null;
        else return LineList[Counter];
    }
    
    public Integer nextAction(){
        if(!TurnedOn)
            return null;
        Integer state = ProgramBox.gets(CommandList[Counter++]);
        if(SymbolsProgram.TURNOFF == state){
            TurnedOn = false;
            return state;
        }
        if(ProgramBox.isAction(state))
            return state;
        if(ProgramBox.isCondition(state)){
            if(executeConditional(state,true,0)){
                Counter += 2;
                return nextAction();
            }else{
                Counter = Integer.parseInt(CommandList[Counter+1]);
                return nextAction();
            }
        }
        if(SymbolsProgram.BRANCH == state){
            Counter = Integer.parseInt(CommandList[Counter]);
            return nextAction();
        }
        if(SymbolsProgram.CALL == state){
            Dirs.push(Counter + 1);
            Parameter.push(0);
            Counter = Integer.parseInt(CommandList[Counter]);
            return nextAction();
        }
        if(SymbolsProgram.PARAMCALL == state){
            Integer i = Integer.parseInt(CommandList[Counter]);
            Dirs.push(Counter + 2);
            Counter++;
            Parameter.push(executeNumber());
            Counter = i;
            return nextAction();
        }
        if(SymbolsProgram.RETURN == state){
            Counter = Dirs.pop();
            Parameter.pop();
            return nextAction();
        }
        return null;
    }
    
    private Boolean executeConditional(Integer state, Boolean pred, Integer act){
        boolean b = true;
        boolean not = false;
        
        if(state == SymbolsProgram.NOT)
            not = true;
        
        switch(state){
            
            case SymbolsProgram.CFCLEAR: Counter++; b = Env.isFrontClear(); break;
            case SymbolsProgram.CFBLOCK: Counter++; b = !Env.isFrontClear(); break;
            case SymbolsProgram.CLCLEAR: Counter++; b = Env.isLeftClear(); break;
            case SymbolsProgram.CLBLOCK: Counter++; b = !Env.isLeftClear(); break;
            case SymbolsProgram.CRCLEAR: Counter++; b = Env.isRightClear(); break;
            case SymbolsProgram.CRBLOCK: Counter++; b = !Env.isRightClear(); break;
            case SymbolsProgram.NEXTB: Counter++; b = Env.isNextToABeeper(); break;
            case SymbolsProgram.NONEXTB: Counter++; b = !Env.isNextToABeeper(); break;
            case SymbolsProgram.ANYBBAG: Counter++; b = Env.isThereAnyBeepersInBag(); break;
            case SymbolsProgram.NOBBAG: Counter++; b = !Env.isThereAnyBeepersInBag(); break;
            case SymbolsProgram.FACINGN: Counter++; b = Env.isFacingNorth(); break;
            case SymbolsProgram.FACINGS: Counter++; b = Env.isFacingSouth(); break;
            case SymbolsProgram.FACINGE: Counter++; b = Env.isFacingEast(); break;
            case SymbolsProgram.FACINGW: Counter++; b = Env.isFacingWest(); break;
            case SymbolsProgram.NOFACINGN: Counter++; b = !Env.isFacingNorth(); break;
            case SymbolsProgram.NOFACINGS: Counter++; b = !Env.isFacingSouth(); break;
            case SymbolsProgram.NOFACINGE: Counter++; b = !Env.isFacingEast(); break;
            case SymbolsProgram.NOFACINGW: Counter++; b = !Env.isFacingWest(); break;
                
            case SymbolsPascal.IFZERO: Counter++; b = (executeNumber() == 0); break;

        }
        
        if(not)
            b = !b;
        
        if(act == 0)
            b = (pred && b);
        else
            b = (pred || b);
        
        if(ProgramBox.gets(CommandList[Counter]) == SymbolsProgram.AND)
            return executeConditional(ProgramBox.gets(CommandList[++Counter]),b,0);
        if(ProgramBox.gets(CommandList[Counter]) == SymbolsProgram.OR)
            return executeConditional(ProgramBox.gets(CommandList[++Counter]),b,1);
        if(ProgramBox.gets(CommandList[Counter]) == SymbolsProgram.CONDBRANCH)
            return b;
        return null;
    }
    
    private Integer executeNumber(){
        Integer i = ProgramBox.gets(CommandList[Counter]);
        if(i == null)
            return Integer.parseInt(CommandList[Counter++]);
        if(i == SymbolsProgram.PARAM)
            return Parameter.peek();
        if(i == SymbolsProgram.PRED){
            Counter++;
            return (executeNumber() - 1);
        }
        if(i == SymbolsProgram.SUCC){
            Counter++;
            return (executeNumber() + 1);
        }
        return null;
    }
    
}

/**
 *
 * @author Uziel
 */
