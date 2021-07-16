package com.ai.cbp;

import java.util.ArrayList;
import java.util.List;

public class CBPVariable<T> {
    private final List<T> domain;
    private final String variableName;
    private T assignedValue;

    public CBPVariable(String variableName, List<T> domain) {
        this.variableName = variableName;
        this.domain = new ArrayList<>(domain);
    }

    public List<T> getDomain() {
        return new ArrayList<>(domain);
    }

    public void removeAssignedDomainValue() {
        domain.remove(assignedValue);
    }

    public String getVariableName() {
        return variableName;
    }

    public void assignDomainValue(T value) {
        assignedValue = value;
    }

    public T getAssignedValue() {
        return assignedValue;
    }

    public CBPVariable<T> deepCopy() {
        CBPVariable<T> variable = new CBPVariable<T>(
                variableName,
                new ArrayList<>(domain)
        );
        variable.assignDomainValue(this.assignedValue);
        return variable;
    }
}
