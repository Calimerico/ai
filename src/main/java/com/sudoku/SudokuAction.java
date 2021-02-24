package com.sudoku;

import com.ai.Action;
import lombok.Value;

@Value
public class SudokuAction <T> implements Action {
    String variableName;
    T domainValue;
}
