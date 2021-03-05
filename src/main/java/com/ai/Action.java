package com.ai;

public interface Action extends Comparable<Action> {
    default double pathCost() {
        return 0;
    }

    @Override
    default int compareTo(Action o) {
        return 0;
    }
}
