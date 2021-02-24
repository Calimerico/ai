package com.ai;

import com.ai.Action;
import com.ai.State;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Builder
@Getter
public class Node {
    private Node parent;
    private Action action;
    private State state;
    private int depth;
    private double costFromStartNode;

    public void populatePath(List<Action> actions) {
        if (action != null) {
            actions.add(action);
            parent.populatePath(actions);
        }
    }

    public double getAStarEvaluationFunction() {
        return getCostFromStartNode() + getState().getHeuristicFunction();
    }

    @Override
    public String toString() {
        return "State = " + state.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(parent, node.parent) &&
                Objects.equals(action, node.action) &&
                state.equals(node.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parent, action, state);
    }
}
