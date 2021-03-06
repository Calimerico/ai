package com.ai;

import com.romania.RomaniaState;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.*;
import java.util.concurrent.*;

public class MiniMax {

    private ExecutorService executorService = Executors.newCachedThreadPool();
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
        List<ImmutablePair<Action, Future<Double>>> evaluations = new ArrayList<>();
        Set<Action> actions = state.getActions();
        if (state.maxPlayer()) {
            for (Action action : actions) {
                evaluations.add(
                        new ImmutablePair<>(
                                action,
                                executorService
                                        .submit(() -> min(((MiniMaxState) state.newState(action)), depth - 1))
                        )
                );
            }
            double max = -1 * Double.MAX_VALUE;
            for (ImmutablePair<Action, Future<Double>> evaluation : evaluations) {
                try {
                    System.out.println(evaluation.getLeft() + ": " + evaluation.getRight().get());
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
            for (ImmutablePair<Action, Future<Double>> evaluation : evaluations) {
                Double eval = null;
                try {
                    eval = evaluation.getRight().get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
                if (eval > max) {
                    max = eval;
                    act = evaluation.getLeft();
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
