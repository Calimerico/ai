package com.ai;

import com.romania.RomaniaState;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.*;
import java.util.concurrent.*;

public class MiniMax {

    private double alpha = Double.MIN_VALUE;//best found for max
    private double beta = Double.MAX_VALUE;//best found for min
    private BreakTest breakTest;

    public MiniMax(BreakTest breakTest) {
        this.breakTest = breakTest;
    }


    public Action search(MiniMaxState state) {
        long millis = System.currentTimeMillis();
        Action search = search(state, 6);
        return search;
    }

    private Action search(MiniMaxState state, int depth) {
        Action act = null;
        if (state.maxPlayer()) {
            double max = Double.MIN_VALUE;
            for (Action action : state.getActions()) {
                double eval = min(((MiniMaxState) state.newState(action)), depth - 1);
                if (eval > max) {
                    max = eval;
                    act = action;
                }
            }
        } else {
            double min = Double.MAX_VALUE;
            for (Action action : state.getActions()) {
                double eval = max(((MiniMaxState) state.newState(action)), depth - 1);
                if (eval < min) {
                    min = eval;
                    act = action;
                }
            }
        }
        return act;
    }

    private double max(MiniMaxState state, int depth) {
        if (breakTest.shouldEvaluate(state, depth)) {
            return state.getHeuristicFunction();
        }
        double max = -1 * Double.MAX_VALUE;
        for (Action action : state.getActions()) {
            double newStateValue = min(((MiniMaxState) state.newState(action)), depth - 1);
            if (newStateValue >= beta) {
                return newStateValue;
            }
            if (newStateValue > max) {
                max = newStateValue;
                alpha = max;
            }
        }
        return max;
    }

    private double min(MiniMaxState state, int depth) {
        if (breakTest.shouldEvaluate(state, depth)) {
            return state.getHeuristicFunction();
        }
        double min = Double.MAX_VALUE;
        for (Action action : state.getActions()) {
            double newStateValue = max(((MiniMaxState) state.newState(action)), depth - 1);
            if (newStateValue <= alpha) {
                return newStateValue;
            }
            if (newStateValue < min) {
                min = newStateValue;
                beta = min;
            }
        }
        return min;
    }
}
