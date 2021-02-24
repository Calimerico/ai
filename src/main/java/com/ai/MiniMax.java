package com.ai;

import com.romania.RomaniaState;

import java.util.*;

public class MiniMax {

    private double alpha = Double.MIN_VALUE;//best found for max
    private double beta = Double.MAX_VALUE;//best found for min
    private ArrayDeque<Node> queue = new ArrayDeque<>();
    private Path path = new Path();
    private EvaluationFunction evaluationFunction;
    private BreakTest breakTest;

    public MiniMax(EvaluationFunction evaluationFunction, BreakTest breakTest) {
        this.evaluationFunction = evaluationFunction;
        this.breakTest = breakTest;
    }


    public Action search(MiniMaxState state) {
        return search(state, 10);
    }

    private double search(MiniMaxState state, int depth) {
        if (breakTest.shouldEvaluate(state, depth)) {
            return state.getHeuristicFunction();
        }
        if (state.maxPlayer()) {
            double max = Double.MIN_VALUE;
            Action act = null;
            for (Action action : state.getActions()) {
                double eval = search(((MiniMaxState) state.newState(action)), depth - 1);
                if (eval > max) {
                    max = eval;
                    act = action;
                }

            }
            path.add(new Node());
            return max;
        }
    }

    private Map.Entry<Action, Double> max(MiniMaxState state, int depth) {

        double max = Double.MIN_VALUE;
        for (Action action : state.getActions()) {
            double newStateValue = state.newState(action).getHeuristicFunction();
            if (newStateValue >= beta) {
                return newStateValue;
            }
            max = Math.max(max, newStateValue);
        }
        return max;
    }

    private double min(MiniMaxState state, int depth) {
        if (breakTest.shouldEvaluate(state, depth)) {
            return state.getHeuristicFunction();
        }
        double min = Double.MAX_VALUE;
        for (Action action : state.getActions()) {
            double newStateValue = state.newState(action).getHeuristicFunction();
            if (newStateValue <= alpha) {
                return newStateValue;
            }
            min = Math.min(min, newStateValue);
        }
        return min;
    }
}
