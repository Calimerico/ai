package com.ai.cbp;

public interface CBPVariable <T> {
    CBPDomain<T> getDomain();
    void removeDomainValue(T value);
    String getVariableName();
    void assignDomainValue(T value);
    T getAssignedValue();
    CBPVariable<T> deepCopy();
}
