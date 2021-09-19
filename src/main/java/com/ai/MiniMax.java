package com.ai;

public class MiniMax {

    private final BreakTest breakTest;
    private final ZobristHashing zobristHashing;

    public MiniMax(BreakTest breakTest, ZobristHashing zobristHashing) {
        this.breakTest = breakTest;
        this.zobristHashing = zobristHashing;
    }

    public Action search(MiniMaxState state) {
        zobristHashing.initializeZobristValues(state);
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
                alpha = Math.max(alpha, eval);
                if (eval > max) {
                    max = eval;
                    act = action;
                }
                if (max >= beta) {
                    return action;
                }

            }
        } else {
            double min = 1_000_000;
            for (Action action : state.getActions()) {
                double eval = max(((MiniMaxState) state.newState(action)), depth - 1, alpha, beta);
                beta = Math.min(beta, eval);
                if (eval < min) {
                    min = eval;
                    act = action;
                }
                if (min <= alpha) {
                    return action;
                }

            }
        }
        return act;
    }

    private double max(MiniMaxState state, int depth, double alpha, double beta) {
        Float zobristValue = zobristHashing.getCachedValue(state);
        if (zobristValue != null) {
            return zobristValue;
        }
        if (breakTest.shouldEvaluate(state, depth)) {
            double eval = state.getHeuristicFunction();
            zobristHashing.saveIntoCache(state, (float) eval);
            return eval;
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
        Float zobristValue = zobristHashing.getCachedValue(state);
        if (zobristValue != null) {
            return zobristValue;
        }
        if (breakTest.shouldEvaluate(state, depth)) {
            double eval = state.getHeuristicFunction();
            zobristHashing.saveIntoCache(state, (float) eval);
            return eval;
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
