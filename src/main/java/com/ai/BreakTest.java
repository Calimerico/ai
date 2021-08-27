package com.ai;

public interface BreakTest {
    boolean shouldEvaluate(MiniMaxState state, int depth);
    int getDepth();
}
