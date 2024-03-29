package com.sudoku;

import com.ai.Action;
import com.ai.State;
import com.ai.cbp.CBPVariable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SudokuState implements State {

    private final CBPVariable<Integer>[][] sudokuVariables;

    private SudokuState(CBPVariable<Integer>[][] sudokuVariables) {
        this.sudokuVariables = sudokuVariables;
    }

    public SudokuState(List<CBPVariable<Integer>> sudokuVariables) {
        this.sudokuVariables = new CBPVariable[9][9];
        for (CBPVariable<Integer> sudokuVariable : sudokuVariables) {
            char[] chars = sudokuVariable.getVariableName().toCharArray();
            int column = Character.getNumericValue(chars[0]);
            int row = Character.getNumericValue(chars[2]);
            this.sudokuVariables[row - 1][column - 1] = sudokuVariable;
        }
    }



    @Override
    public Set<Action> getActions() {
        Set<Action> actions = new HashSet<>();
        for (CBPVariable<Integer>[] row : sudokuVariables) {
            for (CBPVariable<Integer> variable : row) {
                if (variable.getAssignedValue() == null) {
                    variable.getDomain().forEach(domainValue -> actions.add(new SudokuAction<>(variable.getVariableName(), domainValue)));
                }
                return actions;
            }
        }
        return actions;
    }

    @Override
    public State newState(Action action) {
//        SudokuAction<Integer> sudokuAction = (SudokuAction<Integer>) action;
//        SudokuState state = (SudokuState) deepCopy();
//        for (CBPVariable<Integer>[] row : sudokuVariables) {
//            for (CBPVariable<Integer> variable : row) {
//                if (variable.getVariableName().equals(sudokuAction.getVariableName())) {
//                    variable.assignDomainValue(sudokuAction.getDomainValue());
//                }
//            }
//        }
        return null;//todo
    }

    @Override
    public double getHeuristicFunction() {
        return 0;
    }

    public void print() {
        for (CBPVariable<Integer>[] row : sudokuVariables) {
            for (CBPVariable<Integer> data : row) {
                System.out.printf(data.getAssignedValue() == null ? " " : String.valueOf(data.getAssignedValue()));
            }
            System.out.println(" ");
        }
    }
}
