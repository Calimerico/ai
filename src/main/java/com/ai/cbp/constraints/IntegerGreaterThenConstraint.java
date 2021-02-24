package com.ai.cbp.constraints;

import com.ai.cbp.CBPConstraint;
import com.ai.cbp.CBPVariable;

import java.util.List;

public class IntegerGreaterThenConstraint implements CBPConstraint<Integer> {

    @Override
    public boolean violated(List<Integer> domainValues) {
        for (int i = 0; i < domainValues.size() - 1; i++) {
            if (!(domainValues.get(i) > (domainValues.get(i+1)))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return ">";
    }
}
