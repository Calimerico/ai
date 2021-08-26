package com.ai;

import com.romania.RomaniaState;

import java.util.*;

public class MiniMax {

    private final BreakTest breakTest;

    public MiniMax(BreakTest breakTest) {
        this.breakTest = breakTest;
    }

    public Action search(MiniMaxState state) {
        return search(state, breakTest.getDepth());
    }

    private Action search(MiniMaxState state, int depth) {
        double alpha = -1_000_000;//best found for max
        double beta = 1_000_000;//best found for min
        Action act = null;
        if (state.maxPlayer()) {
            double max = -1_000_000;
            for (Action action : state.getActions()) {
                double eval = min(((MiniMaxState) state.newState(action)), depth - 1, alpha, beta);
                if (eval > max) {
                    max = eval;
                    act = action;
                }
            }
        } else {
            double min = 1_000_000;
            for (Action action : state.getActions()) {
                double eval = max(((MiniMaxState) state.newState(action)), depth - 1, alpha, beta);
                if (eval < min) {
                    min = eval;
                    act = action;
                }
            }
        }
        return act;
    }

    private double max(MiniMaxState state, int depth, double alpha, double beta) {
        if (breakTest.shouldEvaluate(state, depth)) {
            return state.getHeuristicFunction();
        }
        double max = -1_000_000;
        for (Action action : state.getActions()) {
            double eval = min(((MiniMaxState) state.newState(action)), depth - 1, alpha, beta);
            max = Math.max(eval, max);
            alpha = Math.max(alpha, eval);
            if (max >= beta) {
                return eval;
            }
        }
        return max;
    }

    private double min(MiniMaxState state, int depth, double alpha, double beta) {
        if (breakTest.shouldEvaluate(state, depth)) {
            return state.getHeuristicFunction();
        }
        double min = 1_000_000;
        for (Action action : state.getActions()) {
            double eval = max(((MiniMaxState) state.newState(action)), depth - 1, alpha, beta);
            min = Math.min(eval, min);
            beta = Math.min(beta, eval);
            if (min <= alpha) {
                return eval;
            }
        }
        return min;
    }
}
