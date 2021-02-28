package com.sudoku;

import com.ai.Action;
import com.ai.State;
import com.ai.cbp.CBPIntegerVariable;
import com.ai.cbp.CBPVariable;
import lombok.AllArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SudokuState implements State {

    private CBPVariable<Integer>[][] sudokuVariables;

    private SudokuState(CBPVariable<Integer>[][] sudokuVariables) {
        this.sudokuVariables = sudokuVariables;
    }

    public SudokuState(List<CBPVariable<Integer>> sudokuVariables) {
        this.sudokuVariables = new CBPIntegerVariable[9][9];
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
                    variable.getDomain().getDomainValues().forEach(domainValue -> actions.add(new SudokuAction<>(variable.getVariableName(), domainValue.getValue())));
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

    @Override
    public State deepCopy() {
        CBPVariable<Integer>[][] sudokuVariables = new CBPIntegerVariable[9][9];
        for (int i =0 ; i < sudokuVariables.length ; i++) {
            for (int j =0 ; j < sudokuVariables.length ; j++) {
                sudokuVariables[i][j] = this.sudokuVariables[i][j].deepCopy();
            }
        }
        return new SudokuState(sudokuVariables);
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
