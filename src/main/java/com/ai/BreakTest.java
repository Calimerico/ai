package com.ai;

import lombok.Getter;

public class BreakTest {

    @Getter
    private final int depth;

    public BreakTest(int depth) {
        this.depth = depth;
    }

    boolean shouldEvaluate(MiniMaxState state, int depth) {
        return depth == 0;
    }
}
