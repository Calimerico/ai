package com.ai;


import java.util.*;

public class DepthFirstSearch implements Algorithm {

    private ArrayDeque<Node> queue = new ArrayDeque<>();
    private Path path = new Path();

    public List<Action> search(State startState, State goalState) {
        queue.addLast(Node.builder().state(startState).build());
        Node node;
        while ((node = queue.pollLast()) != null) {
            path.add(node);
            if (node.getState().equals(goalState)) {
                return path.getActions();
            }
            Set<Action> childActions = node.getState().getActions();
            boolean foundNewStateInChildren = false;
            for (Action childAction : childActions) {
                State newState = node.getState().newState(childAction);
                if (path.containsState(newState)) {
                    continue;
                }
                foundNewStateInChildren = true;
                queue.addLast(
                        Node
                                .builder()
                                .parent(node)
                                .action(childAction)
                                .state(newState)
                                .depth(node.getDepth() + 1)
                                .build());
            }
            if (!foundNewStateInChildren) {
                queue.pollLast();
                path.removeLast();
            }
        }
        return null;
    }

    public List<Action> search(State startState, State goalState, int depth) {
        queue.addLast(Node.builder().state(startState).build());
        Node node;
        while ((node = queue.pollLast()) != null) {
            if (node.getDepth() > depth) {
                return null;
            }
            path.add(node);
            if (node.getState().equals(goalState)) {
                return path.getActions();
            }
            Set<Action> childActions = node.getState().getActions();
            boolean foundNewStateInChildren = false;
            for (Action childAction : childActions) {
                State newState = node.getState().newState(childAction);
                if (path.containsState(newState)) {
                    continue;
                }
                foundNewStateInChildren = true;
                queue.addLast(
                        Node
                                .builder()
                                .parent(node)
                                .action(childAction)
                                .state(newState)
                                .depth(node.getDepth() + 1)
                                .build());
            }
            if (!foundNewStateInChildren) {
                queue.pollLast();
                path.removeLast();
            }
        }
        return null;
    }
}
