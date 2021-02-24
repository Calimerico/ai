package com.ai;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Path {
    private List<Node> path = new ArrayList<>();

    public boolean containsState(State state) {
        return path.stream().map(Node::getState).collect(Collectors.toList()).contains(state);
    }

    public boolean containsAction(Action action) {
        return path.stream().map(Node::getAction).collect(Collectors.toList()).contains(action);
    }

    public List<Action> getActions() {
        return path.stream().map(Node::getAction).filter(Objects::nonNull).collect(Collectors.toList());
    }

    public void removeLast() {
        if (!path.isEmpty()) {
            path.remove(path.size() - 1);
        }
    }

    public void add(Node node) {
        if (!path.contains(node)) {
            path.add(node);
        }
    }

    public void clear() {
        path.clear();
    }
}
