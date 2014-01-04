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

    private Integer IterVal;
    
    private Stack<Integer> IterStack;
    
    private Stack<Integer> Parameter;
    
    private Stack<Integer> Dirs;
    
    private EnvironmentK Env;
    
    private Boolean TurnedOn;
    
    public Program(String[] s, Integer[] i, EnvironmentK e){
        Dirs = new Stack();
        Parameter = new Stack();
        IterStack = new Stack();
        LineList = i;
        CommandList = s; 
        Counter = Integer.parseInt(CommandList[0]);
        Env = e;
        TurnedOn = true;
        IterVal = 0;
    }
    
    public Program(String[] s, EnvironmentK e){
        Dirs = new Stack();
        Parameter = new Stack();
        IterStack = new Stack();
        CommandList = s;
        Counter = Integer.parseInt(CommandList[0]);
        Env = e;
        TurnedOn = true;
        IterVal = 0;
    }
    
    public Integer getCurrentLine(){
        if(LineList == null || !TurnedOn) return null;
        else return LineList[Counter] + 1;
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
                return null;
            }else{
                Counter = Integer.parseInt(CommandList[Counter+1]);
                return null;
            }
        }
        if(SymbolsProgram.ITERATE == state){
            Integer initialState = Counter - 1;
            if(IterVal < executeNumber()){
                Parameter.push(Parameter.peek());
                Dirs.push(initialState);
                IterStack.push(IterVal+1);
                IterVal = 0;
                Counter++;
                return null;
            }
            else{
                Counter = Integer.parseInt(CommandList[Counter]);
                IterVal = 0;
                return null;
            }
        }
        if(SymbolsProgram.BRANCH == state){
            Counter = Integer.parseInt(CommandList[Counter]);
            return null;
        }
        if(SymbolsProgram.CALL == state){
            Dirs.push(Counter + 1);
            Parameter.push(0);
            IterStack.push(0);
            Counter = Integer.parseInt(CommandList[Counter]);
            return null;
        }
        if(SymbolsProgram.PARAMCALL == state){
            Integer i = Integer.parseInt(CommandList[Counter]);
            Dirs.push(Counter + 2);
            Counter++;
            Parameter.push(executeNumber());
            IterStack.push(0);
            Counter = i;
            return null;
        }
        if(SymbolsProgram.RETURN == state){
            Counter = Dirs.pop();
            Parameter.pop();
            IterVal = IterStack.pop();
            return null;
        }
        return null;
    }
    
    private Boolean executeConditional(Integer state, Boolean pred, Integer act){
        boolean b = true;
        boolean not = false;
        
        if(state == SymbolsProgram.NOT)
            not = true;
        
        switch(state){
            
            case SymbolsProgram.CFCLEAR: b = Env.isFrontClear(); break;
            case SymbolsProgram.CFBLOCK: b = !Env.isFrontClear(); break;
            case SymbolsProgram.CLCLEAR: b = Env.isLeftClear(); break;
            case SymbolsProgram.CLBLOCK: b = !Env.isLeftClear(); break;
            case SymbolsProgram.CRCLEAR: b = Env.isRightClear(); break;
            case SymbolsProgram.CRBLOCK: b = !Env.isRightClear(); break;
            case SymbolsProgram.NEXTB: b = Env.isNextToABeeper(); break;
            case SymbolsProgram.NONEXTB: b = !Env.isNextToABeeper(); break;
            case SymbolsProgram.ANYBBAG: b = Env.isThereAnyBeepersInBag(); break;
            case SymbolsProgram.NOBBAG: b = !Env.isThereAnyBeepersInBag(); break;
            case SymbolsProgram.FACINGN: b = Env.isFacingNorth(); break;
            case SymbolsProgram.FACINGS: b = Env.isFacingSouth(); break;
            case SymbolsProgram.FACINGE: b = Env.isFacingEast(); break;
            case SymbolsProgram.FACINGW: b = Env.isFacingWest(); break;
            case SymbolsProgram.NOFACINGN: b = !Env.isFacingNorth(); break;
            case SymbolsProgram.NOFACINGS: b = !Env.isFacingSouth(); break;
            case SymbolsProgram.NOFACINGE: b = !Env.isFacingEast(); break;
            case SymbolsProgram.NOFACINGW: b = !Env.isFacingWest(); break;
                
            case SymbolsPascal.IFZERO: b = (executeNumber() == 0); break;

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
        if(i == SymbolsProgram.PARAM){
            Counter++;
            return Parameter.peek();
        }
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
