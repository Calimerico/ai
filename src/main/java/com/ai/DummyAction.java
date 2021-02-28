package com.ai;

public class DummyAction implements Action {
    private int add;

    public DummyAction(int add) {
        this.add = add;
    }

    public int getAdd() {
        return add;
    }

    @Override
    public String toString() {
        return "DummyAction{" +
                "add=" + add +
                '}';
    }
}
