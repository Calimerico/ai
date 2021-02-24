package com.ai;

import java.util.*;

public class AStarSearch implements Algorithm {

    private Set<Node> set = new HashSet<>();
    private Path path = new Path();

    public List<Action> search(State startState, State goalState) {
        set.add(Node.builder().state(startState).build());

        while (!set.isEmpty()) {
            Node nextNode = new Node(null,null,startState,0,Double.MAX_VALUE/1.3);
            for (Node n : set) {
                double aStarEvaluationOfN = n.getAStarEvaluationFunction();
                double aStarEvaluationOfNest = nextNode.getAStarEvaluationFunction();
                if (aStarEvaluationOfN < aStarEvaluationOfNest) {
                    nextNode = n;
                }
            }
            set.remove(nextNode);

            path.add(nextNode);
            if (nextNode.getState().equals(goalState)) {
                return path.getActions();
            }
            Set<Action> childActions = nextNode.getState().getActions();
            boolean foundNewStateInChildren = false;
            for (Action childAction : childActions) {
                State newState = nextNode.getState().newState(childAction);
                if (path.containsState(newState)) {
                    continue;
                }
                foundNewStateInChildren = true;
                set.add(
                        Node
                                .builder()
                                .parent(nextNode)
                                .action(childAction)
                                .state(newState)
                                .depth(nextNode.getDepth() + 1)
                                .costFromStartNode(nextNode.getCostFromStartNode() + childAction.pathCost())
                                .build());
            }
            if (!foundNewStateInChildren) {
                path.removeLast();
            }
        }
        return null;
    }
}
