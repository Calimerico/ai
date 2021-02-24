package com.ai;

import java.util.*;

public class BestFirstSearch implements Algorithm {

    private ArrayDeque<Node> queue = new ArrayDeque<>();
    private Path path = new Path();

    public List<Action> search(State startState, State goalState) {
        queue.addLast(Node.builder().state(startState).build());
        Node node;
        while ((node = queue.pollLast()) != null) {
            path.add(node);
            State currentState = node.getState();
            if (currentState.equals(goalState)) {
                return path.getActions();
            }
            Set<Action> childActions = currentState.getActions();
            boolean foundNewStateInChildren = false;
            List<Node> childNodes = new ArrayList<>();
            for (Action childAction : childActions) {
                childNodes.add(
                        Node
                                .builder()
                                .parent(node)
                                .action(childAction)
                                .state(currentState.newState(childAction))
                                .depth(node.getDepth() + 1)
                                .build()
                );
            }
            Collections.sort(childNodes, Comparator.comparingDouble(o -> -1*o.getState().getHeuristicFunction()));
            for (Node childNode : childNodes) {
                if (path.containsState(childNode.getState())) {
                    continue;
                }
                foundNewStateInChildren = true;
                queue.addLast(childNode);
            }
            if (!foundNewStateInChildren) {
                queue.pollLast();
                path.removeLast();
            }
        }
        return null;
    }
}
