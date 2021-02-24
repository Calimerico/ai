package com.ai.cbp;

public class CBPDomainValue <T> {
    T value;
    boolean removed;

    public CBPDomainValue(T value) {
        this.value = value;
    }

    public void markRemoved() {
        removed = true;
    }

    public boolean isRemoved() {
        return removed;
    }

    public T getValue() {
        return value;
    }
}
