package com.ai;

import java.util.*;

public class HillClimbingSearch {
    private int maxEqualsInARow = 110;
    private List<State> visitedStatesInLocalMaxMin = new ArrayList<>();
    private int currentEqualsInARow;

    public State search(State state, boolean searchForMin) {
        visitedStatesInLocalMaxMin.add(state);
        double heuristicFunction = state.getHeuristicFunction();
        List<State> childStates = new ArrayList<>();
        Set<Action> actions = state.getActions();


        if (actions == null) {
            return state;
        }
        actions.forEach(action -> {
            State newState = state.newState(action);
            if (!visitedStatesInLocalMaxMin.contains(newState)) {
                childStates.add(newState);
            }
        });
        if (childStates.isEmpty()) {
            return state;
        }
        Comparator<State> comparator = Comparator.comparingDouble(State::getHeuristicFunction);
        if (!searchForMin) {
            comparator = comparator.reversed();
        }
        Collections.sort(childStates, comparator);
        State bestChild = childStates.get(0);
        if (searchForMin) {
            if (bestChild.getHeuristicFunction() < heuristicFunction) {
                currentEqualsInARow = 0;
                return search(bestChild,searchForMin);
            } else if (bestChild.getHeuristicFunction() == heuristicFunction) {
                if (currentEqualsInARow < maxEqualsInARow) {
                    currentEqualsInARow++;
                    return search(bestChild,searchForMin);
                }
            } else {
                return state;
            }
        } else {
            if (bestChild.getHeuristicFunction() > heuristicFunction) {
                currentEqualsInARow = 0;
                return search(bestChild,searchForMin);
            } else if (bestChild.getHeuristicFunction() == heuristicFunction) {
                if (currentEqualsInARow < maxEqualsInARow) {
                    currentEqualsInARow++;
                    return search(bestChild,searchForMin);
                }
            } else {
                return state;
            }
        }
        return null;
    }
}
