/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Compilers.Program;

import Compilers.Program.Symbols;
import java.util.Stack;

/**
 *
 * @author uziel
 */
public class Program {
    
    private static final Integer INSTRUCTION = 0;

    private static final Integer ITERATION = 1;
    
    private Stack<Integer> Types;
    
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
        Types = new Stack();
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
        Types = new Stack();
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
        Integer state = Box.gets(CommandList[Counter++]);
        if(Symbols.TURNOFF == state){
            TurnedOn = false;
            return state;
        }
        if(Box.isAction(state))
            return state;
        if(Box.isCondition(state)){
            if(executeConditional(state,true,0)){
                Counter += 2;
                return null;
            }else{
                Counter = Integer.parseInt(CommandList[Counter+1]);
                return null;
            }
        }
        if(Symbols.ITERATE == state){
            Integer initialState = Counter - 1;
            if(IterVal < executeNumber()){
                Parameter.push(Parameter.peek());
                Dirs.push(initialState);
                IterStack.push(IterVal+1);
                Types.push(ITERATION);
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
        if(Symbols.BRANCH == state){
            Counter = Integer.parseInt(CommandList[Counter]);
            return null;
        }
        if(Symbols.CALL == state){
            Dirs.push(Counter + 1);
            Parameter.push(0);
            IterStack.push(0);
            Types.push(INSTRUCTION);
            Counter = Integer.parseInt(CommandList[Counter]);
            return null;
        }
        if(Symbols.PARAMCALL == state){
            Integer i = Integer.parseInt(CommandList[Counter]);
            Dirs.push(Counter + 2);
            Counter++;
            Parameter.push(executeNumber());
            IterStack.push(0);
            Types.push(INSTRUCTION);
            Counter = i;
            return null;
        }
        if(Symbols.RETURN == state){
            Counter = Dirs.pop();
            Parameter.pop();
            IterVal = IterStack.pop();
            Types.pop();
            return null;
        }
        if(Symbols.EXITINST == state){
            Integer Type = null;
            do {
                Counter = Dirs.pop();
                Parameter.pop();
                IterVal = IterStack.pop();
                Type = Types.pop();
            }while(Type == INSTRUCTION);
        }
        return null;
    }
    
    private Boolean executeConditional(Integer state, Boolean pred, Integer act){
        boolean b = true;
        boolean not = false;
        
        if(state == Symbols.NOT)
            not = true;
        
        switch(state){
            
            case Symbols.CFCLEAR: b = Env.isFrontClear(); break;
            case Symbols.CFBLOCK: b = !Env.isFrontClear(); break;
            case Symbols.CLCLEAR: b = Env.isLeftClear(); break;
            case Symbols.CLBLOCK: b = !Env.isLeftClear(); break;
            case Symbols.CRCLEAR: b = Env.isRightClear(); break;
            case Symbols.CRBLOCK: b = !Env.isRightClear(); break;
            case Symbols.NEXTB: b = Env.isNextToABeeper(); break;
            case Symbols.NONEXTB: b = !Env.isNextToABeeper(); break;
            case Symbols.ANYBBAG: b = Env.isThereAnyBeepersInBag(); break;
            case Symbols.NOBBAG: b = !Env.isThereAnyBeepersInBag(); break;
            case Symbols.FACINGN: b = Env.isFacingNorth(); break;
            case Symbols.FACINGS: b = Env.isFacingSouth(); break;
            case Symbols.FACINGE: b = Env.isFacingEast(); break;
            case Symbols.FACINGW: b = Env.isFacingWest(); break;
            case Symbols.NOFACINGN: b = !Env.isFacingNorth(); break;
            case Symbols.NOFACINGS: b = !Env.isFacingSouth(); break;
            case Symbols.NOFACINGE: b = !Env.isFacingEast(); break;
            case Symbols.NOFACINGW: b = !Env.isFacingWest(); break;
                
            case Symbols.IFZERO: b = (executeNumber() == 0); break;

        }
        
        if(not)
            b = !b;
        
        if(act == 0)
            b = (pred && b);
        else
            b = (pred || b);
        
        if(Box.gets(CommandList[Counter]) == Symbols.AND)
            return executeConditional(Box.gets(CommandList[++Counter]),b,0);
        if(Box.gets(CommandList[Counter]) == Symbols.OR)
            return executeConditional(Box.gets(CommandList[++Counter]),b,1);
        if(Box.gets(CommandList[Counter]) == Symbols.CONDBRANCH)
            return b;
        return null;
    }
    
    private Integer executeNumber(){
        Integer i = Box.gets(CommandList[Counter]);
        if(i == null)
            return Integer.parseInt(CommandList[Counter++]);
        if(i == Symbols.PARAM){
            Counter++;
            return Parameter.peek();
        }
        if(i == Symbols.PRED){
            Counter++;
            return (executeNumber() - 1);
        }
        if(i == Symbols.SUCC){
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
