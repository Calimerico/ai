package com.ai;

public class BreakTest {

    boolean shouldEvaluate(MiniMaxState state, int depth) {
        return depth == 0;
    }
}
