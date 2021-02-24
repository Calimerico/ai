package com.eightqueens;

import com.Move;
import com.ai.Action;
import lombok.ToString;
import lombok.Value;

@Value
@ToString
public class EightQueensAction implements Action {

    private Move move;
}
