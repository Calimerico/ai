package com.ai.cbp;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class CBPVariable<T> {
    private final CBPDomain<T> domain;
    private final String variableName;
    private T assignedValue;

    public CBPVariable(String variableName, List<T> domainValues) {
        this.variableName = variableName;
        domain = new CBPDomain<T>(domainValues);
    }

    public CBPVariable(String variableName, List<T> domainValues, T assignedValue) {
        this(variableName, domainValues);
        this.assignedValue = assignedValue;
    }

    public CBPDomain<T> getDomain() {
        return domain;
    }

    public void removeDomainValue(T value) {
        domain.removeDomainValue(value);
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
        return new CBPVariable<T>(
                variableName,
                domain.getDomainValues().stream().map(CBPDomainValue::getValue).collect(toList()),
                assignedValue
        );
    }
}
