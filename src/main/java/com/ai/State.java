package com.ai;

import java.util.Set;

public interface State {
    Set<Action> getActions();
    State newState(Action action);
    double getHeuristicFunction();
    State deepCopy();
}
