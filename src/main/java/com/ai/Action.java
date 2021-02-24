package com.ai;

public interface Action {
    default double pathCost() {
        return 0;
    }

}
