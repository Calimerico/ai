package com.ai.cbp.constraints;

import java.util.List;

public class IntegerEqualsConstraint implements CBPConstraint<Integer> {

    @Override
    public boolean violated(List<Integer> domainValues) {
        for (int i = 0; i < domainValues.size() - 1; i++) {
            if (!domainValues.get(i).equals(domainValues.get(i+1))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return "=";
    }
}
