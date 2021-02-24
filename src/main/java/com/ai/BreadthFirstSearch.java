package com.ai;


import java.util.*;

public class BreadthFirstSearch implements Algorithm {

    private Queue<Node> queue = new ArrayDeque<>();
    private Set<State> visitedStates = new HashSet<>();

    public List<Action> search(State startState, State goalState) {
        List<Action> actions = new ArrayList<>();
        if (startState.equals(goalState)) {
            return actions;
        }
        visitedStates.add(startState);
        queue.add(Node.builder().state(startState).build());
        Node node;
        while ((node = queue.poll()) != null) {
            State state = node.getState();
            for (Action action : state.getActions()) {
                State newState = state.newState(action);
                if (visitedStates.contains(newState)) {
                    continue;
                }
                if (goalState.equals(newState)) {
                    actions.add(action);
                    node.populatePath(actions);
                    return actions;
                }
                visitedStates.add(node.getState());
                queue.add(
                        Node
                                .builder()
                                .parent(node)
                                .action(action)
                                .state(newState)
                                .depth(node.getDepth() + 1)
                                .build());
            }
        }
        return null;
    }
}
