package com.ai.cbp;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class CBPIntegerVariable implements CBPVariable<Integer> {

    private final CBPDomainInteger domain;
    private final String variableName;
    private Integer assignedValue;

    public CBPIntegerVariable(String variableName, List<Integer> domainValues) {
        this.variableName = variableName;
        domain = new CBPDomainInteger(domainValues);
    }

    public CBPIntegerVariable(String variableName, List<Integer> domainValues, Integer assignedValue) {
        this(variableName, domainValues);
        this.assignedValue = assignedValue;
    }

    @Override
    public CBPDomain<Integer> getDomain() {
        return domain;
    }

    @Override
    public void removeDomainValue(Integer value) {
        domain.removeDomainValue(value);
    }

    @Override
    public String getVariableName() {
        return variableName;
    }

    @Override
    public void assignDomainValue(Integer value) {
        assignedValue = value;
    }

    @Override
    public Integer getAssignedValue() {
        return assignedValue;
    }

    @Override
    public CBPVariable<Integer> deepCopy() {
        return new CBPIntegerVariable(variableName,domain.getDomainValues().stream().map(CBPDomainValue::getValue).collect(toList()),assignedValue);
    }
}
