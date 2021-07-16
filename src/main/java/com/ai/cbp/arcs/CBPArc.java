package com.ai.cbp.arcs;

import com.ai.cbp.CBPConstraint;
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
        for (T domainValue: firstVariable.getDomain()) {
            firstVariable.assignDomainValue(domainValue);
            boolean shouldRemoveDomain = shouldRemoveDomainValue(variables.subList(1, variables.size()));
            if (shouldRemoveDomain) {
                variableDomainChanged = true;
                firstVariable.removeAssignedDomainValue();
            }
        }
        return variableDomainChanged;
    }

    private boolean shouldRemoveDomainValue(List<CBPVariable<T>> variables) {
        CBPVariable<T> currentVariable = variables.get(0);
        List<T> domainValues = currentVariable.getDomain();
        for (T domainValue : domainValues) {
            currentVariable.assignDomainValue(domainValue);
            boolean violated = constraint.violated(this.variables.stream().map(CBPVariable::getAssignedValue).limit(this.variables.size() - (variables.size() - 1)).collect(Collectors.toList()));
            if (!violated) {
                if (variables.size() == 1) {
                    return false;
                }
                boolean shouldRemoveDomainFromVariable = shouldRemoveDomainValue(variables.subList(1, variables.size()));
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
