package com.ai.cbp.arcs;

import com.ai.cbp.CBPConstraint;
import com.ai.cbp.CBPDomainValue;
import com.ai.cbp.CBPVariable;

import java.util.List;
import java.util.stream.Collectors;

public class CBPArc<T> {

    private final List<CBPVariable<T>> variables;
    private final CBPConstraint<T> constraint;

    public CBPArc(List<CBPVariable<T>> variables, CBPConstraint<T> constraint) {
        this.variables = variables;
        this.constraint = constraint;
    }

    public boolean execute() {
        boolean variableDomainChanged = false;
        CBPVariable<T> firstVariable = variables.get(0);
        for (CBPDomainValue<T> domainValue: firstVariable.getDomain().getDomainValues()) {
            firstVariable.assignDomainValue(domainValue.getValue());
            boolean shouldRemoveDomain = shouldRemoveDomain(variables.subList(1, variables.size()));
            if (shouldRemoveDomain) {
                variableDomainChanged = true;
            }
            if (shouldRemoveDomain) {
                firstVariable.removeDomainValue(firstVariable.getAssignedValue());
            }
        }
        return variableDomainChanged;
    }

    private boolean shouldRemoveDomain(List<CBPVariable<T>> variables) {
        CBPVariable<T> currentVariable = variables.get(0);
        List<CBPDomainValue<T>> domainValues = currentVariable.getDomain().getDomainValues();
        if (variables.isEmpty()) {
            return true;
        }

        if (variables.size() == 1) {
            for (CBPDomainValue<T> domainValue : domainValues) {
                currentVariable.assignDomainValue(domainValue.getValue());
                boolean violated = constraint.violated(this.variables.stream().map(CBPVariable::getAssignedValue).collect(Collectors.toList()));
                if (!violated) {
                    return false;
                }
            }
            return true;
        }
        for (CBPDomainValue<T> domainValue : domainValues) {
            currentVariable.assignDomainValue(domainValue.getValue());
            boolean violated = constraint.violated(this.variables.stream().map(CBPVariable::getAssignedValue).limit(this.variables.size() - (variables.size() - 1)).collect(Collectors.toList()));
            if (!violated) {
                boolean shouldRemoveDomainFromVariable = shouldRemoveDomain(variables.subList(1, variables.size()));
                if (!shouldRemoveDomainFromVariable) {
                    return false;
                }
            }

        }
        return true;
    }

    public boolean variableOnTheRightSide(String variableName) {
        for (int i = 1; i < variables.size(); i++) {
            if (variables.get(i).getVariableName().equals(variableName)) {
                return true;
            }
        }
        return false;
    }

    public String getFirstVariableName() {
        return variables.get(0).getVariableName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CBPArc<?> cbpArc = (CBPArc<?>) o;
        for (int i = 0 ; i < variables.size() ; i++) {
            if (!variables.get(i).getVariableName().equals(cbpArc.variables.get(i).getVariableName())) {
                return false;
            }
        }
        return constraint.getClass().equals(cbpArc.constraint.getClass());
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        String symbol = constraint.getSymbol();
        List<String> variableNames = variables.stream().map(variable -> variable.getVariableName() + symbol).collect(Collectors.toList());
        StringBuilder s = new StringBuilder();
        for (String variableName : variableNames) {
            s.append(variableName);
        }
        return s.substring(0, s.length() - symbol.length());
    }
}
