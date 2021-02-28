package com.ai;

import java.util.List;
import java.util.Set;

public class DummyMiniMax implements MiniMaxState {

    private int eval;
    private boolean maxOnTheMove;
    private Set<Action> actionList;

    public DummyMiniMax(int eval, boolean maxOnTheMove, Set<Action> actionList) {
        this.eval = eval;
        this.maxOnTheMove = maxOnTheMove;
        this.actionList = actionList;
    }

    @Override
    public boolean maxPlayer() {
        return maxOnTheMove;
    }

    @Override
    public Set<Action> getActions() {
        return actionList;
    }

    @Override
    public State newState(Action action) {
        return new DummyMiniMax(eval + ((DummyAction) action).getAdd(),!maxOnTheMove, actionList);
    }

    @Override
    public double getHeuristicFunction() {
        return eval;
    }

    @Override
    public State deepCopy() {
        return null;
    }
}
